package io.arvi.office.database;

import java.time.ZoneId;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class CompanyDBService {
    
    private PgPool dbClient;
    
    public CompanyDBService(Vertx vertx, PgPool client) {
        this.dbClient = client;

        vertx.eventBus().consumer("database.company", this::onMessage);
    }

    private void onMessage(Message<JsonObject> message) {

        String action = message.headers().get("action");
        
        switch (action) {
            case "get-company":
                getCompany(message);
                break;
            case "update-company":
                updateCompany(message);                
                break;
            default:
                break;
        }
    }

    private void getCompany(Message<JsonObject> message) {
        dbClient.query("SELECT * FROM public.company LIMIT 1")
            .execute(ar -> {
                if (ar.succeeded()) {
                    RowSet<Row> result = ar.result();
                    JsonObject company = new JsonObject();
                    for (Row row : result) {
                        company
                            .put("company_id", row.getInteger("company_id"))
                            .put("name", row.getString("name"))
                            .put("start_year", row.getInteger("tax_date"))
                            .put("tax_prefix_no", row.getString("tax_id"))
                            .put("tax_id", row.getString("tax_id"))
                            .put("tax_responsible_id", row.getString("tax_responsible_id"))
                            .put("tax_responsible_start_date", row.getLocalDate("tax_responsible_start_date").atStartOfDay(ZoneId.of("UTC")).toInstant())
                            .put("version", row.getInteger("version"));
                            /*.put("updated_date", row.getOffsetDateTime("updated_date").toInstant())
                            .put("updated_by", row.getString("updated_by"))
                            .put("deleted", row.getBoolean("deleted"));*/
                    }
                    message.reply(company);    
                } else {

                }
            });
        
    }

    private void updateCompany(Message<JsonObject> message) {

    }
}

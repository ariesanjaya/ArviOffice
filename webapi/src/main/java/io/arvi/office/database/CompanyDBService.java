package io.arvi.office.database;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.Tuple;

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
                            .put("start_year", row.getInteger("start_year"))
                            .put("tax_date", row.getLocalDate("tax_date").atStartOfDay(ZoneId.of("UTC")).toInstant())
                            .put("tax_prefix_no", row.getString("tax_prefix_no"))
                            .put("tax_id", row.getString("tax_id"))
                            .put("tax_responsible_id", row.getString("tax_responsible_id"))
                            .put("tax_responsible_start_date", row.getLocalDate("tax_responsible_start_date").atStartOfDay(ZoneId.of("UTC")).toInstant())
                            .put("version", row.getInteger("version"))
                            .put("updated_date", row.getOffsetDateTime("updated_date").toInstant())
                            .put("updated_by", row.getString("updated_by"))
                            .put("deleted", row.getBoolean("deleted"));
                    }
                    message.reply(company);    
                } else {

                }
            });        
    }

    private void updateCompany(Message<JsonObject> message) {
        JsonObject body = message.body();
        LocalDate taxDate = LocalDate.ofInstant(body.getInstant("tax_date"), ZoneId.of("UTC"));
        LocalDate taxResponsibleStartDate = LocalDate.ofInstant(body.getInstant("tax_responsible_start_date"), ZoneId.of("UTC"));
        OffsetDateTime updatedDate = OffsetDateTime.ofInstant(body.getInstant("updated_date"), ZoneId.of("UTC"));
        dbClient.begin(res -> {
            // Get Transaction
            Transaction tx = res.result();
            // Update company statement
            tx.preparedQuery("UPDATE company SET name = $1, " 
                + "start_year = $2, tax_date = $3, " 
                + "tax_prefix_no = $4, tax_id = $5, "
                + "tax_responsible_id = $6, tax_responsible_start_date = $7, "
                + "version = $8, updated_date = $9, updated_by = $10 " 
                + "WHERE company_id = 1")
            .execute(Tuple.of(
                body.getString("name"),
                body.getInteger("start_year"),
                taxDate,
                body.getString("tax_prefix_no"),
                body.getString("tax_id"),
                body.getString("tax_responsible_id"),
                taxResponsibleStartDate,
                body.getString("version"),
                updatedDate,
                body.getString("updated_by")),ar -> {
                    if (ar.succeeded()) {
                        // Commit the transaction
                        // the connection will automatically return to the pool
                        tx.commit(ar3 -> {
                            if (ar3.succeeded()) {
                                System.out.println("Transaction succeeded");
                                message.reply("Transaction succeeded");
                            } else {
                                System.out.println("Transaction failed " + ar3.cause().getMessage());
                            }
                        });
                    }
            });
        });        
    }
}

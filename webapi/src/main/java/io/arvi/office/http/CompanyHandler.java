package io.arvi.office.http;

import java.time.ZoneId;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class CompanyHandler {

	public CompanyHandler(Router router) {
		
		//Company API Route
        router.get("/api/company").handler(this::companyGetHandler);
        router.put("/api/company:id").handler(this::companyUpdateHandler);
	}
	
	private void companyGetHandler(RoutingContext context) {
        /*dbClient
            .query("SELECT * FROM company LIMIT 1")
            .execute(ar -> {
                if (ar.succeeded()) {
                    RowSet<Row> result = ar.result();
                    JsonObject json = new JsonObject();
                    
                    for (Row row : result) {
                    	json.put("company_id", row.getInteger("company_id"));
                    	json.put("name", row.getString("name"));
                    	json.put("start_year", row.getShort("start_year"));
                    	json.put("tax_date", row.getLocalDate("tax_date").atStartOfDay(ZoneId.of("UTC")).toInstant());
                    	json.put("start_year", row.getShort("start_year"));
                    	json.put("tax_prefix_no", row.getString("tax_prefix_no"));
                    	json.put("tax_id", row.getString("tax_id"));
                    	json.put("tax_responsible_id", row.getString("tax_responsible_id"));
                    	json.put("tax_responsible_start_date", row.getLocalDate("tax_responsible_start_date").atStartOfDay(ZoneId.of("UTC")).toInstant());
                    	json.put("version", row.getInteger("version"));
                    	json.put("updated_date", row.getOffsetDateTime("updated_date").toInstant());
                    	json.put("updated_by", row.getString("updated_by"));
                    	json.put("deleted", row.getBoolean("deleted"));
                    }

                    context.response()
                        .putHeader("content-type", "application/json")
                        .end(json.encodePrettily());
                }
            });*/        
		context.response()
        .putHeader("content-type", "text/html")
        .end("Hello World");
    }

    private void companyUpdateHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Company UPDATE");
    }
}

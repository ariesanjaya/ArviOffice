package io.arvi.office;

import java.time.ZoneId;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class MainVerticle extends AbstractVerticle {

    private PgPool dbClient;

    @Override
    public void start(Promise<Void> promise) throws Exception {
        Future<Void> steps = prepareDatabase().compose(v -> startHttpServer());
        steps.onComplete(promise);
    }

    private Future<Void> prepareDatabase() {
        Promise<Void> promise = Promise.promise();

        PgConnectOptions connectOptions = new PgConnectOptions()
            .setPort(5432)
            .setHost("localhost")
            .setDatabase("postgres")
            .setUser("postgres")
            .setPassword("admin");
        
        PoolOptions poolOptions = new PoolOptions()
            .setMaxSize(5);
        
        dbClient = PgPool.pool(vertx, connectOptions, poolOptions);

        dbClient.getConnection(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
            } else {
                promise.complete();
            }
        });

        return promise.future();
    }

    private Future<Void> startHttpServer() {
        Promise<Void> promise = Promise.promise();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        //Company API Route
        router.get("/api/company").handler(this::companyGetHandler);
        router.put("/api/company:id").handler(this::companyUpdateHandler);
        //Branch API Route
        router.get("/api/branch").handler(this::branchGetHandler);
        router.post("/api/branch").handler(this::branchAddHandler);
        router.put("/api/branch/:id").handler(this::branchUpdateHandler);
        router.delete("/api/branch/:id").handler(this::branchDeleteHandler);
        //Employee API Route
        router.get("/api/employee").handler(this::employeeGetHandler);
        router.post("/api/employee").handler(this::employeeAddHandler);
        router.put("/api/employee:id").handler(this::employeeUpdateHandler);
        router.delete("/api/employee:id").handler(this::employeeDeleteHandler);
        
        server.requestHandler(router)
            .listen(8080, ar -> {
                if (ar.succeeded()) {
                    promise.complete();
                } else {
                    promise.fail(ar.cause());
                }
            });

        return promise.future();
    }

    // Company API Handler

    private void companyGetHandler(RoutingContext context) {
        dbClient
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
                    	json.put("updated_date", row.getLocalDateTime("updated_date").atZone(ZoneId.of("UTC")).toInstant());
                    	json.put("updated_by", row.getString("updated_by"));
                    	json.put("deleted", row.getBoolean("deleted"));
                    	
                    }

                    context.response()
                        .putHeader("content-type", "application/json")
                        .end(json.encodePrettily());
                }
            });
        
    }

    private void companyUpdateHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Company UPDATE");
    }
    
    // Branch API Handler

    private void branchGetHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Branch GET");
    }

    private void branchAddHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Branch ADD");
    }

    private void branchUpdateHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Branch UPDATE");
    }

    private void branchDeleteHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Branch DELETE");
    }

    // Employee API Handler

    private void employeeGetHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Employee GET");
    }

    private void employeeAddHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Employee ADD");
    }

    private void employeeUpdateHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Employee UPDATE");
    }

    private void employeeDeleteHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello Employee DELETE");
    }
}

package io.arvi.office.http;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class CompanyHandler {
    
    private Vertx vertx;

    public CompanyHandler(Vertx vertx, Router router) {
        this.vertx = vertx;

        router.get("/company").handler(this::companyGetHandler);
        router.put("/company").handler(this::companyUpdateHandler);
    
    }

    private void companyGetHandler(RoutingContext context) {
 
        DeliveryOptions options = new DeliveryOptions().addHeader("action", "get-company");
        vertx.eventBus().request("database.company", new JsonObject(), options, reply -> {

            if (reply.succeeded()) {
                JsonObject body = (JsonObject)reply.result().body();
                
                context.response()
                    .putHeader("content-type", "application/json")
                    .end(body.encodePrettily());
            }
        });    
    }

    private void companyUpdateHandler(RoutingContext context) {
        DeliveryOptions options = new DeliveryOptions().addHeader("action", "update-company");
        JsonObject body = context.getBodyAsJson();
        vertx.eventBus().request("database.company", body, options, reply -> {

            if (reply.succeeded()) {
                
                context.response()
                    .putHeader("content-type", "application/json")
                    .end(reply.result().body().toString());
            } else {
                context.fail(reply.cause());
            }
        });
    }
}

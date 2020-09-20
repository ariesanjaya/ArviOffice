package io.arvi.office.http;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class BranchHandler {
    
    private Vertx vertx;

    public BranchHandler(Vertx vertx, Router router) {
        this.vertx = vertx;

        router.get("/branches").handler(this::branchGetAllHandler);
        router.post("/branches").handler(this::branchAddHandler);
        router.put("/branches/:id").handler(this::branchUpdateHandler);
    }

    private void branchGetAllHandler(RoutingContext context) {
        DeliveryOptions options = new DeliveryOptions().addHeader("action", "get-branches");
        vertx.eventBus().request("database.branches", new JsonObject(), options, reply -> {
            if (reply.succeeded()) {
                JsonArray body = (JsonArray)reply.result().body();
                context.response()
                    .putHeader("content-type", "application/json")
                    .end(body.encodePrettily());
            } else {
                System.out.println("Failed Reply is called...");
                
            }         
        });        
    }
    
    private void branchAddHandler(RoutingContext context) {

    }

    private void branchUpdateHandler(RoutingContext context) {

    }
}

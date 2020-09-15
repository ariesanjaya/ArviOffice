package io.arvi.office.http;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class InventoryHandler {
    
    public void setRouter(Router router) {

        router.get("/item-group").handler(this::itemGroupGetHandler);
    }

    private void itemGroupGetHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "application/json")
            .end(new JsonObject().put("hello", "Hello World From ITEM GROUP GET HANDLER").encodePrettily());
    }
}

package io.arvi.office.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class HttpServerVerticle extends AbstractVerticle {
    
    @Override
    public void start(Promise<Void> promise) throws Exception {
        
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        
        CompanyHandler companyHandler = new CompanyHandler();
        companyHandler.setRouter(vertx,router);
        InventoryHandler inventoryHandler = new InventoryHandler();
        inventoryHandler.setRouter(router);

        server
            .requestHandler(router)
            .listen(8080, ar -> {
                if (ar.succeeded()) {
                    promise.complete();
                } else {
                    promise.fail(ar.cause());
                }
            });
    }
    
}

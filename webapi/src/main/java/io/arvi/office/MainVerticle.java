package io.arvi.office;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) throws Exception {
        startHttpServer().setHandler(promise);
    }

    private Future<Void> startHttpServer() {
        Promise<Void> promise = Promise.promise();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/api*").handler(BodyHandler.create());
        router.get("/api/company").handler(this::companyGetHandler);

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

    private void companyGetHandler(RoutingContext context) {
        context.response()
            .putHeader("content-type", "text/html")
            .end("Hello World");
    }

    
}

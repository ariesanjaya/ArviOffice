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
        startHttpServer().onComplete(promise);
    }

    private Future<Void> startHttpServer() {
        Promise<Void> promise = Promise.promise();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.get("/api/company").handler(this::companyGetHandler);
        router.get("/api/branch").handler(this::branchGetHandler);
        router.post("/api/branch").handler(this::branchAddHandler);
        router.put("/api/branch").handler(this::branchUpdateHandler);
        router.delete("/api/branch").handler(this::branchDeleteHandler);
        

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
            .end("Hello Company GET");
    }

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
}

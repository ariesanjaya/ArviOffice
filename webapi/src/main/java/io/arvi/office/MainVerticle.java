package io.arvi.office;

import io.arvi.office.database.DatabaseVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) throws Exception {
        
        Promise<String> dbVerticleDeployment = Promise.promise();
        vertx.deployVerticle(new DatabaseVerticle(), dbVerticleDeployment);

        dbVerticleDeployment.future().compose(id -> {
            Promise<String> httpVerticleDeployment = Promise.promise();
            vertx.deployVerticle("io.arvi.office.http.HttpServerVerticle",
            new DeploymentOptions().setInstances(3), 
            httpVerticleDeployment);

            return httpVerticleDeployment.future();
        }).onComplete(ar -> {
            if (ar.succeeded()) {
                promise.complete();
            } else {
                promise.fail(ar.cause());
            }
        });
    }
}

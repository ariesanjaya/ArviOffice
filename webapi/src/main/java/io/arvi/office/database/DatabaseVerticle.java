package io.arvi.office.database;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class DatabaseVerticle extends AbstractVerticle {

    private PgPool client;

    @Override
    public void start(Promise<Void> promise) throws Exception {
        PgConnectOptions connectOptions = new PgConnectOptions()
        .setPort(5432)
        .setHost("localhost")
        .setDatabase("postgres")
        .setUser("postgres")
        .setPassword("admin");

        // Pool options
        PoolOptions poolOptions = new PoolOptions()
        .setMaxSize(10);

        // Create the client pool
        client = PgPool.pool(vertx, connectOptions, poolOptions);

        client.getConnection(ar -> {
            if (ar.succeeded()) {
                initializeDBService();
                promise.complete();        
            } else {
                promise.fail(ar.cause());
            }
        });
    }

    private void initializeDBService() {
        new CompanyDBService(vertx, client);
    }
}

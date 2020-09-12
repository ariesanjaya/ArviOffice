package io.arvi.office.database;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class DatabaseVerticle extends AbstractVerticle {
    
    @Override
    public void start(Promise<Void> promise) throws Exception {
        
        promise.complete();        
    }


}

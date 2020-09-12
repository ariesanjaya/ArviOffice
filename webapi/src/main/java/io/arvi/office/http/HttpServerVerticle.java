package io.arvi.office.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class HttpServerVerticle extends AbstractVerticle {
    
    @Override
    public void start(Promise<Void> promise) throws Exception {
        
        promise.complete();
    }
}

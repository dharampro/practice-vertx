package com.techendear.eventbus.reqresp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestVertical extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(RequestVertical.class);

    public static void main(String[] args) {
        Vertx vertex = Vertx.vertx();
        vertex.deployVerticle(RequestVertical.class.getName());
        vertex.deployVerticle(ResponseVertical.class.getName());
    }

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        LOG.info("Vertical started");
        startPromise.complete();
        var bus = vertx.eventBus();
        LOG.info("Bus setup {} message send", bus);
        bus.request("my.request.address", "Hello Bus1");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
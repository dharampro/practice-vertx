package com.techendear.eventbus.reqresp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseVertical extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RequestVertical.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        startPromise.complete();
        vertx.eventBus().consumer("my.request.address", msg-> {
            LOG.info("message received: {}", msg.body());
            msg.reply("Thanks message received");
        });
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
        super.stop(stopPromise);
    }
}

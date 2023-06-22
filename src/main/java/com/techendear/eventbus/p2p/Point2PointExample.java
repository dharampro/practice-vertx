package com.techendear.eventbus.p2p;

import com.techendear.eventbus.reqresp.RequestVertical;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Point2PointExample {

    private static final Logger LOG = LoggerFactory.getLogger(RequestVertical.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new PointOne());
        vertx.deployVerticle(new PointTwo());

    }

    static class PointOne extends AbstractVerticle {

        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            startPromise.complete();
            LOG.info("Message send from point one");
            vertx.setPeriodic(1000, id -> {
                LOG.info("Sending with ID: {}", id.toString());
                vertx.eventBus().send(PointOne.class.getName(), "Message from point one is Hello and id is : " + id);
            });
        }

        @Override
        public void stop(Promise<Void> stopPromise) throws Exception {
            stopPromise.complete();
            super.stop(stopPromise);
        }
    }

    static class PointTwo extends AbstractVerticle {
        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            vertx.eventBus().consumer(PointOne.class.getName(), msg -> {
                LOG.info("received message: {}", msg.body());
            });
        }

        @Override
        public void stop(Promise<Void> stopPromise) throws Exception {
            super.stop(stopPromise);
        }
    }
}


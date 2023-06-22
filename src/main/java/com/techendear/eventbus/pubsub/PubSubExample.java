package com.techendear.eventbus.pubsub;

import com.techendear.eventbus.reqresp.RequestVertical;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PubSubExample {

    private static final Logger LOG = LoggerFactory.getLogger(RequestVertical.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Publisher());
        vertx.deployVerticle(Subscriber.class.getName(), new DeploymentOptions().setInstances(3));
        vertx.deployVerticle(Subscriber2.class.getName(), new DeploymentOptions().setInstances(3));

    }

    public static class Publisher extends AbstractVerticle {

        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            startPromise.complete();
            LOG.info("Message send from point one");
            LOG.info("Sending");
            vertx.setPeriodic(Duration.ofSeconds(1).toMillis(), id -> {
                vertx.eventBus().publish(Publisher.class.getName(), "Message from point one is Hello");
            });
        }

        @Override
        public void stop(Promise<Void> stopPromise) throws Exception {
            stopPromise.complete();
            super.stop(stopPromise);
        }
    }

    public static class Subscriber extends AbstractVerticle {
        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            vertx.eventBus().consumer(Publisher.class.getName(), msg -> {
                LOG.info("received message Subscriber 1: {}", msg.body());
            });
        }

        @Override
        public void stop(Promise<Void> stopPromise) throws Exception {
            super.stop(stopPromise);
        }
    }

    public static class Subscriber2 extends AbstractVerticle {
        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            vertx.eventBus().consumer(Publisher.class.getName(), msg -> {
                LOG.info("received message Subscriber 2: {}", msg.body());
            });
        }

        @Override
        public void stop(Promise<Void> stopPromise) throws Exception {
            super.stop(stopPromise);
        }
    }
}


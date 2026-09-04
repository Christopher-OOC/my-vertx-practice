package com.javalord.verx_practice.web.api.dog.handler;

import com.javalord.verx_practice.web.api.model.Dog;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class GetDogHandler implements Handler<RoutingContext> {

  @Override
  public void handle(RoutingContext routingContext) {
    JsonArray dogs = new JsonArray(
      List.of(
        new Dog(1, "lucky", "dog"),
        new Dog(2, "smart", "dog")
      )
    );

    routingContext.response().putHeader("Content-Type", "application/json").end(dogs.toBuffer());
  }
}

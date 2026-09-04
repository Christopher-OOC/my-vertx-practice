package com.javalord.verx_practice.web.api.cat.handler;

import com.javalord.verx_practice.web.api.model.Cat;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class GetCatHandler implements Handler<RoutingContext> {

  @Override
  public void handle(RoutingContext routingContext) {
    JsonArray cats = new JsonArray(
      List.of(
        new Cat(1, "leon", "cat"),
        new Cat(2, "moon", "cat")
      )
    );

    routingContext.response().end(cats.toBuffer());
  }
}

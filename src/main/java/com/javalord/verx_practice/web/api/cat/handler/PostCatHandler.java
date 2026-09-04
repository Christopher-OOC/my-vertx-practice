package com.javalord.verx_practice.web.api.cat.handler;

import com.javalord.verx_practice.web.api.model.Cat;
import com.javalord.verx_practice.web.api.model.Pet;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PostCatHandler implements Handler<RoutingContext> {

  private List<Pet> pets;

  public PostCatHandler(List<Pet> pets) {
    this.pets = pets;
  }

  @Override
  public void handle(RoutingContext routingContext) {
    JsonObject requestBody = routingContext.body().asJsonObject();

    Cat newCat = new Cat(ThreadLocalRandom.current().nextInt(100), requestBody.getString("name"), "cat");
    pets.add(newCat);

    routingContext.json(pets);
  }
}

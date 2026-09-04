package com.javalord.verx_practice.web;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.Vertx;

public class WebMainVerticle extends VerticleBase {

  @Override
  public Future<?> start() throws Exception {
    return vertx.deployVerticle(RestApiVerticle.class.getName());
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WebMainVerticle())
      .onSuccess(id -> System.out.println("Main verticle deployed with ID: " + id))
      .onFailure(ex -> {
        System.out.println("Error: " + ex.getMessage());
        vertx.close();
      });
  }
}

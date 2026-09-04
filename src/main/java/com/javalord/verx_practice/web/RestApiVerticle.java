package com.javalord.verx_practice.web;

import com.javalord.verx_practice.web.api.cat.CatRestApi;
import com.javalord.verx_practice.web.api.dog.DogRestApi;
import com.javalord.verx_practice.web.api.model.Pet;
import com.javalord.verx_practice.web.config.ConfigLoader;
import com.javalord.verx_practice.web.config.ServerConfig;
import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.ArrayList;
import java.util.List;

public class RestApiVerticle extends VerticleBase {

  private List<Pet> pets = new ArrayList<>();

  @Override
  public Future<?> start() throws Exception {
    return ConfigLoader.load(vertx).onSuccess(c -> {
      System.out.println("SERVER CONFIG: " + c);
      startHttpServerAndConfiguredRoutes(c);
    }).onFailure(ex -> {
      System.out.println("Error: " + ex.getMessage());
      vertx.close();
    });
  }

  private void startHttpServerAndConfiguredRoutes(ServerConfig c) {
    Router restApi = Router.router(vertx);

    restApi.route()
      .handler(BodyHandler.create())
      .handler(ctx -> {
        ctx.response()
          .putHeader("Content-Type", "application/json");
        ctx.next();
      })
      .failureHandler(ctx -> {
        System.out.println("ERROR: " + ctx.failure().getMessage());
        ctx.failure().printStackTrace();
        ctx.response().end(new JsonObject().put("error", true).put("message", "error: " + ctx.failure().getMessage()).encode());
      });

    CatRestApi.attach(restApi, pets);
    DogRestApi.attach(restApi, pets);

    restApi.get("/pets").handler(ctx -> ctx.json(pets));

    vertx.createHttpServer()
      .requestHandler(restApi)
      .listen(8080)
      .onSuccess(s -> System.out.println("The server has started successfully!"))
      .onFailure(ex -> System.out.println("Error while starting server!"));
  }
}

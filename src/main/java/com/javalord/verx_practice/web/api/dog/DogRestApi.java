package com.javalord.verx_practice.web.api.dog;

import com.javalord.verx_practice.web.api.dog.handler.GetDogHandler;
import com.javalord.verx_practice.web.api.model.Pet;
import io.vertx.ext.web.Router;

import java.util.List;

public class DogRestApi {

  public static void attach(Router router, List<Pet> pets) {
    router.get("/dogs").handler(new GetDogHandler());
  }
}

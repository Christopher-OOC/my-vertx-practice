package com.javalord.verx_practice.web.api.cat;

import com.javalord.verx_practice.web.api.cat.handler.GetCatHandler;
import com.javalord.verx_practice.web.api.cat.handler.PostCatHandler;
import com.javalord.verx_practice.web.api.model.Pet;
import io.vertx.ext.web.Router;

import java.util.List;

public class CatRestApi {

  public static void attach(Router router, List<Pet> pets) {
    router.get("/cats").handler(new GetCatHandler());
    router.post("/cats").handler(new PostCatHandler(pets));
  }
}

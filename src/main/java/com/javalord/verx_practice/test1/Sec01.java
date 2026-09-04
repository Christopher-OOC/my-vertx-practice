package com.javalord.verx_practice.test1;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;

public class Sec01 extends VerticleBase {

  @Override
  public Future<?> start() throws Exception {
    System.out.println("Sec01 starting...");

    return Future.succeededFuture();
  }

}

package com.javalord.verx_practice.web.config;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ConfigLoader {

  public static Future<ServerConfig> load(Vertx vertx) {
    String profile = System.getenv().getOrDefault("APP_ENV", "prod");
    System.out.println("Vertx using profile: " + profile);
    String fileName = "application-" + profile + ".yml";

    ConfigStoreOptions yamlStoreCommon = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject().put("path", "application.yml"));

    ConfigStoreOptions yamlStoreProfile = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject().put("path", fileName));

    ConfigRetriever retriever = ConfigRetriever.create(
      vertx,
      new ConfigRetrieverOptions()
        .addStore(yamlStoreCommon)
        .addStore(yamlStoreProfile)
    );

    return retriever.getConfig().map(ServerConfig::from);
  }

}

package com.javalord.verx_practice.web.config;

import io.vertx.core.json.JsonObject;

public class ServerConfig {

  String version;
  private int port;
  private DbConfig dbConfig;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public DbConfig getDbConfig() {
    return dbConfig;
  }

  public void setDbConfig(DbConfig dbConfig) {
    this.dbConfig = dbConfig;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public static ServerConfig from(JsonObject config) {
    var serverConfigOptions = config.getJsonObject(ConfigProperties.SERVER.class.getSimpleName().toLowerCase());
    var dbConfigOptions = config.getJsonObject(ConfigProperties.DB.class.getSimpleName().toLowerCase());
    JsonObject appOptions = config.getJsonObject(ConfigProperties.APP.class.getSimpleName().toLowerCase());

    ServerConfig serverConfig = new ServerConfig();
    serverConfig.setPort(serverConfigOptions.getInteger(ConfigProperties.SERVER.PORT));
    serverConfig.setVersion(appOptions.getString(ConfigProperties.APP.VERSION));

    DbConfig dbConfig = new DbConfig();
    dbConfig.setName(dbConfigOptions.getString(ConfigProperties.DB.NAME));
    dbConfig.setPort(dbConfigOptions.getInteger(ConfigProperties.DB.PORT));
    dbConfig.setUsername(dbConfigOptions.getString(ConfigProperties.DB.USERNAME));
    dbConfig.setPassword(dbConfigOptions.getString(ConfigProperties.DB.PASSWORD));

    serverConfig.setDbConfig(dbConfig);

    return serverConfig;
  }

  @Override
  public String toString() {
    return "ServerConfig{" +
      "version=" + version +
      ", port=" + port +
      ", dbConfig=" + dbConfig +
      '}';
  }
}

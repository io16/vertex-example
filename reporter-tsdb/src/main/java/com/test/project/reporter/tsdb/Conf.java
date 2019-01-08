package com.test.project.reporter.tsdb;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import static java.lang.System.getenv;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Conf {
  private String tsdbHost;
  private Integer tsdbPort;
  private String tsdbUsername;
  private String tsdbPassword;
  private String tsdbDatabase;

  public String getTsdbHost() { return tsdbHost; }
  public Integer getTsdbPort() { return tsdbPort; }
  public String getTsdbUsername() { return tsdbUsername; }
  public String getTsdbPassword() { return tsdbPassword; }
  public String getTsdbDatabase() { return tsdbDatabase; }

  static class Module extends AbstractModule {
    @Provides @Singleton
    Conf conf() {
      var c = new Conf();
      c.tsdbHost =     requireNonNullElse(getenv("TAHOMETER_ACTIVITY_REPORTER_TSDB_HOST"), "localhost");
      c.tsdbPort =                  toInt(getenv("TAHOMETER_ACTIVITY_REPORTER_TSDB_PORT"), 5432);
      c.tsdbUsername = requireNonNullElse(getenv("TAHOMETER_ACTIVITY_REPORTER_TSDB_USERNAME"), "postgres");
      c.tsdbPassword = requireNonNullElse(getenv("TAHOMETER_ACTIVITY_REPORTER_TSDB_PASSWORD"), "postgres");
      c.tsdbDatabase = requireNonNullElse(getenv("TAHOMETER_ACTIVITY_REPORTER_TSDB_DATABASE"), "database");
      return c;
    }
  }
}

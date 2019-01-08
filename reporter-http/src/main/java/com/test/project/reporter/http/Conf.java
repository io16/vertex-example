package com.test.project.reporter.http;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Singleton;

import static java.lang.System.getenv;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Conf {
  private String httpHost;
  private Integer httpPort;

  public String getHttpHost() { return httpHost; }
  public Integer getHttpPort() { return httpPort; }

  static class Module extends AbstractModule {
    @Provides @Singleton
    Conf conf() {
      var c = new Conf();
      c.httpHost = requireNonNullElse(getenv("TAHOMETER_ACTIVITY_REPORTER_HTTP_HOST"), "0.0.0.0");
      c.httpPort =              toInt(getenv("TAHOMETER_ACTIVITY_REPORTER_HTTP_PORT"), 8083);
      return c;
    }
  }
}

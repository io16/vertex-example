package com.test.project.main;

import com.google.inject.Guice;
import com.test.project.reporter.ReporterVerticle;
import org.slf4j.LoggerFactory;

public class Main {
  public static void main(String[] args) {
    var log = LoggerFactory.getLogger(Main.class);

    var injector = Guice.createInjector(new _Module());
    var framework = injector.getInstance(Framework.class);

    var reporterVerticle = injector.getInstance(ReporterVerticle.class);
    framework.vertx().getDelegate().deployVerticle(reporterVerticle, ar -> {
      if (ar.failed())
        framework.vertx().close(cr -> {
          log.error("Could not deploy {}.", reporterVerticle.getClass().getName(), ar.cause());
          System.exit(101);
        });
    });
  }
}

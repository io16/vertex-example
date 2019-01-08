package com.test.project.reporter;

import com.test.project.reporter.http.HttpServerVerticleFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReporterVerticle extends AbstractVerticle {
  private final Logger log = LoggerFactory.getLogger(ReporterVerticle.class);

  @Inject
  HttpServerVerticleFactory httpServerVerticleFactory;

  @Override
  public void start(Future<Void> startFuture) {
    var options = new DeploymentOptions().setInstances(8); // TODO: envvar + some default like cores * 2 ?
    vertx.deployVerticle(httpServerVerticleFactory, options, dr -> {
      if (dr.failed())
        vertx.close(cr -> {
          log.error("Could not deploy HTTP server.", dr.cause());
          System.exit(101);
        });
    });
  }
}

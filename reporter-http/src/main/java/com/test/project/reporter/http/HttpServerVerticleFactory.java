package com.test.project.reporter.http;

import io.vertx.core.Verticle;
import io.vertx.reactivex.ext.web.Router;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.function.Supplier;

@Singleton
public class HttpServerVerticleFactory implements Supplier<Verticle> {
  @Inject Conf conf;
  @Inject @Named("reporterRouter") Router router;

  @Override
  public Verticle get() {
    return new HttpServerVerticle(conf, router);
  }
}

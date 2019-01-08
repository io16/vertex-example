package com.test.project.reporter.http;

import io.vertx.core.Future;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServerVerticle extends AbstractVerticle {
  private final Logger log = LoggerFactory.getLogger(HttpServerVerticle.class);

  Conf conf;
  Router router;

  public HttpServerVerticle(Conf conf, Router router) {
    this.conf = conf;
    this.router = router;
  }

  @Override
  public void start(Future<Void> startFuture) {
    var options = new HttpServerOptions()
        .setHost(conf.getHttpHost())
        .setPort(conf.getHttpPort())
        .setCompressionLevel(9)
        .setCompressionSupported(true);
    var server = vertx.createHttpServer(options);

    server.requestHandler(router::accept);

    server.rxListen().subscribe(
        s -> {
          log.info("HTTP server is listening on {}:{}.", conf.getHttpHost(), s.actualPort());
          startFuture.complete();
        },
        error -> {
          // TODO: some common exiting class?
        }
    );
  }
}

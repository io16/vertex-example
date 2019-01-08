package com.test.project.reporter.http.rawactivity;

import com.alibaba.fastjson.JSON;
import com.test.project.reporter.core.Reporter;
import com.test.project.reporter.http.Route;
import io.reactivex.Flowable;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.vertx.core.http.HttpMethod.POST;

public class HttpRequestHandler implements Route, Handler<RoutingContext> {
  private final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);

  @Inject
  Reporter reporter;

  @Override
  public void configure(Router router) {
    router.route(POST, "/api/v2/productivity/raw_activities.json")
        .handler(BodyHandler.create())
        .handler(this);
  }

  @Override
  public void handle(RoutingContext request) {
    Flowable
        .just(request)
        .map(RoutingContext::getBodyAsString)
        .map(s -> JSON.parseObject(s, HttpRequest.class))
        .map(ReportRequest::new)
        .flatMapSingle(reporter::reportRawActivity)
        .subscribe(
            report -> request.response().end(report.toJsonString()),
            error -> {
              log.error("Could not handle request.", error);
              request.response().setStatusCode(INTERNAL_SERVER_ERROR.code()).end();
            }
        );
  }
}

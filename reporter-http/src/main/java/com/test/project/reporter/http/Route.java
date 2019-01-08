package com.test.project.reporter.http;

import io.vertx.reactivex.ext.web.Router;

public interface Route {
  void configure(Router router);
}

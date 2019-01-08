package com.test.project.reporter.http.rawactivity;

import com.google.inject.AbstractModule;
import com.test.project.reporter.http.Route;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(Route.class).annotatedWith(RawActivity.class).to(HttpRequestHandler.class);
  }
}

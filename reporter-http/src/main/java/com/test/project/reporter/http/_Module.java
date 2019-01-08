package com.test.project.reporter.http;

import com.google.inject.AbstractModule;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    install(new com.test.project.reporter.http.rawactivity._Module());

    install(new Conf.Module());
    install(new Routing());
  }
}

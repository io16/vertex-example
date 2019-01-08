package com.test.project.reporter;

import com.google.inject.AbstractModule;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    install(new com.test.project.reporter.core._Module());
    install(new com.test.project.reporter.http._Module());
    install(new com.test.project.reporter.tsdb._Module());
  }
}

package com.test.project.main;

import com.google.inject.AbstractModule;
import com.test.project.util.Framework;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(Framework.class).to(com.test.project.main.Framework.class);

    install(new com.test.project.reporter._Module());
  }
}

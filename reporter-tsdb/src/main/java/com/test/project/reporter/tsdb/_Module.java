package com.test.project.reporter.tsdb;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.test.project.reporter.core.Storage;
import io.reactiverse.pgclient.PgPoolOptions;
import io.reactiverse.reactivex.pgclient.PgClient;
import io.reactiverse.reactivex.pgclient.PgPool;

import javax.inject.Named;
import javax.inject.Singleton;

public class _Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(Storage.class).to(TsdbStorage.class);

    install(new Conf.Module());
  }

  @Provides @Singleton @Named("reporterSqlClient")
  PgPool sqlClient(Conf conf) {
    PgPoolOptions options = new PgPoolOptions()
        .setHost(conf.getTsdbHost())
        .setPort(conf.getTsdbPort())
        .setUser(conf.getTsdbUsername())
        .setPassword(conf.getTsdbPassword())
        .setDatabase(conf.getTsdbDatabase())
        .setMaxSize(10)
        .setCachePreparedStatements(false);
    return PgClient.pool(options);
  }

}

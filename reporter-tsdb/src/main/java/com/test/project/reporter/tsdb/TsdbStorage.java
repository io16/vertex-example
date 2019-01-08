package com.test.project.reporter.tsdb;

import com.test.project.reporter.core.RawActivityReportRequest;
import com.test.project.reporter.core.Report;
import com.test.project.reporter.core.Storage;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class TsdbStorage implements Storage {
  @Inject @Named("reporterSqlClient") PgPool sql;

  @Override
  public Single<Report> getRawActivityReport(RawActivityReportRequest request) {
    var query = new SelectRawActivityBuilderSql()
        .withDomain(request.getDomainId())
        .withUserId(request.getUserId())
        .withProjectId(request.getProjectId())
        .withFrom(request.getFrom())
        .withTo(request.getTo())
        .build();
//    return Single.just(()->"");
    return sql.rxPreparedQuery(query)
        .map(rs -> () -> rs.iterator().next().getJson(0).toString());
  }
}

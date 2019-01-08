package com.test.project.reporter.core;

import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Reporter {
  @Inject Storage storage;

  public Single<Report> reportRawActivity(ReportRequest reportRequest) {
    var req = (RawActivityReportRequest) reportRequest;
    return storage.getRawActivityReport(req);
  }
}

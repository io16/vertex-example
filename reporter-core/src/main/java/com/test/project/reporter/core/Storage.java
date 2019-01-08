package com.test.project.reporter.core;

import io.reactivex.Single;

public interface Storage {
  Single<Report> getRawActivityReport(RawActivityReportRequest request);
}

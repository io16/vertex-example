package com.test.project.reporter.core;

import java.time.LocalDateTime;

public interface RawActivityReportRequest extends ReportRequest {
  LocalDateTime getFrom();
  LocalDateTime getTo();
  Integer getDomainId();
  Integer getUserId();
  Integer getProjectId();
}

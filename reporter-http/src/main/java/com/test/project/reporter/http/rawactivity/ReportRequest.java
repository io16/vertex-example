package com.test.project.reporter.http.rawactivity;

import com.test.project.reporter.core.RawActivityReportRequest;

import java.time.LocalDateTime;

public class ReportRequest implements RawActivityReportRequest {
  LocalDateTime from;
  LocalDateTime to;
  Integer domainId;
  Integer userId;
  Integer projectId;

  public ReportRequest(HttpRequest httpRequest) {
    this.domainId = httpRequest.domainId;
    this.userId = httpRequest.userId;
    this.from = httpRequest.period.fromDate;
    this.to = httpRequest.period.toDate;
    this.projectId = httpRequest.projectId;
  }

  @Override public LocalDateTime getFrom() { return from; }
  @Override public LocalDateTime getTo() { return to; }
  @Override public Integer getDomainId() { return domainId; }
  @Override public Integer getUserId() { return userId; }
  @Override public Integer getProjectId() { return projectId; }
}

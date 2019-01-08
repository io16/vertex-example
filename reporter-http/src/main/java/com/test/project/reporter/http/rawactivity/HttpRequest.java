package com.test.project.reporter.http.rawactivity;

import java.time.LocalDateTime;

public class HttpRequest {
  static class Period {
    LocalDateTime fromDate;
    LocalDateTime toDate;

    public LocalDateTime getFromDate() { return fromDate; }
    public void setFromDate(LocalDateTime fromDate) { this.fromDate = fromDate; }
    public LocalDateTime getToDate() { return toDate; }
    public void setToDate(LocalDateTime toDate) { this.toDate = toDate; }
  }

  Period period;
  Integer userId;
  Integer domainId;
  Integer projectId;

  public Period getPeriod() { return period; }
  public void setPeriod(Period period) { this.period = period; }
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }
  public Integer getDomainId() { return domainId; }
  public void setDomainId(Integer domainId) { this.domainId = domainId; }
  public Integer getProjectId() { return projectId; }
  public void setProjectId(Integer projectId) { this.projectId = projectId; }
}

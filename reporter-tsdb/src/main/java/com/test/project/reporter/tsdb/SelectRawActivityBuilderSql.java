package com.test.project.reporter.tsdb;

import com.test.project.util.es.AbstractQueryBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class SelectRawActivityBuilderSql extends AbstractQueryBuilder {
  private LocalDateTime fromDate;
  private LocalDateTime toDate;
  private Integer domainId;
  private Integer userId;
  private Integer projectId;

  public SelectRawActivityBuilderSql withFrom(LocalDateTime from) {
    this.fromDate = from;
    return this;
  }

  public SelectRawActivityBuilderSql withTo(LocalDateTime to) {
    this.toDate = to;
    return this;
  }

  public SelectRawActivityBuilderSql withDomain(Integer domainId) {
    this.domainId = domainId;
    return this;
  }

  public SelectRawActivityBuilderSql withUserId(Integer userId) {
    this.userId = userId;
    return this;
  }

  public SelectRawActivityBuilderSql withProjectId(Integer projectId) {
    this.projectId = projectId;
    return this;
  }

  public String build() {
    requireNonNull(fromDate);
    requireNonNull(toDate);
    requireNonNull(domainId);
    requireNonNull(userId);
    requireNonNull(projectId);

    Map<String, Object> model = new HashMap<>();
    model.put("FROM_DATE", fromDate.toString());
    model.put("TO_DATE", toDate.toString());
    model.put("DOMAIN_ACTIVITY_ID", "d" + domainId + "_activity_v3");
    model.put("USER_ID", userId);
    model.put("PROJECT_ID", projectId);

    return render(model);
  }
}

package com.test.project.util.es;

import com.test.project.util.es.AbstractQueryBuilder.NoTemplateFile;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AbstractQueryBuilderTest {
  @Test
  void should_error_if_template_file_not_found() {
    var b = new AbstractQueryBuilder() {};
    assertThrows(NoTemplateFile.class, b::loadTemplateString);
  }

  @Test
  void should_load_template_file_according_to_class_name() throws Exception {
    var b = new SingleQueryBuilderJson();
    var t = b.loadTemplateString();
    assertNotNull(t);
  }

  @Test
  void should_minimise_template() throws Exception {
    var b = new SingleQueryBuilderJson();
    var t = b.loadTemplateString();
    assertEquals("{  \"update\": {    \"_index\": \"{{indexName}}\",    \"_retry_on_conflict\": 3,    \"_type\": \"{{documentType}}\",    \"_id\": \"{{documentId}}\"  }}", t);
  }

  @Test
  void should_not_put_bulk_request_delimiters_if_there_are_no_placeholders_provided() throws Exception {
    var b = new NoBulkDelimitersQueryBuilderJson();
    var t = b.loadTemplateString();
    assertEquals("{\"script\": \"query1\"}{\"script\": \"query2\"}", t);
  }

  @Test
  void should_put_bulk_request_delimiters_if_there_are_placeholders() throws Exception {
    var b = new BulkQueryBuilderJson();
    var t = b.loadTemplateString();
    assertEquals("{\"script\": \"{{query1}}\"}\n{\"script\": \"{{ query2 }}\", \"count\": {{number1}}}\n", t);
  }

  @Test
  void should_insert_data_from_given_model() {
    var b = new BulkQueryBuilderJson();
    Map<String, Object> model = Map.of(
        "query1", "some query #1",
        "query2", "some query #2",
        "number1", 42
    );
    var q = b.render(model);
    assertEquals("{\"script\": \"some query #1\"}\n{\"script\": \"some query #2\", \"count\": 42}\n", q);
  }

  @Test
  void should_not_implicitly_escape_double_quote_in_model_values() {
    var b = new BulkQueryBuilderJson();
    Map<String, Object> model = Map.of(
        "query1", "some \"query #1\"",
        "query2", "display size: 25\"",
        "number1", 42
    );
    var q = b.render(model);
    assertEquals("{\"script\": \"some \"query #1\"\"}\n{\"script\": \"display size: 25\"\", \"count\": 42}\n", q);
  }

  @Test
  void should_escape_double_qoute_if_escape_function_is_used() {
    var b = new EscapeExampleQueryBuilderJson();
    Map<String, Object> model = Map.of(
        "projectName", "\"The best of the best\""
    );
    var q = b.render(model);
    assertEquals("{  \"some\": \"\\\"The best of the best\\\"\"}", q);
  }
}


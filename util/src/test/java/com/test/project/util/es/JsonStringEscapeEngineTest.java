package com.test.project.util.es;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonStringEscapeEngineTest {
  @Test
  void should_escape_single_double_qoute() {
    var input = "display size is 32\"";
    var output = new JsonStringEscapeEngine().escape(input);
    assertEquals("display size is 32\\\"", output);
  }

  @Test
  void should_escape_multiple_double_qoutes() {
    var input = "display \"size\" is 32\"";
    var output = new JsonStringEscapeEngine().escape(input);
    assertEquals("display \\\"size\\\" is 32\\\"", output);
  }
}

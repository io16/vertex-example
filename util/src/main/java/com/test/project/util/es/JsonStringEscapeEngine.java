package com.test.project.util.es;

import org.jtwig.escape.EscapeEngine;

public class JsonStringEscapeEngine implements EscapeEngine {
  @Override
  public String escape(String input) {
    return input.replaceAll("\"", "\\\\\"");
  }
}

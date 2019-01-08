package com.test.project.util.es;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.environment.EnvironmentConfigurationBuilder;

import java.util.Map;

public abstract class AbstractQueryBuilder {

  protected String render(Map<String, Object> model) {
    var t = getTemplate();
    var m = JtwigModel.newModel(model);
    return t.render(m);
  }

  // template engine configuration

  private static EnvironmentConfiguration conf = null;

  EnvironmentConfiguration getConf() {
    if (conf == null)
      synchronized (getClass()) {
        if (conf == null)
          conf = EnvironmentConfigurationBuilder
              .configuration()
                  .escape()
                      .withInitialEngine("none")
                      .withDefaultEngine("none")
                      .engines()
                          .add("json-string", new JsonStringEscapeEngine())
                      .and()
                  .and()
              .build();
      }
    return conf;
  }

  // template

  private JtwigTemplate template = null;

  JtwigTemplate getTemplate() {
    if (template == null)
      synchronized (this) {
        if (template == null)
          try {
            template = JtwigTemplate.inlineTemplate(loadTemplateString(), getConf());
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
      }
    return template;
  }

  String loadTemplateString() throws Exception {
    var templateFileName = this.getClass().getSimpleName().replaceAll("Builder", "");
    if(templateFileName.endsWith("Sql")){
      templateFileName = templateFileName.replaceAll("Sql$", "") + ".sql";
    } else if (templateFileName.endsWith("Json")) {
      templateFileName = templateFileName.replaceAll("Json$", "") + ".json";
    } else {
      templateFileName += ".resource";
    }
    var resource = this.getClass().getResource(templateFileName);
    if (resource == null)
      throw new NoTemplateFile();

    var template = Resources.toString(resource, Charsets.UTF_8);
    template = minimiseTemplate(template);
    template = placeBulkRequestDelimiters(template);

    return template;
  }

  private String minimiseTemplate(String lined) {
    return lined.replaceAll("\n", "");
  }

  private String placeBulkRequestDelimiters(String template) {
    return template.replaceAll("BULK_REQUEST_DELIMITER", "\n");
  }

  class NoTemplateFile extends RuntimeException {};
}

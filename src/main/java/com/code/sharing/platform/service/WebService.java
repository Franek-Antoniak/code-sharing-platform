package com.code.sharing.platform.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.code.sharing.platform.tools.FreeMakerConfigBean;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebService {

    @Autowired
    private FreeMakerConfigBean configBean;

    private Map<String, Object> getDataModel(String tag, Object bean) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put(tag, bean);
        return dataModel;
    }

    private Template getTemplateByName(String templateName) {
        Template template = null;
        try {
            template = configBean.getCfg().getTemplate(templateName);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Template %s not found. %s", templateName, e.getMessage()));
        }
        return template;
    }

    private String getOutputHtml(Map<String, Object> dataModel, Template template) {
        String htmlWithTemplate = "";
        try {
            Writer stringWriter = new StringWriter();
            template.process(dataModel, stringWriter);
            htmlWithTemplate = stringWriter.toString();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
        return htmlWithTemplate;
    }

    public <T> ResponseEntity<String> getResponseEntityHTML(String templateName, String parameterName, T object) {
        return ResponseEntity.ok(getOutputHtml
                (getDataModel(parameterName, object), getTemplateByName(templateName)));
    }

}

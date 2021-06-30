package com.code.sharing.platform.tools;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Locale;

@org.springframework.context.annotation.Configuration
public class FreeMakerConfigBean {

    @Bean
    public Configuration getCfg() {

        // Create your Configuration instance
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);

        // Specify the source where the template files come from.
        try {
            cfg.setDirectoryForTemplateLoading(new ClassPathResource("templates").getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(new Locale("es", "PL"));

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


        // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
        cfg.setLogTemplateExceptions(false);

        // Wrap unchecked exceptions thrown during template processing into TemplateException-s:
        cfg.setWrapUncheckedExceptions(true);

        // Do not fall back to higher scopes when reading a null loop variable:
        cfg.setFallbackOnNullLoopVariable(false);

        return cfg;
    }
}
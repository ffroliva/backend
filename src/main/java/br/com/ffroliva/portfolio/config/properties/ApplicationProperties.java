package br.com.ffroliva.portfolio.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Component
    @ConfigurationProperties()
    @PropertySource(value = "classpath:application.yml")
    public static class Documentation {

        @Value("${app-documentation.api-title}")
        public String title;

        @Value("${app-documentation.description}")
        public String description;

        @Value("${app-documentation.version}")
        public String version;
    }

        @Component
    @ConfigurationProperties()
    @PropertySource(value = "classpath:application.yml")
    public static class File {

        @Value("${file.upload-dir}")
        public String uploadDir;
    }

    private ApplicationProperties() {
    }
}

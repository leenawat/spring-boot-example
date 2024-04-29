package com.punyadev.springjaegertracing;

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
        logger.info("tracing.url : {}", url);
        return OtlpHttpSpanExporter.builder()
                .setEndpoint(url)
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.
                build();
    }
}

package com.punyadev.batch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {

    private String fileName;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String uploadUrl;
    private Integer chunkSize;
}
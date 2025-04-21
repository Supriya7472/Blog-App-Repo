package com.example.Blog_App.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Component
@Getter
@Setter
public class AppEnv {
    private String baseUrl;
    private Security security;
    private Domain domain;
    private Client client;


    @Getter
    @Setter
    public static class Security{
        private String secret;
        private TokenValidity tokenValidity;

        @Getter
        @Setter
        public static class TokenValidity{
            private Long accessValidity;
            private Long refreshValidity;
        }

    }
    @Getter
    @Setter
    public static class Domain{
        private String name;
        private boolean secure;
        private String sameSite;
    }

    @Getter
    @Setter
    public static class Client{
        private String origin;
    }
}


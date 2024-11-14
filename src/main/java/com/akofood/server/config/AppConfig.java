package com.akofood.server.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    String apiKey = "4464588121804225";
    String secretKey = "T2NtuGKwrAIUlghY1sYwLlRAI5BymGo6sDMrjsZNx0Qd54KZrsQu4RfG2yMoQ0Nbz4YgwSzAr2mhQxDO";

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }
}


package com.polytech.si5.al.dronedelivery.team.g.truck.configuration;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {

        var factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(Api.TIMEOUT);
        factory.setReadTimeout(Api.TIMEOUT);

        return new RestTemplate(factory);
    }
}

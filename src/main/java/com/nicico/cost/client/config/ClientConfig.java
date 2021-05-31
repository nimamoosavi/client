package com.nicico.cost.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableCircuitBreaker
@EnableRetry
public class ClientConfig {

    private static int maxAttempts;
    private static long backOffPeriod;

    @Value("${retry.maxAttempts:2}")
    public void setCMaxAttempts(int maxAttempts) {
        ClientConfig.maxAttempts = maxAttempts;
    }

    @Value("${retry.backOffPeriod:5000}")
    public void setBackOffPeriod(Long backOffPeriod) {
        ClientConfig.backOffPeriod = backOffPeriod;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(backOffPeriod);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(maxAttempts);
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }

}

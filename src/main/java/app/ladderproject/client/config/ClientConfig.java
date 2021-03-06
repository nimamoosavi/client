package app.ladderproject.client.config;

import app.ladderproject.core.utility.RequestUtility;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

import static app.ladderproject.core.config.general.GeneralStatic.AUTHORIZATION;


@Configuration
@EnableCircuitBreaker
@EnableRetry
@RequiredArgsConstructor
public class ClientConfig implements feign.RequestInterceptor {
    private final RequestUtility requestUtility;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = requestUtility.getHeader(AUTHORIZATION);
        requestTemplate.header("Authorization", token);
    }
}

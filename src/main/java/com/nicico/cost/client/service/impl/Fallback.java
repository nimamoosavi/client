package com.nicico.cost.client.service.impl;

import org.springframework.retry.annotation.Recover;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

public abstract class Fallback {


    @Recover
    public void fallbackResourceAccessException(ResourceAccessException e) {
    }

    @Recover
    public void fallbackHttpServerErrorException(HttpServerErrorException e) {
    }

    @Recover
    public void fallbackException(Exception e) {
    }
}

package com.nicico.cost.client.client.impl;

import com.nicico.cost.client.client.Client;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.packages.crud.view.Query;
import com.nicico.cost.framework.utility.RequestUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.nicico.cost.framework.service.GeneralResponse.successCustomResponse;

public abstract class ClientImpl<S, I extends Serializable> implements Client<S, I> {
    @Autowired
    public RequestUtility applicationRequest;

    public String pathUrl;

    public ClientImpl(String pathUrl) {
        this.pathUrl = pathUrl;
    }


    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    /*@HystrixCommand(fallbackMethod = "createFallBack",
            commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})*/
    public BaseDTO<String> create(@NotNull S s) {
        return successCustomResponse(applicationRequest.httpRequest(pathUrl, HttpMethod.POST, null, s, String.class).getBody());
    }


    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> update(S s, I id) {
        String url = pathUrl.concat("?id=").concat(id.toString());
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.POST, null, s, String.class).getBody());
    }

    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> deleteById(I id) {
        String url = pathUrl.concat("?id=").concat(id.toString());
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.DELETE, null, null, String.class).getBody());
    }


    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> findById(I id) {
        String url = pathUrl.concat("?id=").concat(id.toString());
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class).getBody());
    }


    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> existsById(I id) {
        String url = pathUrl.concat("/exists/ById?id=").concat(id.toString());
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class).getBody());

    }

    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> findAll() {
        String url = pathUrl.concat("/all");
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class).getBody());
    }

    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> findAll(Query query) {
        String url = pathUrl.concat("/all/query");
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.POST, null, query, String.class).getBody());
    }


    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> findAll(Integer page, Integer pageSize) {
        String url = pathUrl.concat("/all/pagination?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class).getBody());
    }


    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> findAll(Integer page, Integer pageSize, Query query) {
        String url = pathUrl.concat("/all/pagination/query?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.POST, null, query, String.class).getBody());

    }

    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> count() {
        String url = pathUrl.concat("/count");
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class).getBody());
    }

    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<String> count(Query query) {
        String url = pathUrl.concat("/count/query");
        return successCustomResponse(applicationRequest.httpRequest(url, HttpMethod.POST, null, query, String.class).getBody());
    }
}

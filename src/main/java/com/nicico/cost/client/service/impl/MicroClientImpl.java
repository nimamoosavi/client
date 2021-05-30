package com.nicico.cost.client.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nicico.cost.client.service.MicroClient;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.mapper.jackson.Mapper;
import com.nicico.cost.framework.utility.request.ApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class MicroClientImpl<S, R, I extends Serializable, U extends String> implements MicroClient<S, R, I, U> {
    private final ApplicationRequest applicationRequest;
    private final Mapper mapper;


    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<R> create(S s, U u) {
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(u, HttpMethod.POST, null, s, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<R>>() {
        });
    }


    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<R> update(S s, I id, U u) {
        String url = u.concat("?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.PUT, null, s, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<R>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<Boolean> deleteById(I id, U u) {
        String url = u.concat("?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.DELETE, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<Boolean>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<R> findById(I id, U u) {
        String url = u.concat("?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<R>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<Boolean> existsById(I id, U u) {
        String url = u.concat("/exists/ById?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<Boolean>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<List<R>> getAll(U u) {
        String url = u.concat("/all");
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<List<R>>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<PageDTO<List<R>>> findListByPagination(Integer page, Integer pageSize, U u) {
        String url = u.concat("/all/pagination?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<PageDTO<List<R>>> findListByPagination(Integer page, Integer pageSize, String orders, U u) {
        String url = u.concat("/all/pagination?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.POST, null, orders, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetail(Integer page, Integer pageSize, U u) {
        String url = u.concat("/all/pagination/detail?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetail(Integer page, Integer pageSize, String orders, U u) {
        String url = u.concat("/all/pagination/detail?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.POST, null, orders, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    public BaseDTO<Long> count(U u) {
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(u, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<Long>>() {
        });
    }
}

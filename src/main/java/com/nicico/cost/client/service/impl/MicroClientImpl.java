package com.nicico.cost.client.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nicico.cost.client.enums.ClientException;
import com.nicico.cost.client.service.MicroClient;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.mapper.jackson.Mapper;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.utility.request.ApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class MicroClientImpl<S, R, I extends Serializable, E extends RuntimeException, U extends String> implements MicroClient<S, R, I, E, U> {
    private final ApplicationRequest applicationRequest;
    private final ApplicationException<E> applicationException;
    private final Mapper mapper;


    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "createFallBack")
    public BaseDTO<R> create(S s, U u) {
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(u, HttpMethod.POST, null, s, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<R>>() {
        });
    }

    public BaseDTO<R> createFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }


    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "updateFallBack")
    public BaseDTO<R> update(S s, I id, U u) {
        String url = u.concat("?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.PUT, null, s, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<R>>() {
        });
    }

    public BaseDTO<R> updateFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "deleteByIdFallBack")
    public BaseDTO<Boolean> deleteById(I id, U u) {
        String url = u.concat("?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.DELETE, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<Boolean>>() {
        });
    }

    public BaseDTO<Boolean> deleteByIdFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "findByIdFallBack")
    public BaseDTO<R> findById(I id, U u) {
        String url = u.concat("?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<R>>() {
        });
    }

    public BaseDTO<R> findByIdFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "existsByIdFallBack")
    public BaseDTO<Boolean> existsById(I id, U u) {
        String url = u.concat("/exists/ById?id=").concat(id.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<Boolean>>() {
        });
    }

    public BaseDTO<Boolean> existsByIdFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "getAllFallBack")
    public BaseDTO<List<R>> getAll(U u) {
        String url = u.concat("/all");
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<List<R>>>() {
        });
    }

    public BaseDTO<List<R>> getAllFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "findListByPaginationFallBack")
    public BaseDTO<PageDTO<List<R>>> findListByPagination(Integer page, Integer pageSize, U u) {
        String url = u.concat("/all/pagination?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    public BaseDTO<PageDTO<List<R>>> findListByPaginationFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "findByPaginationFallBack")
    public BaseDTO<PageDTO<List<R>>> findListByPagination(Integer page, Integer pageSize, String orders, U u) {
        String url = u.concat("/all/pagination?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.POST, null, orders, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    public BaseDTO<PageDTO<List<R>>> findByPaginationFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "findByPaginationByDetailsFallBack")
    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetail(Integer page, Integer pageSize, U u) {
        String url = u.concat("/all/pagination/detail?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetailsFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "findByPaginationByDetailFallBack")
    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetail(Integer page, Integer pageSize, String orders, U u) {
        String url = u.concat("/all/pagination/detail?page=").concat(page.toString()).concat("&pageSize=").concat(pageSize.toString());
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(url, HttpMethod.POST, null, orders, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetailFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    @Retryable(value = {ResourceAccessException.class, HttpServerErrorException.class})
    @HystrixCommand(fallbackMethod = "countFallBack")
    public BaseDTO<Long> count(U u) {
        ResponseEntity<String> objectResponseEntity = applicationRequest.httpRequest(u, HttpMethod.GET, null, null, String.class);
        return mapper.jsonToObject(objectResponseEntity.getBody(), new TypeReference<BaseDTO<Long>>() {
        });
    }

    public BaseDTO<Long> countFallBack() {
        throw applicationException.createApplicationException(ClientException.CLIENT_EXCEPTION, HttpStatus.REQUEST_TIMEOUT);
    }
}

package app.ladderproject.client.client.impl;

import app.ladderproject.client.client.Client;
import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.domain.dto.PageDTO;
import app.ladderproject.core.packages.crud.view.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public abstract class ClientImpl<S, R, I extends Serializable> implements Client<S, R, I> {

    @Autowired
    public FeignClientImpl<S, R, I> feignClient;

    @Override
    public BaseDTO<R> create(@NotNull S s) {
        return feignClient.create(s).getBody();
    }

    @Override
    public BaseDTO<R> update(@NotNull S s) {
        return feignClient.update(s).getBody();
    }

    @Override
    public BaseDTO<Boolean> deleteById(I id) {
        return feignClient.deleteById(id).getBody();
    }

    @Override
    public BaseDTO<R> findById(I id) {
        return feignClient.findById(id).getBody();
    }

    @Override
    public BaseDTO<Boolean> existsById(I id) {
        return feignClient.existsById(id).getBody();
    }

    @Override
    public BaseDTO<List<R>> findAll() {
        return feignClient.findAll().getBody();
    }

    @Override
    public BaseDTO<List<R>> findAll(Query query) {
        return feignClient.findAll(query).getBody();
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findAll(@NotNull int page, @NotNull int pageSize) {
        return feignClient.findAll(page, pageSize).getBody();
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findAll(@NotNull int page, @NotNull int pageSize, Query query) {
        return feignClient.findAll(page, pageSize, query).getBody();
    }

    @Override
    public BaseDTO<Long> count() {
        return feignClient.count().getBody();
    }

    @Override
    public BaseDTO<Long> count(Query query) {
        return feignClient.count().getBody();
    }
}

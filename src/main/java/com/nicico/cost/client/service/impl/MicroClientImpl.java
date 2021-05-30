package com.nicico.cost.client.service.impl;

import com.nicico.cost.client.service.MicroClient;
import com.nicico.cost.crud.domain.dto.PageDTO;
import com.nicico.cost.framework.domain.dto.BaseDTO;

import java.io.Serializable;
import java.util.List;

public abstract class MicroClientImpl<S, R, I extends Serializable> implements MicroClient<S, R, I> {

    @Override
    public BaseDTO<R> create(S s) {
        return null;
    }

    @Override
    public BaseDTO<List<R>> saveAll(List<S> s) {
        return null;
    }

    @Override
    public BaseDTO<R> update(S s, I id) {
        return null;
    }

    @Override
    public BaseDTO<Boolean> deleteById(I id) {
        return null;
    }

    @Override
    public BaseDTO<R> findById(I id) {
        return null;
    }

    @Override
    public BaseDTO<Boolean> existsById(I id) {
        return null;
    }

    @Override
    public BaseDTO<List<R>> getAll() {
        return null;
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findListByPagination(int page, int pageSize) {
        return null;
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findListByPagination(int page, int pageSize, String orders) {
        return null;
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize) {
        return null;
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize, String orders) {
        return null;
    }

    @Override
    public BaseDTO<Long> count() {
        return null;
    }
}

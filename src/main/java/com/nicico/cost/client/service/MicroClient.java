package com.nicico.cost.client.service;

import com.nicico.cost.crud.domain.dto.PageDTO;
import com.nicico.cost.framework.domain.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public interface MicroClient<S, R, I extends Serializable> {
    BaseDTO<R> create(@NotNull S s);

    BaseDTO<List<R>> saveAll(List<S> sList);

    BaseDTO<R> update(@NotNull S s, @NotNull I id);

    BaseDTO<Boolean> deleteById(@NotNull I id);

    BaseDTO<R> findById(@NotNull I id);

    BaseDTO<Boolean> existsById(@NotNull I id);

    BaseDTO<List<R>> getAll();

    BaseDTO<PageDTO<List<R>>> findListByPagination(int page, int pageSize);

    BaseDTO<PageDTO<List<R>>> findListByPagination(int page, int pageSize, String orders);

    BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize);

    BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize, String orders);

    BaseDTO<Long> count();
}

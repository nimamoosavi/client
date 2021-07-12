package com.nicico.cost.client.client;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.packages.crud.view.Query;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public interface Client<S, I extends Serializable> {

    BaseDTO<String> create(@NotNull S s);

    BaseDTO<String> update(S s);

    BaseDTO<String> deleteById(I id);

    BaseDTO<String> findById(I id);

    BaseDTO<String> existsById(I id);

    BaseDTO<String> findAll();

    BaseDTO<String> findAll(Query query);

    BaseDTO<String> findAll(Integer page, Integer pageSize, Query query);

    BaseDTO<String> findAll(Integer page, Integer pageSize);

    BaseDTO<String> count();

    BaseDTO<String> count(Query query);
}

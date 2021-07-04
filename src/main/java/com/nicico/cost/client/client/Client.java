package com.nicico.cost.client.client;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.packages.crud.view.Sort;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public interface Client<S, I extends Serializable> {

    BaseDTO<String> create(@NotNull S s);

    BaseDTO<String> update(S s, I id);

    BaseDTO<String> deleteById(I id);

    BaseDTO<String> findById(I id);

    BaseDTO<String> existsById(I id);

    BaseDTO<String> findAll();

    BaseDTO<String> findAll(Integer page, Integer pageSize);

    BaseDTO<String> findAll(Integer page, Integer pageSize, List<Sort> sorts);

    BaseDTO<String> findAllWithTotal(Integer page, Integer pageSize);

    BaseDTO<String> findAllWithTotal(Integer page, Integer pageSize, List<Sort> sorts);

    BaseDTO<String> count();
}

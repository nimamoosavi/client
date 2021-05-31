package com.nicico.cost.client.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.nicico.cost.client.client.Client;
import com.nicico.cost.client.service.ClientService;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.mapper.jackson.Mapper;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @implNote @Log {@link com.nicico.cost.framework.anotations.Log} Used For Log But if you need to Used It you must add Audit Library to Your Project
 * @apiNote this class is BaseService that you can extended your Service Class and you must create bean of it
 */
public abstract class MicroClientService<S, R, I extends Serializable> implements ClientService<S, R, I> {

    @Autowired
    public Client<S, I> client;
    @Autowired
    public ApplicationException<ServiceException> applicationException;
    @Autowired
    private Mapper mapper;


    /**
     * @param s is the Request view Model that you can save it another microservices
     * @return the Response View Model that you must set in base class
     * @apiNote this method used for save data in another microservices
     */
    public BaseDTO<R> create(@NotNull S s) {
        String response = client.create(s).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<R>>() {
        });
    }


    /**
     * @param s  is the Request view Model that you can save it in Another Microservices
     * @param id is the incrementalId of in Another Microservices
     * @return the result of view Model
     * @apiNote this method used for update Another Microservices
     */
    public BaseDTO<R> update(@NotNull S s, @NotNull I id) {
        String response = client.update(s, id).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<R>>() {
        });
    }

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote this methode used for delete Data with the incremental Id
     */
    public BaseDTO<Boolean> deleteById(@NotNull I id) {
        String response = client.deleteById(id).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<Boolean>>() {
        });
    }


    /**
     * @param id is the incrementalId of Another Microservices
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from Another Microservices with the incremental Id of object
     */
    public BaseDTO<R> findById(@NotNull I id) {
        String response = client.findById(id).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<R>>() {
        });
    }

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote used for to know that this incremental Id is in Another Microservices Or Not
     */
    public BaseDTO<Boolean> existsById(@NotNull I id) {
        String response = client.existsById(id).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<Boolean>>() {
        });
    }

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote thi method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    public BaseDTO<List<R>> getAll() {
        String response = client.getAll().getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<List<R>>>() {
        });
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    public BaseDTO<PageDTO<List<R>>> findListByPagination(Integer page, Integer pageSize) {
        String response = client.findListByPagination(page, pageSize).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    public BaseDTO<PageDTO<List<R>>> findListByPagination(Integer page, Integer pageSize, String orders) {
        String response = client.findListByPagination(page, pageSize, orders).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }


    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetail(Integer page, Integer pageSize) {
        String response = client.findListByPagination(page, pageSize).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    public BaseDTO<PageDTO<List<R>>> findByPaginationByDetail(Integer page, Integer pageSize, String orders) {
        String response = client.findListByPagination(page, pageSize, orders).getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<PageDTO<List<R>>>>() {
        });
    }

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    public BaseDTO<Long> count() {
        String response = client.count().getData();
        return mapper.jsonToObject(response, new TypeReference<BaseDTO<Long>>() {
        });
    }


}

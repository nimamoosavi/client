package com.nicico.cost.client.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Query;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class used for call Another Microservices and Used Retry And Recover for Fall Back
 * you can override FallBack Methode for Your Response but by Default Throw Exception in FallBack Methode
 * {@link com.nicico.cost.client.client.Client}
 */
public interface ClientService<S, R, I extends Serializable> {

    /**
     * @param s is the Request view Model that you can save it another microservices
     * @return the Response View Model that you must set in base class
     * @apiNote this method used for save data in another microservices
     */
    BaseDTO<R> create(@NotNull S s);


    /**
     * @param s  is the Request view Model that you can save it in Another Microservices
     * @return the result of view Model
     * @apiNote this method used for update Another Microservices
     */
    BaseDTO<R> update(@NotNull S s);

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote this methode used for delete Data with the incremental Id
     */
    BaseDTO<Boolean> deleteById(@NotNull I id);

    /**
     * @param id is the incrementalId of Another Microservices
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from Another Microservices with the incremental Id of object
     */
    BaseDTO<R> findById(@NotNull I id);

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote used for to know that this incremental Id is in Another Microservices Or Not
     */
    BaseDTO<Boolean> existsById(@NotNull I id);


    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    BaseDTO<List<R>> findAll();

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    BaseDTO<List<R>> findAll(Query query);

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    BaseDTO<PageDTO<List<R>>> findAll(Integer page, Integer pageSize);

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param query   orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    BaseDTO<PageDTO<List<R>>> findAll(Integer page, Integer pageSize,  Query query);



    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    BaseDTO<Long> count();

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    BaseDTO<Long> count(Query query);
}

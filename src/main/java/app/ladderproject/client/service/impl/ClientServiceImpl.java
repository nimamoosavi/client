package app.ladderproject.client.service.impl;


import app.ladderproject.client.client.Client;
import app.ladderproject.client.service.ClientService;
import com.webold.framework.domain.dto.BaseDTO;
import com.webold.framework.domain.dto.PageDTO;
import com.webold.framework.anotations.Log;
import com.webold.framework.packages.crud.view.Query;
import com.webold.framework.service.exception.ApplicationException;
import com.webold.framework.service.exception.ServiceException;
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
 * @implNote @Log {@link Log} Used For Log But if you need to Used It you must add Audit Library to Your Project
 * @apiNote this class is BaseService that you can extended your Service Class and you must create bean of it
 */
public abstract class ClientServiceImpl<S, R, I extends Serializable> implements ClientService<S, R, I> {

    @Autowired
    public Client<S, R, I> client;
    @Autowired
    public ApplicationException<ServiceException> applicationException;


    /**
     * @param s is the Request view Model that you can save it another microservices
     * @return the Response View Model that you must set in base class
     * @apiNote this method used for save data in another microservices
     */
    public BaseDTO<R> create(@NotNull S s) {
        return client.create(s);
    }


    /**
     * @param s is the Request view Model that you can save it in Another Microservices
     * @return the result of view Model
     * @apiNote this method used for update Another Microservices
     */
    public BaseDTO<R> update(@NotNull S s) {
        return client.update(s);
    }

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote this methode used for delete Data with the incremental Id
     */
    public BaseDTO<Boolean> deleteById(@NotNull I id) {
        return client.deleteById(id);
    }


    /**
     * @param id is the incrementalId of Another Microservices
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from Another Microservices with the incremental Id of object
     */
    public BaseDTO<R> findById(@NotNull I id) {
        return client.findById(id);
    }

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote used for to know that this incremental Id is in Another Microservices Or Not
     */
    public BaseDTO<Boolean> existsById(@NotNull I id) {
        return client.existsById(id);
    }

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote thi method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    public BaseDTO<List<R>> findAll() {
        return client.findAll();
    }

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote thi method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    public BaseDTO<List<R>> findAll(Query query) {
        return client.findAll(query);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize) {
        return client.findAll(page, pageSize);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param query    orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, Query query) {
        return client.findAll(page, pageSize, query);
    }

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    public BaseDTO<Long> count() {
        return client.count();
    }

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    public BaseDTO<Long> count(Query query) {
        return client.count(query);
    }


}

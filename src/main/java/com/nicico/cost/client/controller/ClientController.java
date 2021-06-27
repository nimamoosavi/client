package com.nicico.cost.client.controller;

import com.nicico.cost.client.service.impl.MicroClientService;
import com.nicico.cost.framework.anotations.Log;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Sort;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

import static com.nicico.cost.framework.config.general.GeneralStatic.*;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class is baseController that you can extended your rest controller and you must create bean of it
 */
@Log
public abstract class ClientController<S, R, I extends Serializable> {

    @Autowired
    public MicroClientService<S, R, I> microClient;

    /**
     * @param s is the object of request model
     * @return ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
     * @apiNote this method save data to DataBase that you must implemented in repository layer
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping
    public ResponseEntity<BaseDTO<R>> create(@Valid @RequestBody S s) {
        return new ResponseEntity<>(microClient.create(s), HttpStatus.CREATED);
    }

    /**
     * @param s  is the object of request model
     * @param id is your IncrementalId of DataBase
     * @return ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
     * @apiNote this method save data to DataBase that you must implemented in repository layer
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PutMapping
    public ResponseEntity<BaseDTO<R>> update(@Valid @RequestBody S s, @Valid @RequestParam I id) {
        return new ResponseEntity<>(microClient.update(s, id), HttpStatus.OK);
    }

    /**
     * @param id is your IncrementalId of DataBase
     * @return ResponseEntity<BaseDTO < Boolean>> is the true or false result in BaseDTO pattern
     * @apiNote used for delete an entity from data base
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @DeleteMapping
    public ResponseEntity<BaseDTO<Boolean>> deleteById(@Valid @RequestParam I id) {
        return new ResponseEntity<>(microClient.deleteById(id), HttpStatus.OK);
    }

    /**
     * @param id is your IncrementalId of DataBase
     * @return ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
     * @apiNote this method used for get object from Identify number of data base
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping
    public ResponseEntity<BaseDTO<R>> findByID(@Valid @RequestParam I id) {
        return new ResponseEntity<>(microClient.findById(id), HttpStatus.OK);
    }


    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     * findListByPagination Or findByPagination for fetch data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/all")
    public ResponseEntity<BaseDTO<List<R>>> findAll() {
        return new ResponseEntity<>(microClient.getAll(), HttpStatus.OK);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/all/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findListByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize) {
        return new ResponseEntity<>(microClient.findListByPagination(page, pageSize), HttpStatus.OK);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   is the list of fields and your direction such as Asc and Desc for Sorting
     * @return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/all/pagination/sort")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAllByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize, @RequestBody List<Sort> orders) {
        return new ResponseEntity<>(microClient.findListByPagination(page, pageSize, orders), HttpStatus.OK);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/all/pagination-total")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize) {
        return new ResponseEntity<>(microClient.findListByPagination(page, pageSize), HttpStatus.OK);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   is the list of fields and your direction such as Asc and Desc for Sorting for Sorting
     * @return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/all/pagination-total/sort")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize, @RequestBody  List<Sort> orders) {
        return new ResponseEntity<>(microClient.findByPaginationByDetail(page, pageSize, orders), HttpStatus.OK);
    }

    /**
     * @param id is your IncrementalId of DataBase
     * @return the boolean of result
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/exists/ById")
    public ResponseEntity<BaseDTO<Boolean>> existsById(@Valid @RequestParam I id) {
        return new ResponseEntity<>(microClient.existsById(id), HttpStatus.OK);
    }

    /**
     * @return the number of objects
     * @apiNote this controller used for the count of data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/count")
    public ResponseEntity<BaseDTO<Long>> count() {
        return new ResponseEntity<>(microClient.count(), HttpStatus.OK);
    }

}
package com.nicico.cost.client.client.impl;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public interface FeignClientImpl<S, R, I extends Serializable> {

    /**
     * @param s is the Request view Model that you can save it another microservices
     * @return the Response View Model that you must set in base class
     * @apiNote this method used for save data in another microservices
     */
    @PostMapping
    ResponseEntity<BaseDTO<R>> create(@Valid @RequestBody S s);


    /**
     * @param s is the Request view Model that you can save it in Another Microservices
     * @return the result of view Model
     * @apiNote this method used for update Another Microservices
     */
    @PutMapping
    ResponseEntity<BaseDTO<R>> update(@Valid @RequestBody S s);

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote this methode used for delete Data with the incremental Id
     */
    @DeleteMapping
    ResponseEntity<BaseDTO<Boolean>> deleteById(@Valid @RequestParam I id);

    /**
     * @param id is the incrementalId of Another Microservices
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from Another Microservices with the incremental Id of object
     */
    @GetMapping
    ResponseEntity<BaseDTO<R>> findById(@Valid @RequestParam I id);

    /**
     * @param id is the incrementalId of Another Microservices
     * @return the result such as true or false
     * @apiNote used for to know that this incremental Id is in Another Microservices Or Not
     */
    @GetMapping("/exists/ById")
    ResponseEntity<BaseDTO<Boolean>> existsById(@Valid @RequestParam I id);


    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    @GetMapping("/all")
    ResponseEntity<BaseDTO<List<R>>> findAll();

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    @PostMapping("/all/query")
    ResponseEntity<BaseDTO<List<R>>> findAll(@RequestBody Query query);

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    @GetMapping("/all/pagination")
    ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAll(@RequestParam Integer page, @RequestParam Integer pageSize);

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param query    orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    @PostMapping("/all/pagination/query")
    ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAll(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestBody Query query);


    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    @GetMapping("/count")
    ResponseEntity<BaseDTO<Long>> count();

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    @PostMapping("/count/query")
    ResponseEntity<BaseDTO<Long>> count(@RequestBody Query query);
}

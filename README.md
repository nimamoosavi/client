### this library used for aggregation and api gateWay that implement the base Controller methode for your client
you can know more than in client library

### Requirements
The library works with Java 8+, ladder framework 1.0.1+

## [Core](https://github.com/nimamoosavi/core/wiki)

### Structure

[[/images/client.jpg |"Micro client Diagram"]]

this project create default Micro Client and used mapstruct library for casting object

[BaseController]

[GeneralService]

[GeneralMapper]

[client]



### Maven Central
~~~
<dependency>
            <groupId>app.ladderproject</groupId>
            <artifactId>micro-client</artifactId>
            <version>0.0.1-SNAPSHOT</version>
</dependency>
~~~

### BaseController

a simple abstract implementation of REST controller that support CRUD operations, and which you can use in your application

## method

@PostMapping

public ResponseEntity<BaseDTO<R>> create(@Valid @RequestBody S s)
> s is the object of request model
>
> return ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
>
> this method save data to DataBase that you must implement in repository layer

@PutMapping

public ResponseEntity<BaseDTO<R>> update(@Valid @RequestBody S s, @Valid @RequestParam I id)
>s  is the object of request model
>
> id is your IncrementalId of DataBase
>
> ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
>
> apiNote this method save data to DataBase that you must implement in repository layer

@DeleteMapping

public ResponseEntity<BaseDTO<Boolean>> deleteById(@Valid @RequestParam I id)
> id is your IncrementalId of DataBase
>
> ResponseEntity<BaseDTO < Boolean>> is the true or false result in BaseDTO pattern
>
> used for delete an entity from data base

@GetMapping

public ResponseEntity<BaseDTO<R>> findByID(@Valid @RequestParam I id)
>id is your IncrementalId of DataBase
>
> ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
>
> this method used for get object from Identify number of data base

@GetMapping(value = "/all")

public ResponseEntity<BaseDTO<List<R>>> findAll()
> @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
>
> used for getAll data from data base , you must know that the cost of this method is high and you can used findListByPagination Or findByPagination for fetch data


@GetMapping(value = "/all/pagination")

public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findListByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize)
> page is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it

@PostMapping(value = "/all/pagination")

public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findListByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize, @RequestBody String orders)
>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> orders   is the list of fields and your direction such as Asc and Desc for Sorting
>
> return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it

@GetMapping(value = "/all/pagination/detail")

public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize)

>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it


@PostMapping(value = "/all/pagination/detail")

public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize, @RequestBody String orders)

>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> orders   is the list of fields and your direction such as Asc and Desc for Sorting
>
> return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it

@GetMapping(value = "/exists/ById")

public ResponseEntity<BaseDTO<Boolean>> existsById(@Valid @RequestParam I id)

>id is your IncrementalId of DataBase
>
> return the Boolean of result

@GetMapping(value = "/count")

public ResponseEntity<BaseDTO<Long>> count()
>return the number of objects
>
> apiNote this controller used for the count of data


### Example
~~~
@RestController
@RequestMapping(value = "/rest/integration/v1/affair")
@Api(value = "Integration", protocols = "HTTP")
@SwaggerDefinition(tags = {@Tag(name = "Integration", description = "سرویس مدیریت اطلاعات")})
@RequiredArgsConstructor
public class AffairController extends BaseController<Affair, AffairReqVM, AffairResVM, Long> {
}
~~~

### GeneralService
a simple abstract implementation of Service that support CRUD operations, and which you can use in your application

#### method

BaseDTO< R > save(@NotNull S s)
> s is the Request view Model that you can save it in Data Base
>
> return the Response View Model that you must set in base class
>
> this method used for save in Data Base

BaseDTO< List< R > > saveAll(List< S > sList)
> sList is the list of Request view Model that you can save it in Data Base
>
> return the list of Response of view model
>
> this method used for save batch in Data base

BaseDTO< R > update(@NotNull S s, @NotNull I id)
> s  is the Request view Model that you can save it in Data Base
>
> id is the incrementalId of dataBase
>
> return the result of view Model
>
> this method used for update the Data

BaseDTO< Boolean > deleteById(@NotNull I id)
> id is the incrementalId of dataBase
>
> return the result such as true or false
>
> this methode used for delete Data with the incrementalId

BaseDTO<R> findById(@NotNull I id)

>id is the incrementalId of dataBase
>
> BaseDTO< R > is the result of find that you can give it the Response View Model
>
> this method used for fetch data from dataBase with the incrementalId of object

BaseDTO< Boolean > existsById(@NotNull I id)
> id is the incrementalId of dataBase
>
> return the result such as true or false
>
> used for to know that this incrementalId is in Data Base Or Not

BaseDTO< List< R > > getAll()
>return BaseDTO< List < R > > the list of response view model Data
>
> this method used for get all data from dataBse that you must know that the cost of this method is very expensive
you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination

BaseDTO< PageDTO < List < R >>> findListByPagination(int page, int pageSize)
> page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it


BaseDTO<PageDTO<List<R>>> findListByPagination(int page, int pageSize, String orders)
> page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> orders is the list of fields and your direction such as Asc and Desc
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it


BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize)
>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
>
> this method call count method and return the count of data

BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize, String orders)
>
> page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> orders are the list of fields and your direction such as Asc and Desc
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
>
> this method call count method and return the count of data

BaseDTO<Long> count()
>
> return the number of data
>
> this method used for count of data objects

### Example

~~~
@Service
public class AffairService extends GeneralService<Affair, AffairReqVM, AffairResVM, Long> {
}
~~~

### GeneralMapper

Mappers are used to convert DTOs to entities and vice versa. This is done automatically thanks to MapStruct framework

>this class used for map compile time and used is the protocol that implement by mapStruct library version 1.3.1.Final
> you must create interface and extended it you must create interface and extended it

### methode

public abstract R requestToBaseObject(S source);
> source is the source of request view model
>
> return the R is the Object
>
> method used for cast request to BaseObject

public abstract S toRequestModel(R target)
> target the BaseObject of object
>
> return the Request view model

public abstract List< R > requestToBaseObject(List< S > sourceList)
> sourceList the list of Request view Model
>
> method used for cast List Response View Model to List BaseObject

public abstract List< S > toRequestModel(List< R > targetList)
> targetList the list of Response View Model
>
> return the List Of Request View Model
>
> method used for cast List Response View Model to List Request View Model

public abstract List< S > toRequestModels(List< R > targetList)
> targetList the List Of BaseObject
>
> return the List of Response View Model
>
> method used for cast Iterable Of BaseObject to List Of Request View Model

public abstract R responseToBaseObject(R source)
> source the Response View Model

public abstract R toResponseModel(R target)
>target the BaseObject Object
>
> return the Response View Model
>
> method used for cast BaseObject to Response

public abstract List< R > responseToBaseObject(List< R > sourceList)
>sourceList the List Of Response View Model
>
> return the list Of BaseObject
>
> used for cast Iterable of Response to List Of BaseObject

public abstract List< R >  toResponseModel(List< R > targetList)
>targetList the List of BaseObject
>
> return the List Of Response View Model
>
> used for cast to Iterable of BaseObject to List Of Response

## Example

~~~
@Mapper(componentModel = "spring")
@Component
public abstract class AffairMapper extends GeneralMapper<Affair, AffairReqVM, AffairResVM, Long> {
}
~~~

### client

this class used for connect to another microservice that extended from Base Controller

BaseDTO< R > create(@NotNull S s)
> s is the Request view Model that you can save it another microservices
>
> return the Response View Model that you must set in base class
>
> this method used for save data in another microservices

BaseDTO< R > update(@NotNull S s, @NotNull I id)
>s  is the Request view Model that you can save it in Another Microservices
>
> id is the incrementalId of in Another Microservices
>
> return the result of view Model
>
> this method used for update Another Microservices

BaseDTO< Boolean > deleteById(@NotNull I id)
>id is the incrementalId of Another Microservices
>
> return the result such as true or false
>
> this methode used for delete Data with the incrementalId

BaseDTO< R > findById(@NotNull I id)
>id is the incrementalId of Another Microservices
>
> BaseDTO< R > is the result of find that you can give it the Response View Model
>
> this method used for fetch data from Another Microservices with the incrementalId of object

BaseDTO< Boolean > existsById(@NotNull I id)
>id is the incrementalId of Another Microservices
>
> return the result such as true or false
>
> used for to know that this incrementalId is in Another Microservices Or Not

BaseDTO< List< R > > getAll()
>return BaseDTO<List < R>> the list of response view model Data
>
> this method used for get all data from Another Microservices that you must know that the cost of this method is very expensive
you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination

BaseDTO<PageDTO< List< R >> > findListByPagination(Integer page, Integer pageSize)
>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it

BaseDTO<PageDTO<List<R>>> findListByPagination(Integer page, Integer pageSize, String orders)
>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> orders   orders is the list of fields and your direction such as Asc and Desc
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it

BaseDTO<PageDTO<List<R>>> findByPaginationByDetail(Integer page, Integer pageSize)
>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
>
> this method call count method and return the count of data

BaseDTO< PageDTO< List < R >>> findByPaginationByDetail(Integer page, Integer pageSize, String orders)
>page     is the number of page you need to fetch
>
> pageSize is the sizable page of data
>
> orders is the list of fields and your direction such as Asc and Desc
>
> return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
>
> this method call count method and return the count of data

BaseDTO< Long > count()
>return the number of data
>
> this method used for count of data objects

### Config
this config set default but you can change it by Your Application Properties

>retry.maxAttempts:2
>
> retry.backOffPeriod:5000


[BaseController]: #basecontroller
[GeneralService]: #generalservice
[GeneralMapper]: #generalmapper
[client]: #client

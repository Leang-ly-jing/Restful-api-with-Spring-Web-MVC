package co.istad.spring_rest_api.controller;


import co.istad.spring_rest_api.dto.CoffeeResponse;
import co.istad.spring_rest_api.dto.CreateCoffeeRequest;
import co.istad.spring_rest_api.dto.UpdateCoffeeRequest;
import co.istad.spring_rest_api.service.CoffeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<CoffeeResponse> getCoffee(){
        return coffeeService.getCoffee();

    }
    @GetMapping("/{id}")
    public CoffeeResponse getCoffeeById(@PathVariable Integer id){
        log.info("GET id : {}",id);
        return coffeeService.getCoffeeById(id);
    }

    @GetMapping("/search")
    public List<CoffeeResponse> searchCoffees(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") Double price
    ){
        log.info("GET search name: {}", name);
        log.info("GET search price: {}", price);
        return coffeeService.getCoffeeByNameOrPrice(name,price);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CoffeeResponse createCoffee(@Valid @RequestBody CreateCoffeeRequest createCoffeeRequest){
        log.info("CREATE : {}", createCoffeeRequest);
        return coffeeService.createCoffee(createCoffeeRequest);
    }

    @PutMapping("/{id}")
    public CoffeeResponse updateCoffeeById(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCoffeeRequest updateCoffeeRequest
            ){
        return coffeeService.updateCoffeeById(id,updateCoffeeRequest);
    }

    @DeleteMapping("/{id}")
    public CoffeeResponse deleteCoffeeById(@PathVariable Integer id){
        return coffeeService.deleteCoffeeById(id);

    }
}

package co.istad.spring_rest_api.controller;


import co.istad.spring_rest_api.dto.CoffeeResponse;
import co.istad.spring_rest_api.service.CoffeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}

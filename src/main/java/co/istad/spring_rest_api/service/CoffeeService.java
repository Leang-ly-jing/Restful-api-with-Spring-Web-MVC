package co.istad.spring_rest_api.service;

import co.istad.spring_rest_api.dto.CoffeeResponse;
import co.istad.spring_rest_api.dto.CreateCoffeeRequest;
import co.istad.spring_rest_api.dto.UpdateCoffeeRequest;

import java.util.List;

public interface CoffeeService {

    CoffeeResponse deleteCoffeeById(Integer id);

    CoffeeResponse updateCoffeeById(Integer id,UpdateCoffeeRequest updateCoffeeRequest);

    CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest);

    List<CoffeeResponse> getCoffee();

    CoffeeResponse getCoffeeById(Integer id);

    List<CoffeeResponse> getCoffeeByNameOrPrice(String name, Double price);
}

package co.istad.spring_rest_api.service;

import co.istad.spring_rest_api.dto.CoffeeResponse;
import co.istad.spring_rest_api.dto.CreateCoffeeRequest;
import java.util.List;

public interface CoffeeService {



    CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest);

    List<CoffeeResponse> getCoffee();

    CoffeeResponse getCoffeeById(Integer id);

    List<CoffeeResponse> getCoffeeByNameOrPrice(String name, Double price);
}

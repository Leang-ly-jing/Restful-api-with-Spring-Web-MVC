package co.istad.spring_rest_api.service;

import co.istad.spring_rest_api.domain.Coffee;
import co.istad.spring_rest_api.dto.CoffeeResponse;
import co.istad.spring_rest_api.repository.CoffeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CoffeeServiceImpl implements CoffeeService{

    private final CoffeeRepository coffeeRepository;

    public CoffeeServiceImpl(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<CoffeeResponse> getCoffee() {
        List<Coffee> listCoffee = coffeeRepository.beanCoffee();
        return listCoffee.stream()
                .map(coffee -> new CoffeeResponse(coffee.getName(),coffee.getDescription()))
                .toList();

    }
}

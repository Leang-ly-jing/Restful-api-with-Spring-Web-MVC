package co.istad.spring_rest_api.repository;

import co.istad.spring_rest_api.domain.Coffee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeRepository {


    public List<Coffee> beanCoffee(){
        Coffee coffee = new Coffee(1, "Ice latte", "50%");
        Coffee coffee1 = new Coffee(2, "Ice Americano", "50%");
        Coffee coffee2 = new Coffee(3, "Hot latte", "50%");
        return List.of(coffee,coffee1,coffee2);
    }

}


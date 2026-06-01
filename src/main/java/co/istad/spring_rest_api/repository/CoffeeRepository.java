package co.istad.spring_rest_api.repository;

import co.istad.spring_rest_api.domain.Coffee;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CoffeeRepository {

    private final List<Coffee> coffees;
    public CoffeeRepository(){
        this.coffees = new ArrayList<>();

        Coffee coffee = new Coffee(1, "Ice latte", "50%",1.5);
        Coffee coffee1 = new Coffee(2, "Ice Americano", "50%",1.25);
        Coffee coffee2 = new Coffee(3, "Hot latte", "50%",1.25);
        coffees.add(coffee);
        coffees.add(coffee1);
        coffees.add(coffee2);
    }

    public List<Coffee> getCoffees(){
        return coffees;
    }

}


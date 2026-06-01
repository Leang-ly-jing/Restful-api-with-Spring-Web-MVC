package co.istad.spring_rest_api.service;

import co.istad.spring_rest_api.domain.Coffee;
import co.istad.spring_rest_api.dto.CoffeeResponse;
import co.istad.spring_rest_api.dto.CreateCoffeeRequest;
import co.istad.spring_rest_api.repository.CoffeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class CoffeeServiceImpl implements CoffeeService{

    private final CoffeeRepository coffeeRepository;

    public CoffeeServiceImpl(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest) {
        Coffee coffee = new Coffee();
        coffee.setId(new Random().nextInt());
        coffee.setName(createCoffeeRequest.name());
        coffee.setDescription(createCoffeeRequest.description());
        coffee.setPrice(createCoffeeRequest.price());


        coffeeRepository.getCoffees().add(coffee);
        return new CoffeeResponse(createCoffeeRequest.name(),createCoffeeRequest.description(), createCoffeeRequest.price());
    }

    @Override
    public List<CoffeeResponse> getCoffee() {
        List<Coffee> listCoffee = coffeeRepository.getCoffees();
        return listCoffee.stream()
                .map(coffee -> new CoffeeResponse(coffee.getName(),coffee.getDescription(),coffee.getPrice()))
                .toList();
    }

   @Override
    public CoffeeResponse getCoffeeById(Integer id){
        return coffeeRepository.getCoffees().stream().filter(c -> c.getId().equals(id))
                .map(coffee -> new CoffeeResponse(coffee.getName(),coffee.getDescription(),coffee.getPrice()))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Error to get Coffee by Id!!!!!!!!"));
   }

   @Override
    public List<CoffeeResponse> getCoffeeByNameOrPrice(String name,Double price){

       return coffeeRepository.getCoffees().stream()
               .filter(c -> (name.isBlank() || c.getName().toLowerCase().contains(name.toLowerCase().trim())) && (price == null || c.getPrice() < price))
               .map(coffee -> new CoffeeResponse(coffee.getName(),coffee.getDescription(),coffee.getPrice()))
               .toList();

   }
}

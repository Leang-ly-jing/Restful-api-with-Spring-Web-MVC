package co.istad.spring_rest_api.service;

import co.istad.spring_rest_api.domain.Coffee;
import co.istad.spring_rest_api.dto.CoffeeResponse;
import co.istad.spring_rest_api.dto.CreateCoffeeRequest;
import co.istad.spring_rest_api.dto.UpdateCoffeeRequest;
import co.istad.spring_rest_api.repository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@Service
public class CoffeeServiceImpl implements CoffeeService{

    private final CoffeeRepository coffeeRepository;

    public CoffeeServiceImpl(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public CoffeeResponse deleteCoffeeById(Integer id) {
        return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst()
                .map(coffee -> {
                    coffeeRepository.getCoffees().remove(coffee);
                    return new CoffeeResponse(coffee.getName(), coffee.getDescription(), coffee.getPrice());
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Coffee id = %d not found", id)
                ));
    }



    @Override
    public CoffeeResponse updateCoffeeById(Integer id,UpdateCoffeeRequest updateCoffeeRequest) {

        //validation coffee id not exist
        return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst()
                .map(oldCoffee -> {
                    oldCoffee.setName(updateCoffeeRequest.name());
                    oldCoffee.setDescription(updateCoffeeRequest.description());
                    oldCoffee.setPrice(updateCoffeeRequest.price());
                    return oldCoffee;
                })
                .map(newCoffee ->new CoffeeResponse(newCoffee.getName(),newCoffee.getDescription(),newCoffee.getPrice()) )
                .orElseThrow(()->new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Coffee with id %d not found",id)));
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

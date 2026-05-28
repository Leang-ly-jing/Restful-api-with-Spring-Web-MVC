package co.istad.spring_rest_api.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    private Integer id;
    private String name;
    private String description;
    private Double price;

}

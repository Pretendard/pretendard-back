package sclass.pretendard.dish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishRequest {
    private String name;
    private List<DishData.Topping> toppings;
}

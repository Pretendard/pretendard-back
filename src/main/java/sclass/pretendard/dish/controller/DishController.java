package sclass.pretendard.dish.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sclass.pretendard.dish.domain.Dish;
import sclass.pretendard.dish.dto.DishData;
import sclass.pretendard.dish.dto.OrderDishRequest;
import sclass.pretendard.dish.service.DishService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    @GetMapping
    public List<Dish> getDish() {
        return dishService.getDish();
    }

    @PostMapping
    public Dish saveDish(@RequestBody DishData dishRequest) {
        return dishService.saveDish(dishRequest);
    }

    @PostMapping("/order")
    public void orderDish(@RequestBody List<OrderDishRequest> orderDishRequest) {
        dishService.orderDish(orderDishRequest);
    }

}

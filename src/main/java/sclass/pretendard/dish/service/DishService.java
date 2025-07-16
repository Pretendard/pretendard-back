package sclass.pretendard.dish.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sclass.pretendard.dish.domain.Dish;
import sclass.pretendard.dish.domain.DishRepository;
import sclass.pretendard.dish.dto.DishData;
import sclass.pretendard.dish.dto.OrderDishRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> getDish() {
        List<Dish> dishes = dishRepository.findAll();
        return dishes;
    }

    public Dish saveDish(DishData dishRequest) {
        Dish dish = dishRepository.save(
                Dish.builder()
                        .dishData(dishRequest)
                .build()
        );
        return dish;
    }

    public void orderDish(List<OrderDishRequest> orderDishRequest) {
        for( OrderDishRequest request : orderDishRequest) {
            log.info("주문한 메뉴 : " + request.getName());
            log.info("추가한 토핑 : " + request.getToppings());
        }
    }
}

package sclass.pretendard.dish.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sclass.pretendard.dish.domain.Dish;
import sclass.pretendard.dish.domain.DishRepository;
import sclass.pretendard.dish.dto.DishData;
import sclass.pretendard.dish.dto.OrderDishRequest;
import sclass.pretendard.s3.service.S3Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishService {

    private final DishRepository dishRepository;
    private final S3Service s3Service;

    public List<Dish> getDish() {
        List<Dish> dishes = dishRepository.findAll();
        return dishes;
    }

    public Dish saveDish(DishData dishRequest, MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("이미지 파일이 비어있습니다.");
        }

        String imageUrl = s3Service.uploadImage(image.getOriginalFilename(), image.getBytes());

        DishData updatedDishRequest = DishData.builder()
                .name(dishRequest.getName())
                .description(dishRequest.getDescription())
                .image(imageUrl)
                .ingredients(dishRequest.getIngredients())
                .price(dishRequest.getPrice())
                .toppings(dishRequest.getToppings())
                .tag(dishRequest.getTag())
                .type(dishRequest.getType())
                .build();

        Dish dish = dishRepository.save(
                Dish.builder()
                        .dishData(updatedDishRequest)
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

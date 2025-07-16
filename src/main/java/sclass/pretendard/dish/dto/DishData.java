package sclass.pretendard.dish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishData {
    private String name;
    private String description;
    private String image;
    private List<Ingredient> ingredients;
    private Integer price;
    private List<Topping> toppings;
    private List<Tag> tag;
    private String type;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Ingredient {
        private String name;
        private String from;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Topping {
        private String name;
        private Integer price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Tag {
        private boolean hot;

        @JsonProperty("new")
        private boolean _new;

        private boolean picked;
    }
}

package com.knb.calocalws.food;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDto {
    private String code;
    private String name;
    private Double energy;
    private Double protein;
    private Double carbs;
    private Double fats;
    private Integer[] categoryIdArray;
}

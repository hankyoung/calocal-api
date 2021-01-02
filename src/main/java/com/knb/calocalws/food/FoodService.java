package com.knb.calocalws.food;

import com.knb.calocalws.common.BaseResponse;

import java.util.List;
import java.util.Optional;

public interface FoodService {
    List<Food> findAll();

    Optional<Food> findById(Integer foodId);

    Food createFood(FoodDto foodDto, BaseResponse responseBean);

    Food updateFood(Food food, FoodDto foodDto);
}

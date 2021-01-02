package com.knb.calocalws.food;

import com.knb.calocalws.common.BaseResponse;
import com.knb.calocalws.common.EndPoint;
import com.knb.calocalws.common.ResultMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(EndPoint.API_PREFIX)
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping(EndPoint.API_FOOD + "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Welcome to Calocal Webservice", HttpStatus.OK);
    }

    @GetMapping(EndPoint.API_FOOD + "/foods")
    public ResponseEntity<BaseResponse> getAllFoods() {
        List<Food> foods = foodService.findAll();
        return ResponseEntity.ok(new BaseResponse(new ResultMap("foods", foods).getMap(), "Success"));
    }

    @PostMapping(EndPoint.API_FOOD + "/foods")
    public ResponseEntity<BaseResponse> saveFood(@RequestBody FoodDto foodDto) {
        BaseResponse responseBean = new BaseResponse();
        Food food = foodService.createFood(foodDto, responseBean);
        if (food == null) {
            return ResponseEntity.ok(responseBean);
        }
        return ResponseEntity.ok(new BaseResponse(new ResultMap("food", food).getMap(), "Success"));
    }

    @GetMapping(EndPoint.API_FOOD + "/foods/{foodId}")
    public ResponseEntity<BaseResponse> getFoodById(@PathVariable Integer foodId) {
        Optional<Food> food = foodService.findById(foodId);
        return ResponseEntity.ok(new BaseResponse(new ResultMap("food", food), "Success"));
    }

    @PatchMapping(EndPoint.API_FOOD + "/foods/{foodId}")
    public ResponseEntity getFoodById(@PathVariable Integer foodId, @RequestBody FoodDto foodDto) {
        Optional<Food> food = foodService.findById(foodId);
        if (!food.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Food updatedFood = foodService.updateFood(food.get(), foodDto);
        return ResponseEntity.ok(new BaseResponse(new ResultMap("food", updatedFood), "Success"));
    }
}

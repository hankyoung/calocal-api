package com.knb.calocalws.food;

import com.knb.calocalws.category.Category;
import com.knb.calocalws.category.CategoryService;
import com.knb.calocalws.category.FoodFoodCategory;
import com.knb.calocalws.common.BaseResponse;
import com.knb.calocalws.common.MessageByLocaleService;
import com.knb.calocalws.common.ResponseCode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.knb.calocalws.common.Constants.*;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final MessageByLocaleService messageService;
    private final CategoryService categoryService;

    public FoodServiceImpl(FoodRepository foodRepository, MessageByLocaleService messageService,
                           CategoryService categoryService) {
        this.foodRepository = foodRepository;
        this.messageService = messageService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public Optional<Food> findById(Integer foodId) {
        return foodRepository.findById(foodId);
    }

    @Transactional
    @Override
    public Food createFood(FoodDto foodDto, BaseResponse responseBean) {
        if (foodDto.getCategoryIdArray() == null || foodDto.getCategoryIdArray().length == 0) {
            responseBean.setErrorCode(ResponseCode.BAD_REQUEST);
            responseBean.setMessage(messageService.getMessage(
                    "error.nonnull", new Object[]{messageService.getMessage("category")}
            ));
            return null;
        }

        Food food = new Food(foodDto);
        Arrays.stream(foodDto.getCategoryIdArray()).forEach(categoryId -> {
            Optional<Category> category = categoryService.findById(categoryId);
            if (category.isPresent()) {
                FoodFoodCategory foodFoodCategory = new FoodFoodCategory(food, category.get(), STATUS_ACTIVE);
                food.getFoodFoodCategoryList().add(foodFoodCategory);
            }
        });

        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(Food food, FoodDto foodDto) {
        food.updateFood(foodDto);
        return foodRepository.save(food);
    }
}

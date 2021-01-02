package com.knb.calocalws.category;

import com.knb.calocalws.common.BaseResponse;
import com.knb.calocalws.common.EndPoint;
import com.knb.calocalws.common.ResultMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoint.API_PREFIX)
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(EndPoint.API_CATEGORY + "/categories")
    public ResponseEntity<BaseResponse> getAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        return ResponseEntity.ok(new BaseResponse(new ResultMap("categories", categoryList).getMap(), "Success"));
    }

    @PostMapping(EndPoint.API_CATEGORY + "/categories")
    public ResponseEntity<BaseResponse> saveCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(new BaseResponse(new ResultMap("category", category).getMap(), "Success"));
    }

    @GetMapping(EndPoint.API_CATEGORY + "/categories/{id}")
    public ResponseEntity<BaseResponse> getCategoryById(@PathVariable Integer id) {
        Optional<Category> category = categoryService.findById(id);
        return ResponseEntity.ok(new BaseResponse(new ResultMap("category", category).getMap(), "Success"));
    }
}

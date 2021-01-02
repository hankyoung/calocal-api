package com.knb.calocalws.category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Category createCategory(CategoryDto categoryDto);

    Optional<Category> findById(Integer id);
}

package com.knb.calocalws.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name = "food_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<FoodFoodCategory> foodFoodCategoryList = new ArrayList<>();

    public Category(CategoryDto categoryDto) {
        code = categoryDto.getCode();
        name = categoryDto.getName();
        description = categoryDto.getDescription();
    }
}

package com.knb.calocalws.food;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.knb.calocalws.category.FoodFoodCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "energy")
    private Double energy;
    @Column(name = "protein")
    private Double protein;
    @Column(name = "carbs")
    private Double carbs;
    @Column(name = "fats")
    private Double fats;

    @JsonManagedReference
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodFoodCategory> foodFoodCategoryList = new ArrayList<>();

    public Food(FoodDto foodDto) {
        code = foodDto.getCode();
        name = foodDto.getName();
        energy = foodDto.getEnergy();
        protein = foodDto.getProtein();
        carbs = foodDto.getCarbs();
        fats = foodDto.getFats();
    }

    public void updateFood(FoodDto foodDto) {
        if (StringUtils.hasText(foodDto.getName())) {
            name = foodDto.getName();
        }
        if (foodDto.getEnergy() != null) {
            energy = foodDto.getEnergy();
        }
        if (foodDto.getProtein() != null) {
            protein = foodDto.getProtein();
        }
        if (foodDto.getCarbs() != null) {
            carbs = foodDto.getCarbs();
        }
        if (foodDto.getFats() != null) {
            fats = foodDto.getFats();
        }
    }
}

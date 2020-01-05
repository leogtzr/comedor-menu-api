package com.comedor.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Food {
    private String name;
    private int kCal;
    private MealType mealType;
}

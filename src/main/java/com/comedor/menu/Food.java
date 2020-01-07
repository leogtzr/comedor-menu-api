package com.comedor.menu;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public final class Food {
    private String name;
    private int kCal;
    private MealType mealType;
}

package com.comedor.input.sheet;

import com.comedor.menu.MealType;
import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RowTypeIndex {
    private MealType mealType;
    private int index;
}

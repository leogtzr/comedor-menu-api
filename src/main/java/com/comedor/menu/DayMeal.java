package com.comedor.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class DayMeal {
    private Food breakfast;
    private List<Food> lunchDinnerFoodAlternatives;
    private List<String> salads;
}

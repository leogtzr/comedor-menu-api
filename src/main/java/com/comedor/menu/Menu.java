package com.comedor.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Menu {
    private String title;
    private Map<Day, DayMeal> menu;
}

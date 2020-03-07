# comedor-menu-api

## How to use the api:
```java
File file = new File(path);
String absolutePath = file.getAbsolutePath();
Workbook workbook = WorkbookFactory.create(new File(absolutePath));
Sheet sheet = workbook.getSheetAt(0);
```

## To extract a from a XLXs sheet
```java
Optional<Menu> menu = MenuUtils.extractMenuFromSheet(sheet);
menu.ifPresent(m -> {
    // ...
});
```

## Getting day menu
```java
DayMeal menuMonday = menu.getMenu().get(Day.MONDAY);
List<String> mondaySalads = menuMonday.getSalads();
Food mondayBreakfast = menuMonday.getBreakfast();

// Getting all the food alternatives:
List<Food> foodAlternatives = menuMonday.getLunchDinnerFoodAlternatives();
```

### Food class
```java
public final class Food {
    private String name;
    private int kCal;
    private MealType mealType;
}
```

### DayMenu class
````java
public final class DayMeal {
    private Food breakfast;
    private List<Food> lunchDinnerFoodAlternatives;
    private List<String> salads;
}
````

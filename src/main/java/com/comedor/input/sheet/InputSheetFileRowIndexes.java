package com.comedor.input.sheet;

import com.comedor.menu.MealType;

import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class InputSheetFileRowIndexes {

    private int titleRowPosition = 4;
    private EnumMap<MealType, Integer> indexes = null;

    public InputSheetFileRowIndexes() {
        this.indexes = new EnumMap<>(MealType.class);
    }

    public Set<RowTypeIndex> values() {
        return new LinkedHashSet<>(
            List.of(
                    new RowTypeIndex(MealType.TITLE, this.titleRowPosition),
                    new RowTypeIndex(MealType.BREAKFAST, this.titleRowPosition + 2),
                    new RowTypeIndex(MealType.MAIN1, this.titleRowPosition + 4),
                    new RowTypeIndex(MealType.MAIN2, this.titleRowPosition + 5),
                    new RowTypeIndex(MealType.ANTOJITO, this.titleRowPosition + 6),
                    new RowTypeIndex(MealType.SIDE1, this.titleRowPosition + 8),
                    new RowTypeIndex(MealType.SIDE2, this.titleRowPosition + 9),
                    new RowTypeIndex(MealType.SOUP_OR_CREAM, this.titleRowPosition + 10),
                    new RowTypeIndex(MealType.DESSERT, this.titleRowPosition + 11),
                    new RowTypeIndex(MealType.LIGHT, this.titleRowPosition + 12),
                    new RowTypeIndex(MealType.SALADS, this.titleRowPosition + 13)
            )
        );
    }

    public RowTypeIndex title() {
        return new RowTypeIndex(MealType.TITLE, this.titleRowPosition);
    }

    public RowTypeIndex breakfast() {
        return new RowTypeIndex(MealType.BREAKFAST, this.titleRowPosition + 2);
    }

    public RowTypeIndex main1() {
        return new RowTypeIndex(MealType.MAIN1, this.titleRowPosition + 4);
    }

    public RowTypeIndex main2() {
        return new RowTypeIndex(MealType.MAIN2, this.titleRowPosition + 5);
    }

    public RowTypeIndex antojito() {
        return new RowTypeIndex(MealType.ANTOJITO, this.titleRowPosition + 6);
    }

    public RowTypeIndex side1() {
        return new RowTypeIndex(MealType.SIDE1, this.titleRowPosition + 8);
    }

    public RowTypeIndex side2() {
        return new RowTypeIndex(MealType.SIDE2, this.titleRowPosition + 9);
    }

    public RowTypeIndex soupOrCream() {
        return new RowTypeIndex(MealType.SOUP_OR_CREAM, this.titleRowPosition + 10);
    }

    public RowTypeIndex dessert() {
        return new RowTypeIndex(MealType.DESSERT, this.titleRowPosition + 11);
    }

    public RowTypeIndex light() {
        return new RowTypeIndex(MealType.LIGHT, this.titleRowPosition + 12);
    }

    public RowTypeIndex salads() {
        return new RowTypeIndex(MealType.SALADS, this.titleRowPosition + 13);
    }

    public void adjust(final int titleRowPosition) {
        this.titleRowPosition = titleRowPosition;
    }

}

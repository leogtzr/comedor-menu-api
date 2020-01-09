package com.comedor.input.sheet;

import com.comedor.menu.Day;
import lombok.ToString;

@ToString
public enum DayColumnIndexes {
    MONDAY(2, Day.MONDAY),
    TUESDAY(4, Day.TUESDAY),
    WEDNESDAY(6, Day.WEDNESDAY),
    THURSDAY(8, Day.THURSDAY),
    FRIDAY(10, Day.FRIDAY),
    SATURDAY(12, Day.SATURDAY),
    SUNDAY(14, Day.SUNDAY)
    ;

    private final int index;
    private final Day day;

    DayColumnIndexes(final int index, final Day day) {
        this.index = index;
        this.day = day;
    }

    public int index() {
        return this.index;
    }

    public Day day() {
        return this.day;
    }
}

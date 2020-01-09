package com.comedor.input.sheet;

import com.comedor.menu.Day;
import lombok.ToString;

@ToString
public enum DayColumnIndexes {
    LUNES(2, Day.MONDAY),
    MARTES(4, Day.TUESDAY),
    MIERCOLES(6, Day.WEDNESDAY),
    JUEVES(8, Day.THURSDAY),
    VIERNES(10, Day.FRIDAY),
    SABADO(12, Day.SATURDAY),
    DOMINGO(14, Day.SUNDAY)
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

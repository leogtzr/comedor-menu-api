package com.comedor.input.sheet;

import com.comedor.menu.Day;
import lombok.ToString;

@ToString
public enum DayColumnIndexes {
    LUNES(2, Day.LUNES),
    MARTES(4, Day.MARTES),
    MIERCOLES(6, Day.MIERCOLES),
    JUEVES(8, Day.JUEVES),
    VIERNES(10, Day.VIERNES),
    SABADO(12, Day.SABADO),
    DOMINGO(14, Day.DOMINGO)
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

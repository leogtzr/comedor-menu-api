package com.comedor.input.sheet;

public enum LunchDinnerDayColumnIndexes {
    LUNES(2),
    MARTES(4),
    MIERCOLES(6),
    JUEVES(8),
    VIERNES(10),
    SABADO(12),
    DOMINGO(14)
    ;

    private final int index;

    LunchDinnerDayColumnIndexes(final int index) {
        this.index = index;
    }

    public int get() {
        return this.index;
    }
}

package com.comedor.input.sheet;

public class InputSheetFileRowIndexes {

    private int titleRowPosition = 4;

    public InputSheetFileRowIndexes() {
    }

    public int title() {
        return this.titleRowPosition;
    }

    public int breakfast() {
        return this.titleRowPosition + 2;
    }

    public int main1() {
        return this.titleRowPosition + 4;
    }

    public int main2() {
        return this.titleRowPosition + 5;
    }

    public int antojito() {
        return this.titleRowPosition + 6;
    }

    public int side1() {
        return this.titleRowPosition + 8;
    }

    public int side2() {
        return this.titleRowPosition + 9;
    }

    public int soupOrCream() {
        return this.titleRowPosition + 10;
    }

    public int dessert() {
        return this.titleRowPosition + 11;
    }

    public int light() {
        return this.titleRowPosition + 12;
    }

    public int salads() {
        return this.titleRowPosition + 13;
    }

    public void adjust(final int titleRowPosition) {
        this.titleRowPosition = titleRowPosition;
    }

}

package com.comedor.input.sheet;

public enum InputSheetFileRowIndexes {
    TITLE(4) {
        @Override
        public int get() {
            return TITLE.index;
        }

        @Override
        public void adjust(final int index) {
            TITLE.index = index;
        }
    },
    BREAKFAST(6) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.index + 2;
        }
    },
    MAIN1(TITLE.get() + 4) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 4;
        }
    },
    MAIN2(TITLE.get() + 5) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 5;
        }
    },
    ANTOJITO(TITLE.get() + 6) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 6;
        }
    },
    SIDE1(TITLE.get() + 8) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 8;
        }
    },
    SIDE2(TITLE.get() + 9) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 9;
        }
    },
    SOUP_OR_CREAM(TITLE.get() + 10) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 10;
        }
    },
    DESSERT(TITLE.get() + 11) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 11;
        }
    },
    LIGHT(TITLE.get() + 12) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 12;
        }
    },
    SALADS(TITLE.get() + 13) {
        @Override public void adjust(int index) { }

        @Override
        public int get() {
            return TITLE.get() + 13;
        }
    }
    ;
    private int index;

    InputSheetFileRowIndexes(int index) {
        this.index = index;
    }

    public abstract void adjust(int index);
    public abstract int get();
}

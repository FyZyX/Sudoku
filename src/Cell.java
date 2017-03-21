class Cell {
    private int value;

    Cell(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    boolean isSolved() {
        return value != 0;
    }

    void clear() {
        value = 0;
    }

    /**
     * Tests if the cell contains the specified value
     *
     * @param value is the value to be tested
     * @return true if the specified value matches the value contained in the cell
     */
    boolean equals(int value) {
        return this.value == value;
    }
}

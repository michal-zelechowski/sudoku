package com.luxoft.interview.ubs.sudoku;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Quadrant implements Unit {

    private final int baseRow;
    private final int baseColumn;
    private final Content content;

    public Quadrant(int index, Content content) {
        this.baseRow = 3 * (index / 3);
        this.baseColumn = 3 * (index % 3);
        this.content = content;
    }

    @Override
    public Set<Integer> getUniqueValues() {
        return IntStream.of(0, 1, 2)
                .parallel()
                .flatMap(row -> IntStream.of(0, 1, 2)
                        .parallel()
                        .map(column -> this.content.get(baseRow + row, baseColumn + column)))
                .mapToObj(i -> i)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "quadrant at base row " + baseRow + " base column " + baseColumn;
    }
}

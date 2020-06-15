package com.luxoft.interview.ubs.sudoku;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Row implements Unit {
    private final int index;
    private final Content content;

    public Row(int index, Content content) {
        this.index = index;
        this.content = content;
    }

    @Override
    public Set<Integer> getUniqueValues() {
        return IntStream.range(0, 9)
                .parallel()
                .mapToObj(columns -> content.get(index, columns))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "row " + index;
    }
}

package com.luxoft.interview.ubs.sudoku;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Column implements Unit {

    private final int index;
    private final Content content;

    public Column(int index, Content content) {
        this.index = index;
        this.content = content;
    }

    @Override
    public Set<Integer> getUniqueValues() {
        return IntStream.range(0,9)
                .parallel()
                .mapToObj(row -> content.get(row, index))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "column " + index;
    }
}

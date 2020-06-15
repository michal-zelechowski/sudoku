package com.luxoft.interview.ubs.sudoku;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Board implements Content {
    private final Integer[][] content = new Integer[9][];

    public Board(List<String> rows) {
        int i = 0;
        if (rows.size() != 9) {
            throw new IllegalArgumentException("Expecting 9 rows, but found " + rows.size());
        }
        for (String row : rows) {
            content[i] = Stream.of(row.split(","))
                    .map(StringUtils::trim)
                    .map(Board::mapCellValueToInteger)
                    .toArray(Integer[]::new);
            if (content[i].length != 9) {
                throw new IllegalArgumentException(
                        String.format("Expecting 9 columns, but found %d in row %d", rows.size(), i)
                );
            }
            i++;
        }
    }

    private static Integer mapCellValueToInteger(String cellValue) {
        final Integer result = Integer.parseInt(cellValue);
        if (result < 1 || result > 9) {
            throw new IllegalArgumentException("Cell value must be between range 1 and 9, but found " + result);
        }
        return result;
    }

    public Stream<Row> getRows() {
        return IntStream.range(0, 9).mapToObj(r -> new Row(r, this));
    }

    public Stream<Column> getColumns() {
        return IntStream.range(0, 9).mapToObj(c -> new Column(c, this));
    }

    public Stream<Quadrant> getQuadrants() {
        return IntStream.range(0, 9).mapToObj(q -> new Quadrant(q, this));
    }

    @Override
    public Integer get(int row, int column) {
        return this.content[row][column];
    }

}

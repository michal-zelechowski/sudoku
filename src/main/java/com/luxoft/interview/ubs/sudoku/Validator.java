package com.luxoft.interview.ubs.sudoku;

import com.google.common.io.Resources;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.luxoft.interview.ubs.sudoku.ValidationResult.*;

public class Validator {
    public ValidationResult validate(String inputFile) {
        try {
            return this.validate(new File(inputFile).toURI().toURL());
        } catch (MalformedURLException e) {
            return incorrect(Code.READ_ERROR, e.getMessage());
        }
    }

    public ValidationResult validate(URL url) {
        if (url == null)
            return incorrect(Code.READ_ERROR, "Missing input");

        try {
            final List<String> lines = Resources.readLines(url, Charset.forName("UTF-8"));
            final Board board = new Board(lines);
            return Stream.of(board.getRows(), board.getColumns(), board.getQuadrants())
                    .parallel()
                    .flatMap(Function.identity())
                    .map(unit -> Pair.of(unit.toString(), isUnitCorrect(unit)))
                    .filter(unitCorrectness -> !unitCorrectness.getValue())
                    .map(unitCorrectness -> unitCorrectness.getKey())
                    .findAny()
                    .map(unitDescription -> incorrect(Code.INCORRECT_SOLUTION, "Incorrect solution at " + unitDescription))
                    .orElseGet(() -> correct());
        } catch (FileNotFoundException e) {
            return incorrect(Code.READ_ERROR, "Missing input");
        } catch (IOException e) {
            return incorrect(Code.READ_ERROR, "Error reading input: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return incorrect(Code.MALFORMED_INPUT, e.getMessage());
        } catch (RuntimeException e) {
            return incorrect(Code.UNEXPECTED_ERROR, e.getMessage());
        }
    }

    private boolean isUnitCorrect(Unit unit) {
        return unit.getUniqueValues().size() == 9;
    }
}

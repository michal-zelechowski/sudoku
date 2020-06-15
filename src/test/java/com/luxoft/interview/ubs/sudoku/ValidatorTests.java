package com.luxoft.interview.ubs.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTests {

    @Test
    public void testIfMissingInputFails() {
        //given
        Validator objectUnderTest = new Validator();
        //when
        ValidationResult result = objectUnderTest.validate(getClass().getResource("non_existing.file"));
        //then
        assertThat(result.getCode()).isEqualTo(1);
        assertThat(result.getMessage()).isEqualTo("Missing input");
    }

    @ParameterizedTest
    @CsvSource({"/full_board_correct.txt,0",
            "/full_board_incorrect.txt,2",
            "/empty.txt,3",
            "/full_board_incorrect_column.txt,2",
            "/full_board_incorrect_quadrant.txt,2",
            "/full_board_incorrect_row.txt,2",
            "/invalid_numbers.txt,3",
            "/missing_column.txt,3",
            "/missing_row.txt,3",
            "/missing_values.txt,3",
            "/numbers_out_of_range.txt,3",
            "/too_few_values.txt,3",
            "/with_spaces.txt,0"
    })
    public void testBoardsFromResource(String resource, String output) {
        //given
        Validator objectUnderTest = new Validator();
        //when
        ValidationResult result = objectUnderTest.validate(getClass().getResource(resource));
        //then
        assertThat(result.getCode().toString()).describedAs(result.getMessage()).isEqualTo(output);
    }
}

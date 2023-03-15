package com.huddle.huddle.book.shop.domain.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PercentageTest {

  @Test
  void given_Valid_Percentage_Integer_Then_Map_To_Percentage() {
    // prepare && execute
    Percentage percentage = Percentage.of(84);

    // verify
    assertThat(percentage.percentage()).isEqualTo(0.84);
    assertThat(percentage.asRaw()).isEqualTo(84);
  }

  @Test
  void given_Invalid_Percentage_Integer_Below_One_Then_Throw_InvalidArgumentException_() {
    // prepare && execute && verify
    assertThatThrownBy(() -> Percentage.of(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid percentage number.");
  }

  @Test
  void given_Invalid_Percentage_Integer_Above_Hundred_Then_Throw_InvalidArgumentException_() {
    // prepare && execute && verify
    assertThatThrownBy(() -> Percentage.of(105))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid percentage number.");
  }

}
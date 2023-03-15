package com.huddle.huddle.book.shop.domain.book;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RegularBookTypeTest {

  @Test
  void given_BundleOfOneBook_Then_Give_Discounted_Book_Price() {
    // prepare
    BookType bookType = new RegularBookType();

    // execute
    Percentage percentage = bookType.calculatePrice(1);

    // verify
    assertThat(percentage.asRaw()).isEqualTo(100);
    assertThat(percentage.percentage()).isEqualTo(1);
  }

  @Test
  void given_BundleOfTwoBook_Then_Give_Discounted_Book_Price() {
    // prepare
    BookType bookType = new RegularBookType();

    // execute
    Percentage percentage = bookType.calculatePrice(2);

    // verify
    assertThat(percentage.asRaw()).isEqualTo(100);
    assertThat(percentage.percentage()).isEqualTo(1);
  }

  @Test
  void given_BundleOfThreeBook_Then_Give_Bundle_Discounted_Book_Price() {
    // prepare
    BookType bookType = new RegularBookType();

    // execute
    Percentage percentage = bookType.calculatePrice(3);

    // verify
    assertThat(percentage.asRaw()).isEqualTo(90);
    assertThat(percentage.percentage()).isEqualTo(0.9);
  }

  @Test
  void given_BundleOfFourBook_Then_Give_Bundle_Discounted_Book_Price() {
    // prepare
    BookType bookType = new RegularBookType();

    // execute
    Percentage percentage = bookType.calculatePrice(4);

    // verify
    assertThat(percentage.asRaw()).isEqualTo(90);
    assertThat(percentage.percentage()).isEqualTo(0.90);
  }

}
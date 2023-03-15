package com.huddle.huddle.book.shop.domain.book;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class NewReleaseBookTypeTest {

  @Test
  void given_BundleOfOneBook_Then_Give_Default_Book_Price() {
    // prepare
    BookType bookType = new NewReleaseBookType();

    // execute
    Percentage percentage = bookType.calculatePrice(1);

    // verify
    assertThat(percentage.asRaw()).isEqualTo(100);
    assertThat(percentage.percentage()).isEqualTo(1);
  }

  @Test
  void given_BundleOfTwoBook_Then_Give_Default_Book_Price() {
    // prepare
    BookType bookType = new NewReleaseBookType();

    // execute
    Percentage percentage = bookType.calculatePrice(2);

    // verify
    assertThat(percentage.asRaw()).isEqualTo(100);
    assertThat(percentage.percentage()).isEqualTo(1);
  }

  @Test
  void given_BundleOfThreeBook_Then_Give_Default_Book_Price() {
    // prepare
    BookType bookType = new NewReleaseBookType();

    // execute
    Percentage percentage = bookType.calculatePrice(3);

    // verify
    assertThat(percentage.asRaw()).isEqualTo(100);
    assertThat(percentage.percentage()).isEqualTo(1);
  }

}
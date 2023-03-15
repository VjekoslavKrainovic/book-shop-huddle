package com.huddle.huddle.book.shop.domain.book;

import static org.assertj.core.api.Assertions.assertThat;

import com.huddle.huddle.book.shop.domain.EuroMoney;
import org.junit.jupiter.api.Test;

class BookTest {

  @Test
  void given_Book_And_BundleOfFourBooks_Then_Return_Discounted_Price() {
    // prepare
    Book book = new Book(null, null, EuroMoney.of(32.0), new OldEditionBookType());

    // execute
    EuroMoney bookPrice = book.price(4);

    // verify
    assertThat(bookPrice.rawAmount()).isEqualTo(24);
  }

  @Test
  void given_Book_And_BundleOfOneBooks_Then_Return_Discounted_Price() {
    // prepare
    Book book = new Book(null, null, EuroMoney.of(32.0), new OldEditionBookType());

    // execute
    EuroMoney bookPrice = book.price(1);

    // verify
    assertThat(bookPrice.rawAmount()).isEqualTo(25.6);
  }

}
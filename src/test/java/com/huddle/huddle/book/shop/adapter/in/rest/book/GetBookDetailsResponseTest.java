package com.huddle.huddle.book.shop.adapter.in.rest.book;

import static org.assertj.core.api.Assertions.assertThat;

import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.BookId;
import com.huddle.huddle.book.shop.domain.book.BookType;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import com.huddle.huddle.book.shop.domain.book.OldEditionBookType;
import com.huddle.huddle.book.shop.domain.book.RegularBookType;
import org.junit.jupiter.api.Test;

class GetBookDetailsResponseTest {

  @Test
  void given_Book_Then_Map_To_GetBookDetailsResponse() {
    // prepare
    BookType bookType = new RegularBookType();
    Book book = new Book(BookId.of(1), "Batman", EuroMoney.of(100.0), bookType);

    // execute
    GetBookDetailsResponse getBookDetailsResponse = GetBookDetailsResponse.from(book);

    // verify
    assertThat(getBookDetailsResponse.getId()).isEqualTo(1);
    assertThat(getBookDetailsResponse.getName()).isEqualTo("Batman");
    assertThat(getBookDetailsResponse.getPrice()).isEqualTo(100.0);
    assertThat(getBookDetailsResponse.getType()).isEqualTo(GetBookDetailTypeResponse.REGULAR);
    assertThat(getBookDetailsResponse.getDiscountDetails().getDefaultPricePercentage()).isEqualTo(100.0);
    assertThat(getBookDetailsResponse.getDiscountDetails().getBundlePricePercentage()).isEqualTo(90.0);
    assertThat(getBookDetailsResponse.getDiscountDetails().getBundleOfBooksForDiscountPercentage()).isEqualTo(3);
  }

  @Test
  void given_RegularBookType_Then_Map_To_Regular() {
    // prepare
    BookType bookType = new RegularBookType();

    // execute
    GetBookDetailTypeResponse getBookDetailTypeResponse = GetBookDetailTypeResponse.from(bookType);

    // verify
    assertThat(getBookDetailTypeResponse).isEqualTo(GetBookDetailTypeResponse.REGULAR);
  }

  @Test
  void given_NewReleaseBookType_Then_Map_To_NewRelease() {
    // prepare
    BookType bookType = new NewReleaseBookType();

    // execute
    GetBookDetailTypeResponse getBookDetailTypeResponse = GetBookDetailTypeResponse.from(bookType);

    // verify
    assertThat(getBookDetailTypeResponse).isEqualTo(GetBookDetailTypeResponse.NEW_RELEASE);
  }

  @Test
  void given_OldEditionBookType_Then_Map_To_OldEdition() {
    // prepare
    BookType bookType = new OldEditionBookType();

    // execute
    GetBookDetailTypeResponse getBookDetailTypeResponse = GetBookDetailTypeResponse.from(bookType);

    // verify
    assertThat(getBookDetailTypeResponse).isEqualTo(GetBookDetailTypeResponse.OLD_EDITION);
  }
}
package com.huddle.huddle.book.shop.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import com.huddle.huddle.book.shop.domain.book.OldEditionBookType;
import com.huddle.huddle.book.shop.domain.book.RegularBookType;
import java.util.List;
import org.junit.jupiter.api.Test;

class BookBundlerTest {

  @Test
  void given_Books_Then_Sort_Books() {
    // prepare
    Book book1 = new Book(null, null, EuroMoney.of(32.0), new OldEditionBookType());
    Book book2 = new Book(null, null, EuroMoney.of(32.0), new RegularBookType());
    Book book3 = new Book(null, null, EuroMoney.of(32.0), new RegularBookType());
    Book book4 = new Book(null, null, EuroMoney.of(32.0), new RegularBookType());
    Book book5 = new Book(null, null, EuroMoney.of(32.0), new NewReleaseBookType());
    Book book6 = new Book(null, null, EuroMoney.of(32.0), new NewReleaseBookType());

    BookBundler bookBundler = new BookBundler(List.of(book1, book2, book3, book4, book5, book6));

    // verify
    List<Book> newReleaseBooks = bookBundler.getNewReleaseBooks();
    List<Book> oldEditionBooks = bookBundler.getOldEditionBooks();
    List<Book> regularBooks = bookBundler.getRegularBooks();

    // verify
    assertThat(newReleaseBooks).hasSize(2);
    assertThat(newReleaseBooks.get(0)).isEqualTo(book5);
    assertThat(newReleaseBooks.get(1)).isEqualTo(book6);

    assertThat(oldEditionBooks).hasSize(1);
    assertThat(oldEditionBooks.get(0)).isEqualTo(book1);

    assertThat(regularBooks).hasSize(3);
    assertThat(regularBooks.get(0)).isEqualTo(book2);
    assertThat(regularBooks.get(1)).isEqualTo(book3);
    assertThat(regularBooks.get(2)).isEqualTo(book4);
  }
}
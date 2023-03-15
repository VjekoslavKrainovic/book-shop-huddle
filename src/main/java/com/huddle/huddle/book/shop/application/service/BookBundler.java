package com.huddle.huddle.book.shop.application.service;

import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import com.huddle.huddle.book.shop.domain.book.OldEditionBookType;
import com.huddle.huddle.book.shop.domain.book.RegularBookType;
import java.util.List;

public class BookBundler {

  private final List<Book> allBooks;

  public BookBundler(List<Book> allBooks) {
    this.allBooks = allBooks;
  }

  public List<Book> getNewReleaseBooks() {
    return getBookByType(NewReleaseBookType.class);
  }

  public List<Book> getRegularBooks() {
    return getBookByType(RegularBookType.class);
  }

  public List<Book> getOldEditionBooks() {
    return getBookByType(OldEditionBookType.class);
  }

  private List<Book> getBookByType(Class bookType) {
    return allBooks.stream()
        .filter(book -> bookType.isInstance(book.getBookType()))
        .toList();
  }

}

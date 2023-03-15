package com.huddle.huddle.book.shop.application.exception;

import com.huddle.huddle.book.shop.domain.book.BookId;

public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException(BookId bookId) {
    super(String.format("Book with id: %s is not existing.", bookId.id()));
  }

}

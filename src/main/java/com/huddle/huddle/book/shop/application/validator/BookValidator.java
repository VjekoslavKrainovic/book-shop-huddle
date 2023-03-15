package com.huddle.huddle.book.shop.application.validator;

import com.huddle.huddle.book.shop.application.exception.BookNotFoundException;
import com.huddle.huddle.book.shop.application.port.BookRepository;
import com.huddle.huddle.book.shop.domain.book.BookId;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {

  private final BookRepository bookRepository;

  public BookValidator(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void validateIfBookExist(BookId bookId) {
    boolean isBookExist = bookRepository.isExist(bookId);

    if (!isBookExist) {
      throw new BookNotFoundException(bookId);
    }
  }

}

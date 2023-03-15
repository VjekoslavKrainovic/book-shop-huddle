package com.huddle.huddle.book.shop.application.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import com.huddle.huddle.book.shop.application.exception.BookNotFoundException;
import com.huddle.huddle.book.shop.application.port.BookRepository;
import com.huddle.huddle.book.shop.domain.book.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookValidatorTest {

  private BookValidator bookValidator;
  @Mock
  private BookRepository bookRepositoryMock;

  @BeforeEach
  void setUp() {
    bookValidator = new BookValidator(bookRepositoryMock);
  }

  @Test
  void given_Valid_BookId_Then_Dont_Throw_Exception() {
    // prepare
    BookId bookId = BookId.of(2);

    when(bookRepositoryMock.isExist(bookId)).thenReturn(true);

    // execute && verify
    assertDoesNotThrow(() -> bookValidator.validateIfBookExist(bookId));
  }

  @Test
  void given_Non_Existing_BookId_Then_Throw_Exception() {
    // prepare
    BookId bookId = BookId.of(2);

    when(bookRepositoryMock.isExist(bookId)).thenReturn(false);

    // execute && verify
    assertThatThrownBy(
        () -> bookValidator.validateIfBookExist(bookId))
        .isInstanceOf(BookNotFoundException.class)
        .hasMessageContaining("Book with id: 2 is not existing.");
  }

}
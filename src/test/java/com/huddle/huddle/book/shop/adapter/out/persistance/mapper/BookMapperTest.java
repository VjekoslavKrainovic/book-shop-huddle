package com.huddle.huddle.book.shop.adapter.out.persistance.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.BookDbo;
import com.huddle.huddle.book.shop.adapter.out.persistance.entity.BookTypeDbo;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookMapperTest {

  private BookMapper bookMapper;

  @BeforeEach
  void setUp() {
    bookMapper = new BookMapper(new BookTypeMapper());
  }

  @Test
  void given_BookDbo_Then_Map_To_Book() {
    // prepare
    BookDbo bookDbo = new BookDbo();
    bookDbo.setId(2);
    bookDbo.setName("Superman");
    bookDbo.setPrice(23.0);
    bookDbo.setType(BookTypeDbo.NEW_RELEASE);

    // execute
    Book book = bookMapper.asBook(bookDbo);

    // verify
    assertThat(book.getId().id()).isEqualTo(2);
    assertThat(book.getName()).isEqualTo("Superman");
    assertThat(book.getPrice().rawAmount()).isEqualTo(23.0);
    assertThat(book.getBookType()).isInstanceOf(NewReleaseBookType.class);
  }
}
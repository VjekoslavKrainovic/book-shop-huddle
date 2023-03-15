package com.huddle.huddle.book.shop.adapter.out.persistance.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.BookTypeDbo;
import com.huddle.huddle.book.shop.domain.book.BookType;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import com.huddle.huddle.book.shop.domain.book.OldEditionBookType;
import com.huddle.huddle.book.shop.domain.book.RegularBookType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTypeMapperTest {

  private BookTypeMapper bookTypeMapper;

  @BeforeEach
  void setUp() {
    bookTypeMapper = new BookTypeMapper();
  }

  @Test
  void given_NewReleaseDbo_Then_Map_To_NewRelease() {
    // prepare
    BookTypeDbo newRelease = BookTypeDbo.NEW_RELEASE;

    // execute
    BookType newReleaseBookType = bookTypeMapper.asBookType(newRelease);

    // verify
    assertThat(newReleaseBookType).isInstanceOf(NewReleaseBookType.class);
  }

  @Test
  void given_OldEditionDbo_Then_Map_To_OldEdition() {
    // prepare
    BookTypeDbo oldEdition = BookTypeDbo.OLD_EDITION;

    // execute
    BookType oldEditionBookType = bookTypeMapper.asBookType(oldEdition);

    // verify
    assertThat(oldEditionBookType).isInstanceOf(OldEditionBookType.class);
  }

  @Test
  void given_RegularDbo_Then_Map_To_Regular() {
    // prepare
    BookTypeDbo regular = BookTypeDbo.REGULAR;

    // execute
    BookType regularBookType = bookTypeMapper.asBookType(regular);

    // verify
    assertThat(regularBookType).isInstanceOf(RegularBookType.class);
  }

}
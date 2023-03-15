package com.huddle.huddle.book.shop.adapter.out.persistance.mapper;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.BookTypeDbo;
import com.huddle.huddle.book.shop.domain.book.BookType;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import com.huddle.huddle.book.shop.domain.book.OldEditionBookType;
import com.huddle.huddle.book.shop.domain.book.RegularBookType;
import org.springframework.stereotype.Component;

@Component
public class BookTypeMapper {

  public BookType asBookType(BookTypeDbo bookTypeDbo) {
    if (bookTypeDbo == BookTypeDbo.NEW_RELEASE) {
      return new NewReleaseBookType();
    } else if (bookTypeDbo == BookTypeDbo.OLD_EDITION) {
      return new OldEditionBookType();
    } else if (bookTypeDbo == BookTypeDbo.REGULAR) {
      return new RegularBookType();
    } else {
      throw new IllegalArgumentException("Non existing book type");
    }
  }

}

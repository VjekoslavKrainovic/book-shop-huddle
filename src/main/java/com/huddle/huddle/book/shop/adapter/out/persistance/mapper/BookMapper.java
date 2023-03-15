package com.huddle.huddle.book.shop.adapter.out.persistance.mapper;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.BookDbo;
import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.BookId;
import com.huddle.huddle.book.shop.domain.book.BookType;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

  private final BookTypeMapper bookTypeMapper;

  public BookMapper(BookTypeMapper bookTypeMapper) {
    this.bookTypeMapper = bookTypeMapper;
  }

  public Book asBook(BookDbo bookDbo) {
    BookId id = BookId.of(bookDbo.getId());
    EuroMoney price = EuroMoney.of(bookDbo.getPrice());
    BookType bookType = bookTypeMapper.asBookType(bookDbo.getType());
    return new Book(id, bookDbo.getName(), price, bookType);
  }

}

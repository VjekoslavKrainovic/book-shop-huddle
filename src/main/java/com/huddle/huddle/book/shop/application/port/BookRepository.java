package com.huddle.huddle.book.shop.application.port;

import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.BookId;
import java.util.List;

public interface BookRepository {

  List<Book> findByIds(List<BookId> booksId);

  List<Book> findAllBooks();

  boolean isExist(BookId bookId);

}

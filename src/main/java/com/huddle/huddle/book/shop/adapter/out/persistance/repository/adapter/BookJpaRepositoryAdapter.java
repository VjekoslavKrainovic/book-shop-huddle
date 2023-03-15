package com.huddle.huddle.book.shop.adapter.out.persistance.repository.adapter;

import com.huddle.huddle.book.shop.adapter.out.persistance.mapper.BookMapper;
import com.huddle.huddle.book.shop.adapter.out.persistance.entity.BookDbo;
import com.huddle.huddle.book.shop.adapter.out.persistance.repository.jparepository.BookJpaRepository;
import com.huddle.huddle.book.shop.application.port.BookRepository;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.BookId;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookJpaRepositoryAdapter implements BookRepository {

  private final BookJpaRepository bookJpaRepository;
  private final BookMapper bookMapper;

  public BookJpaRepositoryAdapter(BookJpaRepository bookJpaRepository, BookMapper bookMapper) {
    this.bookJpaRepository = bookJpaRepository;
    this.bookMapper = bookMapper;
  }

  @Override
  public List<Book> findByIds(List<BookId> booksId) {
    List<Integer> booksIdInteger = booksId.stream()
        .map(BookId::id)
        .toList();

    List<BookDbo> booksDbo = bookJpaRepository.findByIdIn(booksIdInteger);

    return booksDbo.stream()
        .map(bookMapper::asBook)
        .toList();
  }

  @Override
  public List<Book> findAllBooks() {
    List<BookDbo> booksDbo = bookJpaRepository.findAll();
    return booksDbo.stream()
        .map(bookMapper::asBook)
        .toList();
  }

  @Override
  public boolean isExist(BookId bookId) {
    return bookJpaRepository.existsById(bookId.id());
  }
}

package com.huddle.huddle.book.shop.domain.book;

public record BookId(Integer id) {

  public static BookId of(Integer id) {
    return new BookId(id);
  }

}
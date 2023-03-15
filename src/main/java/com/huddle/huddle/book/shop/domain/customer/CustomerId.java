package com.huddle.huddle.book.shop.domain.customer;

public record CustomerId(Integer id) {

  public static CustomerId of(Integer id) {
    return new CustomerId(id);
  }

}
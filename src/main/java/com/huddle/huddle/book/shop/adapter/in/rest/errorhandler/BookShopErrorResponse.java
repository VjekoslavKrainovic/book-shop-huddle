package com.huddle.huddle.book.shop.adapter.in.rest.errorhandler;

import lombok.Getter;

@Getter
public class BookShopErrorResponse {

  private final String message;

  public BookShopErrorResponse(String message) {
    this.message = message;
  }
}
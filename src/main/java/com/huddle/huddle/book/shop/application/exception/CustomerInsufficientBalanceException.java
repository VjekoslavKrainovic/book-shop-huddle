package com.huddle.huddle.book.shop.application.exception;

public class CustomerInsufficientBalanceException extends RuntimeException {

  public CustomerInsufficientBalanceException() {
    super("Customer insufficient balance.");
  }
}

package com.huddle.huddle.book.shop.application.exception;

import com.huddle.huddle.book.shop.domain.customer.CustomerId;

public class CustomerNotFoundException extends RuntimeException{

  public CustomerNotFoundException(CustomerId customerId) {
    super(String.format("Customer with id: %s is not existing.", customerId.id()));
  }

}

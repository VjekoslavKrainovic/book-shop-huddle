package com.huddle.huddle.book.shop.adapter.in.rest.errorhandler;

import com.huddle.huddle.book.shop.application.exception.BookNotFoundException;
import com.huddle.huddle.book.shop.application.exception.CustomerInsufficientBalanceException;
import com.huddle.huddle.book.shop.application.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookShopErrorHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  BookShopErrorResponse handleIllegalArgumentException(IllegalArgumentException iae) {
    return new BookShopErrorResponse(iae.getMessage());
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  BookShopErrorResponse handleCustomerNotFoundException(CustomerNotFoundException cnfe) {
    return new BookShopErrorResponse(cnfe.getMessage());
  }

  @ExceptionHandler(BookNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  BookShopErrorResponse handleBookNotFoundException(BookNotFoundException bnfe) {
    return new BookShopErrorResponse(bnfe.getMessage());
  }

  @ExceptionHandler(CustomerInsufficientBalanceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  BookShopErrorResponse handleCustomerInsufficientBalanceException(CustomerInsufficientBalanceException cibe) {
    return new BookShopErrorResponse(cibe.getMessage());
  }

}

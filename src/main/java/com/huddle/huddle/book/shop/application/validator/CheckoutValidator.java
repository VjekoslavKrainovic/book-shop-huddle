package com.huddle.huddle.book.shop.application.validator;

import com.huddle.huddle.book.shop.domain.book.BookId;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CheckoutValidator {

  private final CustomerValidator customerValidator;
  private final BookValidator bookValidator;

  public CheckoutValidator(CustomerValidator customerValidator, BookValidator bookValidator) {
    this.customerValidator = customerValidator;
    this.bookValidator = bookValidator;
  }

  public void validateCustomerAndBooks(List<BookId> booksId, CustomerId customerId){
    customerValidator.validateIfCustomerExist(customerId);
    booksId.forEach(bookValidator::validateIfBookExist);
  }

}

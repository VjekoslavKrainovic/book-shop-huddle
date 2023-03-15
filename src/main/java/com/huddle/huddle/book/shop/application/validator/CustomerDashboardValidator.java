package com.huddle.huddle.book.shop.application.validator;

import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDashboardValidator {

  private final CustomerValidator customerValidator;

  public CustomerDashboardValidator(CustomerValidator customerValidator) {
    this.customerValidator = customerValidator;
  }

  public void validateCustomer(CustomerId customerId){
    customerValidator.validateIfCustomerExist(customerId);
  }

}

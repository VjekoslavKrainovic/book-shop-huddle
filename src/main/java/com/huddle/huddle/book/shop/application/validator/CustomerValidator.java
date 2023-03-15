package com.huddle.huddle.book.shop.application.validator;

import com.huddle.huddle.book.shop.application.exception.CustomerNotFoundException;
import com.huddle.huddle.book.shop.application.port.CustomerRepository;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

  private final CustomerRepository customerRepository;

  public CustomerValidator(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public void validateIfCustomerExist(CustomerId customerId) {
    boolean isCustomerExist = customerRepository.isExist(customerId);

    if (!isCustomerExist) {
      throw new CustomerNotFoundException(customerId);
    }
  }

}

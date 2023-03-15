package com.huddle.huddle.book.shop.application.port;

import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;

public interface CustomerRepository {

  Customer findById(CustomerId customerId);

  void save(Customer customer);

  boolean isExist(CustomerId customerId);
}

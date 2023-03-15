package com.huddle.huddle.book.shop.application.service;

import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import java.util.List;

public interface CustomerDashboardService {

  List<Book> getAllBooks();

  Customer findCustomerById(CustomerId customerId);

}

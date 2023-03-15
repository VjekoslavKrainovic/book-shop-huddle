package com.huddle.huddle.book.shop.application.service;

import com.huddle.huddle.book.shop.application.port.BookRepository;
import com.huddle.huddle.book.shop.application.port.CustomerRepository;
import com.huddle.huddle.book.shop.application.validator.CustomerDashboardValidator;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerDashboardServiceImpl implements CustomerDashboardService {

  private final BookRepository bookRepository;
  private final CustomerRepository customerRepository;
  private final CustomerDashboardValidator customerDashboardValidator;

  public CustomerDashboardServiceImpl(BookRepository bookRepository,
      CustomerRepository customerRepository,
      CustomerDashboardValidator customerDashboardValidator) {
    this.bookRepository = bookRepository;
    this.customerRepository = customerRepository;
    this.customerDashboardValidator = customerDashboardValidator;
  }

  @Override
  public List<Book> getAllBooks() {
    log.debug("START: getAllBooks()");

    List<Book> books = bookRepository.findAllBooks();

    log.debug("OUTPUT: {}", books);
    log.info("END: getAllBooks()");
    return books;
  }

  @Override
  public Customer findCustomerById(CustomerId customerId) {
    log.debug("START: findCustomerById()");
    log.debug("INPUT: {}", customerId);

    customerDashboardValidator.validateCustomer(customerId);
    Customer customer = customerRepository.findById(customerId);

    log.debug("OUTPUT: {}", customer);
    log.info("END: findCustomerById()");
    return customer;
  }
}

package com.huddle.huddle.book.shop.application.service;

import com.huddle.huddle.book.shop.application.port.BookRepository;
import com.huddle.huddle.book.shop.application.port.CustomerRepository;
import com.huddle.huddle.book.shop.application.validator.CheckoutValidator;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.BookId;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckoutServiceImpl implements CheckoutService {

  private final BookRepository bookRepository;
  private final CustomerRepository customerRepository;
  private final CheckoutValidator checkoutValidator;

  public CheckoutServiceImpl(BookRepository bookRepository, CustomerRepository customerRepository,
      CheckoutValidator checkoutValidator) {
    this.bookRepository = bookRepository;
    this.customerRepository = customerRepository;
    this.checkoutValidator = checkoutValidator;
  }

  @Override
  public void buyBooks(List<BookId> booksId, CustomerId customerId) {

    log.debug("START: buyBooks()");
    log.debug("INPUT: {}, {}", booksId, customerId);

    checkoutValidator.validateCustomerAndBooks(booksId, customerId);

    List<Book> booksToBuy = bookRepository.findByIds(booksId);
    Customer customer = customerRepository.findById(customerId);

    customer.buyBooks(booksToBuy);

    customerRepository.save(customer);

    log.info("END: buyBooks()");
  }
}

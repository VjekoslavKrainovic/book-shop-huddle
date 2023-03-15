package com.huddle.huddle.book.shop.application.service;

import com.huddle.huddle.book.shop.domain.book.BookId;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import java.util.List;

public interface CheckoutService {

  void buyBooks(List<BookId> booksId, CustomerId customerId);

}

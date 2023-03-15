package com.huddle.huddle.book.shop.adapter.in.rest.book;

import com.huddle.huddle.book.shop.application.service.CustomerDashboardService;
import com.huddle.huddle.book.shop.domain.book.Book;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/books")
public class BookController {

  private final CustomerDashboardService customerDashboardService;

  public BookController(CustomerDashboardService customerDashboardService) {
    this.customerDashboardService = customerDashboardService;
  }

  @GetMapping
  public List<GetBookDetailsResponse> getAllBook() {
    log.debug("START: getAllBook()");

    List<Book> books = customerDashboardService.getAllBooks();
    List<GetBookDetailsResponse> getBookDetailsResponses = books.stream()
        .map(GetBookDetailsResponse::from)
        .toList();

    log.debug("OUTPUT: {}", getBookDetailsResponses);
    log.info("END: getAllBook()");
    return getBookDetailsResponses;
  }

}

package com.huddle.huddle.book.shop.adapter.in.rest.customer;

import com.huddle.huddle.book.shop.application.service.CheckoutService;
import com.huddle.huddle.book.shop.application.service.CustomerDashboardService;
import com.huddle.huddle.book.shop.domain.book.BookId;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

  private final CustomerDashboardService customerDashboardService;
  private final CheckoutService checkoutService;

  public CustomerController(CustomerDashboardService customerDashboardService, CheckoutService checkoutService) {
    this.customerDashboardService = customerDashboardService;
    this.checkoutService = checkoutService;
  }

  @GetMapping("/{customer-id}")
  public GetCustomerDetailsResponse getCustomerDetails(@PathVariable("customer-id") Integer customerIdRequest) {
    log.debug("START: getCustomerDetails()");
    log.debug("INPUT: {}", customerIdRequest);

    CustomerId customerId = CustomerId.of(customerIdRequest);
    Customer customer = customerDashboardService.findCustomerById(customerId);
    GetCustomerDetailsResponse getCustomerDetailsResponse = GetCustomerDetailsResponse.from(customer);

    log.debug("OUTPUT: {}", getCustomerDetailsResponse);
    log.info("END: getCustomerDetails()");
    return getCustomerDetailsResponse;
  }


  @PostMapping("/{customer-id}/checkout")
  public void buyBooks(@PathVariable("customer-id") Integer customerIdRequest, @RequestParam("booksId") List<Integer> booksIdRequest) {
    log.debug("START: buyBooks()");
    log.debug("INPUT: {}, {}", customerIdRequest, booksIdRequest);

    CustomerId customerId = CustomerId.of(customerIdRequest);
    List<BookId> booksId = booksIdRequest.stream()
        .map(BookId::new)
        .toList();

    checkoutService.buyBooks(booksId, customerId);
    log.info("END: buyBooks()");
  }

}

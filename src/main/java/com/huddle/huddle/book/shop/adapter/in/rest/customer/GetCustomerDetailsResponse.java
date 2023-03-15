package com.huddle.huddle.book.shop.adapter.in.rest.customer;

import com.huddle.huddle.book.shop.domain.customer.Customer;

public record GetCustomerDetailsResponse(Double balance, Integer loyaltyPoints) {

  public static GetCustomerDetailsResponse from(Customer customer) {
    return new GetCustomerDetailsResponse(customer.getBalance().rawAmount(), customer.getLoyaltyPoints().points());
  }

}

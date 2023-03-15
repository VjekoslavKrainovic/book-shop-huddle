package com.huddle.huddle.book.shop.adapter.in.rest.customer;

import static org.assertj.core.api.Assertions.assertThat;

import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import com.huddle.huddle.book.shop.domain.customer.LoyaltyPoints;
import org.junit.jupiter.api.Test;

class GetCustomerDetailsResponseTest {

  @Test
  void given_Customer_Then_Map_To_GetCustomerDetailsResponse() {
    // prepare
    Customer customer = new Customer(CustomerId.of(1), EuroMoney.of(100.0), LoyaltyPoints.of(2));

    // execute
    GetCustomerDetailsResponse getCustomerDetailsResponse = GetCustomerDetailsResponse.from(customer);

    // verify
    assertThat(getCustomerDetailsResponse.balance()).isEqualTo(100);
    assertThat(getCustomerDetailsResponse.loyaltyPoints()).isEqualTo(2);
  }
}
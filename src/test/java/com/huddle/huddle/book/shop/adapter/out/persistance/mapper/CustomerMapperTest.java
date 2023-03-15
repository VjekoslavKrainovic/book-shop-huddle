package com.huddle.huddle.book.shop.adapter.out.persistance.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.CustomerDbo;
import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import com.huddle.huddle.book.shop.domain.customer.LoyaltyPoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerMapperTest {

  private CustomerMapper customerMapper;

  @BeforeEach
  void setUp() {
    customerMapper = new CustomerMapper();
  }

  @Test
  void given_Customer_Then_Map_To_CustomerDbo() {
    // prepare
    Customer customer = new Customer(CustomerId.of(1), EuroMoney.of(100.0), LoyaltyPoints.of(2));

    // execute
    CustomerDbo customerDbo = customerMapper.asCustomerDbo(customer);

    // verify
    assertThat(customerDbo.getId()).isEqualTo(1);
    assertThat(customerDbo.getBalance()).isEqualTo(100);
    assertThat(customerDbo.getLoyaltyPoints()).isEqualTo(2);
  }

  @Test
  void given_CustomerDbo_Then_Map_to_Customer() {
    // prepare
    CustomerDbo customerDbo = new CustomerDbo();
    customerDbo.setId(1);
    customerDbo.setBalance(102.0);
    customerDbo.setLoyaltyPoints(19);

    // execute
    Customer customer = customerMapper.asCustomer(customerDbo);

    // verify
    assertThat(customer.getId().id()).isEqualTo(1);
    assertThat(customer.getBalance().rawAmount()).isEqualTo(102.0);
    assertThat(customer.getLoyaltyPoints().points()).isEqualTo(19);
  }
}
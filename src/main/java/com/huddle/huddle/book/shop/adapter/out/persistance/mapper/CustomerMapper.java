package com.huddle.huddle.book.shop.adapter.out.persistance.mapper;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.CustomerDbo;
import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import com.huddle.huddle.book.shop.domain.customer.LoyaltyPoints;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public Customer asCustomer(CustomerDbo customerDbo) {
    CustomerId id = CustomerId.of(customerDbo.getId());
    EuroMoney balance = EuroMoney.of(customerDbo.getBalance());
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(customerDbo.getLoyaltyPoints());
    return new Customer(id, balance, loyaltyPoints);
  }

  public CustomerDbo asCustomerDbo(Customer customer) {
    CustomerDbo customerDbo = new CustomerDbo();

    if (customer.getId() != null) {
      customerDbo.setId(customer.getId().id());
    }

    customerDbo.setBalance(customer.getBalance().rawAmount());
    customerDbo.setLoyaltyPoints(customer.getLoyaltyPoints().points());

    return customerDbo;
  }

}

package com.huddle.huddle.book.shop.adapter.out.persistance.repository.adapter;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.CustomerDbo;
import com.huddle.huddle.book.shop.adapter.out.persistance.mapper.CustomerMapper;
import com.huddle.huddle.book.shop.adapter.out.persistance.repository.jparepository.CustomerJpaRepository;
import com.huddle.huddle.book.shop.application.exception.CustomerNotFoundException;
import com.huddle.huddle.book.shop.application.port.CustomerRepository;
import com.huddle.huddle.book.shop.domain.customer.Customer;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerJpaRepositoryAdapter implements CustomerRepository {

  private final CustomerJpaRepository customerJpaRepository;
  private final CustomerMapper customerMapper;

  public CustomerJpaRepositoryAdapter(CustomerJpaRepository customerJpaRepository, CustomerMapper customerMapper) {
    this.customerJpaRepository = customerJpaRepository;
    this.customerMapper = customerMapper;
  }

  @Override
  public Customer findById(CustomerId customerId) {
    CustomerDbo customerDbo = customerJpaRepository.findById(customerId.id())
        .orElseThrow(() -> new CustomerNotFoundException(customerId));
    return customerMapper.asCustomer(customerDbo);
  }

  @Override
  public void save(Customer customer) {
    CustomerDbo customerDbo = customerMapper.asCustomerDbo(customer);
    customerJpaRepository.save(customerDbo);
  }

  @Override
  public boolean isExist(CustomerId customerId) {
    return customerJpaRepository.existsById(customerId.id());
  }
}

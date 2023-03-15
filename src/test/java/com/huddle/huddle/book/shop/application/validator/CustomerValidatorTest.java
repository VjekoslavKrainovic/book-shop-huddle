package com.huddle.huddle.book.shop.application.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import com.huddle.huddle.book.shop.application.exception.CustomerNotFoundException;
import com.huddle.huddle.book.shop.application.port.CustomerRepository;
import com.huddle.huddle.book.shop.domain.customer.CustomerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerValidatorTest {

  private CustomerValidator customerValidator;
  @Mock
  private CustomerRepository customerRepositoryMock;

  @BeforeEach
  void setUp() {
    customerValidator = new CustomerValidator(customerRepositoryMock);
  }

  @Test
  void given_Valid_CustomerId_Then_Dont_Throw_Exception() {
    // prepare
    CustomerId customerId = CustomerId.of(2);

    when(customerRepositoryMock.isExist(customerId)).thenReturn(true);

    // execute && verify
    assertDoesNotThrow(() -> customerValidator.validateIfCustomerExist(customerId));
  }

  @Test
  void given_Non_Existing_CustomerId_Then_Throw_Exception() {
    // prepare
    CustomerId customerId = CustomerId.of(2);

    when(customerRepositoryMock.isExist(customerId)).thenReturn(false);

    // execute && verify
    assertThatThrownBy(
        () -> customerValidator.validateIfCustomerExist(customerId))
        .isInstanceOf(CustomerNotFoundException.class)
        .hasMessageContaining("Customer with id: 2 is not existing.");
  }

}
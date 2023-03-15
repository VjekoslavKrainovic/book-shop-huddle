package com.huddle.huddle.book.shop.domain.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.huddle.huddle.book.shop.application.exception.CustomerInsufficientBalanceException;
import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import com.huddle.huddle.book.shop.domain.book.OldEditionBookType;
import com.huddle.huddle.book.shop.domain.book.RegularBookType;
import java.util.List;
import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  void given_Customer_With_Big_Enough_Balance_Without_LoyaltyPoints_Then_Buy_Book() {
    // prepare
    // cost with discount is 8.0
    Book book1 = new Book(null, "Old edition book", EuroMoney.of(10.0), new OldEditionBookType());
    // cost with discount is 18.0
    Book book2 = new Book(null, "Regular book 1", EuroMoney.of(20.0), new RegularBookType());
    // cost with discount is 27.0
    Book book3 = new Book(null, "Regular book 2", EuroMoney.of(30.0), new RegularBookType());
    // cost with discount is 36.0
    Book book4 = new Book(null, "Regular book 3", EuroMoney.of(40.0), new RegularBookType());
    // cost with discount is 50
    Book book5 = new Book(null, "New release book 1", EuroMoney.of(50.0), new NewReleaseBookType());
    // cost with discount is 60
    Book book6 = new Book(null, "New release book 2", EuroMoney.of(60.0), new NewReleaseBookType());

    Customer customer = new Customer(null, EuroMoney.of(300.0), LoyaltyPoints.of(5));
    // execute
    customer.buyBooks(List.of(book1, book2, book3, book4, book5, book6));

    // verify
    assertThat(customer.getLoyaltyPoints().points()).isEqualTo(11);
    assertThat(customer.getBalance().rawAmount()).isEqualTo(101.0);
  }

  @Test
  void given_Customer_With_Big_Enough_Balance_With_LoyaltyPoints_For_Two_Books_Then_Buy_Book() {
    // prepare
    // cost with discount is 0
    Book book1 = new Book(null, "Old edition book", EuroMoney.of(10.0), new OldEditionBookType());
    // cost with discount is 18.0
    Book book2 = new Book(null, "Regular book 1", EuroMoney.of(20.0), new RegularBookType());
    // cost with discount is 27.0
    Book book3 = new Book(null, "Regular book 2", EuroMoney.of(30.0), new RegularBookType());
    // cost with discount is 0
    Book book4 = new Book(null, "Regular book 3", EuroMoney.of(40.0), new RegularBookType());
    // cost with discount is 50
    Book book5 = new Book(null, "New release book 1", EuroMoney.of(50.0), new NewReleaseBookType());
    // cost with discount is 60
    Book book6 = new Book(null, "New release book 2", EuroMoney.of(60.0), new NewReleaseBookType());

    Customer customer = new Customer(null, EuroMoney.of(200.0), LoyaltyPoints.of(22));
    // execute
    customer.buyBooks(List.of(book1, book2, book3, book4, book5, book6));

    // verify
    assertThat(customer.getLoyaltyPoints().points()).isEqualTo(8);
    assertThat(customer.getBalance().rawAmount()).isEqualTo(45.0);
  }

  @Test
  void given_Customer_With_Big_Enough_Balance_With_LoyaltyPoints_For_All_Books_Then_Buy_Book() {
    // prepare
    // cost with discount is 0
    Book book1 = new Book(null, "Old edition book", EuroMoney.of(10.0), new OldEditionBookType());
    // cost with discount is 18.0
    Book book2 = new Book(null, "Regular book 1", EuroMoney.of(20.0), new RegularBookType());
    // cost with discount is 27.0
    Book book3 = new Book(null, "Regular book 2", EuroMoney.of(30.0), new RegularBookType());
    // cost with discount is 0
    Book book4 = new Book(null, "Regular book 3", EuroMoney.of(40.0), new RegularBookType());
    // cost with discount is 50
    Book book5 = new Book(null, "New release book 1", EuroMoney.of(50.0), new NewReleaseBookType());
    // cost with discount is 60
    Book book6 = new Book(null, "New release book 2", EuroMoney.of(60.0), new NewReleaseBookType());

    Customer customer = new Customer(null, EuroMoney.of(200.0), LoyaltyPoints.of(45));
    // execute
    customer.buyBooks(List.of(book1, book2, book3, book4, book5, book6));

    // verify
    assertThat(customer.getLoyaltyPoints().points()).isEqualTo(11);
    assertThat(customer.getBalance().rawAmount()).isEqualTo(90.0);
  }

  @Test
  void given_Customer_With_Not_Enough_Balance_But_Enough_LoyaltyPoints_Then_Buy_Book() {
    // prepare
    // cost with discount is 0
    Book book1 = new Book(null, "Old edition book", EuroMoney.of(50.0), new OldEditionBookType());

    Customer customer = new Customer(null, EuroMoney.of(20.0), LoyaltyPoints.of(45));

    // execute && verify
    customer.buyBooks(List.of(book1));

    // verify
    assertThat(customer.getLoyaltyPoints().points()).isEqualTo(36);
    assertThat(customer.getBalance().rawAmount()).isEqualTo(20.0);
  }

  @Test
  void given_Customer_With_Not_Enough_Balance_Then_Throw_Exception() {
    // prepare
    // cost with discount is 0
    Book book1 = new Book(null, "Old edition book", EuroMoney.of(10.0), new OldEditionBookType());
    // cost with discount is 18.0
    Book book2 = new Book(null, "Regular book 1", EuroMoney.of(20.0), new RegularBookType());
    // cost with discount is 27.0
    Book book3 = new Book(null, "Regular book 2", EuroMoney.of(30.0), new RegularBookType());
    // cost with discount is 0
    Book book4 = new Book(null, "Regular book 3", EuroMoney.of(40.0), new RegularBookType());
    // cost with discount is 50
    Book book5 = new Book(null, "New release book 1", EuroMoney.of(50.0), new NewReleaseBookType());
    // cost with discount is 60
    Book book6 = new Book(null, "New release book 2", EuroMoney.of(60.0), new NewReleaseBookType());

    Customer customer = new Customer(null, EuroMoney.of(20.0), LoyaltyPoints.of(45));

    // execute && verify
    assertThatThrownBy(() -> customer.buyBooks(List.of(book1, book2, book3, book4, book5, book6)))
        .isInstanceOf(CustomerInsufficientBalanceException.class)
        .hasMessageContaining("Customer insufficient balance.");
  }

}
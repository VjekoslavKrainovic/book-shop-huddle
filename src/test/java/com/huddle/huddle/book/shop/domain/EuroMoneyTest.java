package com.huddle.huddle.book.shop.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.huddle.huddle.book.shop.application.exception.CustomerInsufficientBalanceException;
import org.junit.jupiter.api.Test;

class EuroMoneyTest {

  @Test
  void given_Lower_Money_Then_Zero_Then_Throw_Exception() {
    // prepare
    Double moneyInDouble = -2.0;

    // execute && verify
    assertThatThrownBy(() -> EuroMoney.of(moneyInDouble))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Money amount cant be lower then zero");
  }

  @Test
  void given_Money_Then_AddUp() {
    // prepare
    EuroMoney moneyToAddUp = EuroMoney.of(10.0);
    EuroMoney money = EuroMoney.of(5.0);

    // execute
    EuroMoney addUpMoney = money.addUp(moneyToAddUp);

    // verify
    assertThat(addUpMoney.rawAmount()).isEqualTo(15.0);
  }

  @Test
  void given_Enough_Money_Then_Subtract() {
    // prepare
    EuroMoney moneyToSubtract = EuroMoney.of(10.0);
    EuroMoney money = EuroMoney.of(15.0);

    // execute
    EuroMoney subtractedMoney = money.subtract(moneyToSubtract);

    // verify
    assertThat(subtractedMoney.rawAmount()).isEqualTo(5.0);
  }

  @Test
  void given_Not_Enough_Money_Then_Throw_Exception() {
    // prepare
    EuroMoney moneyToSubtract = EuroMoney.of(10.0);
    EuroMoney money = EuroMoney.of(5.0);

    // execute && verify
    assertThatThrownBy(() -> money.subtract(moneyToSubtract))
        .isInstanceOf(CustomerInsufficientBalanceException.class)
        .hasMessageContaining("Customer insufficient balance.");
  }
}
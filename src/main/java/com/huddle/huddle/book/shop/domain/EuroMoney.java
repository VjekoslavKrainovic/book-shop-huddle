package com.huddle.huddle.book.shop.domain;

import com.huddle.huddle.book.shop.application.exception.CustomerInsufficientBalanceException;
import java.util.Objects;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class EuroMoney {

  private final Money amount;

  private EuroMoney(Double amount) {
    this.amount = Money.of(CurrencyUnit.EUR, amount);
  }

  public boolean isPossibleToSubtract(EuroMoney moneyToSubtract) {
    return moneyToSubtract.amount.equals(this.amount) || moneyToSubtract.amount.isLessThan(this.amount);
  }

  public EuroMoney addUp(EuroMoney moneyToAdd) {
    org.joda.money.Money newBalance = this.amount.plus(moneyToAdd.amount);
    return EuroMoney.of(newBalance.getAmount().doubleValue());
  }

  public EuroMoney subtract(EuroMoney moneyToSubtract) {

    if (!isPossibleToSubtract(moneyToSubtract)) {
      throw new CustomerInsufficientBalanceException();
    }

    org.joda.money.Money newBalance = this.amount.minus(moneyToSubtract.amount);
    return EuroMoney.of(newBalance.getAmount().doubleValue());
  }

  public static EuroMoney of(Double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Money amount cant be lower then zero");
    }
    return new EuroMoney(amount);
  }

  public Double rawAmount() {
    return amount.getAmount().doubleValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EuroMoney euroMoney = (EuroMoney) o;
    return Objects.equals(amount.getAmount(), euroMoney.amount.getAmount());
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount.getAmount());
  }
}
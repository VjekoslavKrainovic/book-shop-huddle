package com.huddle.huddle.book.shop.domain.book;

public record Percentage(Double percentage) {

  public static Percentage of(Integer percentage) {
    if (percentage < 1 || percentage > 100) {
      throw new IllegalArgumentException("Invalid percentage number.");
    }
    return new Percentage( ((double) percentage) / 100);
  }

  @Override
  public Double percentage() {
    return percentage;
  }

  public Double asRaw() {
    return percentage * 100;
  }

}
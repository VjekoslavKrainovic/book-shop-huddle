package com.huddle.huddle.book.shop.domain.book;

public class OldEditionBookType extends BookType {

  public OldEditionBookType() {
    super(Percentage.of(80), Percentage.of(75), 3);
  }

  @Override
  public Percentage calculatePrice(Integer bookBundleNumber) {
    if (bookBundleNumber >= getBundleOfBooksForDiscount()) {
      return getBundlePrice();
    } else {
      return getDefaultPrice();
    }
  }
}

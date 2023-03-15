package com.huddle.huddle.book.shop.domain.book;

public class RegularBookType extends BookType {

  public RegularBookType() {
    super(Percentage.of(100), Percentage.of(90), 3);
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

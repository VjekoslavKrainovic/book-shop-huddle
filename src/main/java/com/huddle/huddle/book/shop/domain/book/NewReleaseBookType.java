package com.huddle.huddle.book.shop.domain.book;

public class NewReleaseBookType extends BookType {

  public NewReleaseBookType() {
    super(Percentage.of(100), Percentage.of(100), null);
  }

  @Override
  public Percentage calculatePrice(Integer bookBundleNumber) {
    return Percentage.of(100);
  }
}

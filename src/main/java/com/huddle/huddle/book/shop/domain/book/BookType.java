package com.huddle.huddle.book.shop.domain.book;

public abstract class BookType {

  private final Percentage defaultPrice;
  private final Percentage bundlePrice;
  private final Integer bundleOfBooksForDiscount;

  public BookType(Percentage defaultPrice, Percentage bundlePrice, Integer bundleOfBooksForDiscount) {
    this.defaultPrice = defaultPrice;
    this.bundlePrice = bundlePrice;
    this.bundleOfBooksForDiscount = bundleOfBooksForDiscount;
  }

  public abstract Percentage calculatePrice(Integer bookBundleNumber);

  public Percentage getDefaultPrice() {
    return defaultPrice;
  }

  public Percentage getBundlePrice() {
    return bundlePrice;
  }

  public Integer getBundleOfBooksForDiscount() {
    return bundleOfBooksForDiscount;
  }

  @Override
  public String toString() {
    return "BookType{" +
        "defaultPrice=" + defaultPrice +
        ", bundlePrice=" + bundlePrice +
        ", bundleOfBooksForDiscount=" + bundleOfBooksForDiscount +
        '}';
  }

}

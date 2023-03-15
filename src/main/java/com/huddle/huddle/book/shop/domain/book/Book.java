package com.huddle.huddle.book.shop.domain.book;

import com.huddle.huddle.book.shop.domain.EuroMoney;
import java.util.Objects;

public class Book {

  private BookId id;
  private String name;
  private EuroMoney price;
  private BookType bookType;

  public Book(BookId id, String name, EuroMoney price, BookType bookType) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.bookType = bookType;
  }

  public EuroMoney price(Integer bookBundleNumber) {
    Percentage discountedPriceInPercentage = bookType.calculatePrice(bookBundleNumber);
    double priceWithDiscount = price.rawAmount() * discountedPriceInPercentage.percentage();
    return EuroMoney.of(priceWithDiscount);
  }

  public BookId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public EuroMoney getPrice() {
    return price;
  }

  public BookType getBookType() {
    return bookType;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", bookType=" + bookType +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(id, book.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

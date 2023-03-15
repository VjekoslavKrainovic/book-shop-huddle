package com.huddle.huddle.book.shop.adapter.in.rest.book;

import com.huddle.huddle.book.shop.domain.book.Book;
import com.huddle.huddle.book.shop.domain.book.BookType;
import com.huddle.huddle.book.shop.domain.book.NewReleaseBookType;
import com.huddle.huddle.book.shop.domain.book.OldEditionBookType;
import com.huddle.huddle.book.shop.domain.book.RegularBookType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetBookDetailsResponse {

  private final Integer id;
  private final String name;
  private final Double price;
  private final GetBookDetailTypeResponse type;
  private final GetBookDetailsDiscountResponse discountDetails;

  private GetBookDetailsResponse(Integer id, String name, Double price,
      GetBookDetailTypeResponse type, GetBookDetailsDiscountResponse discountDetails) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.type = type;
    this.discountDetails = discountDetails;
  }

  public static GetBookDetailsResponse from(Book book) {
    GetBookDetailTypeResponse bookTypeResponse = GetBookDetailTypeResponse.from(book.getBookType());
    GetBookDetailsDiscountResponse discountResponse = GetBookDetailsDiscountResponse.from(book.getBookType());
    return new GetBookDetailsResponse(book.getId().id(),
        book.getName(),
        book.getPrice().rawAmount(),
        bookTypeResponse,
        discountResponse);
  }

}

@ToString
enum GetBookDetailTypeResponse {
  NEW_RELEASE, OLD_EDITION, REGULAR;

  public static GetBookDetailTypeResponse from(BookType bookType) {
    if (bookType instanceof NewReleaseBookType) {
      return GetBookDetailTypeResponse.NEW_RELEASE;
    } else if (bookType instanceof OldEditionBookType) {
      return GetBookDetailTypeResponse.OLD_EDITION;
    } else if (bookType instanceof RegularBookType) {
      return GetBookDetailTypeResponse.REGULAR;
    } else {
      throw new IllegalArgumentException("Non existing book type");
    }
  }

}

@Getter
@ToString
class GetBookDetailsDiscountResponse {

  private final Double defaultPricePercentage;
  private final Double bundlePricePercentage;
  private final Integer bundleOfBooksForDiscountPercentage;

  private GetBookDetailsDiscountResponse(Double defaultPricePercentage, Double bundlePricePercentage,
      Integer bundleOfBooksForDiscountPercentage) {
    this.defaultPricePercentage = defaultPricePercentage;
    this.bundlePricePercentage = bundlePricePercentage;
    this.bundleOfBooksForDiscountPercentage = bundleOfBooksForDiscountPercentage;
  }

  public static GetBookDetailsDiscountResponse from(BookType bookType) {
    return new GetBookDetailsDiscountResponse(bookType.getDefaultPrice().asRaw(),
        bookType.getBundlePrice().asRaw(),
        bookType.getBundleOfBooksForDiscount());
  }

}
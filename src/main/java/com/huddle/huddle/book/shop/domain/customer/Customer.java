package com.huddle.huddle.book.shop.domain.customer;

import com.huddle.huddle.book.shop.application.service.BookBundler;
import com.huddle.huddle.book.shop.domain.EuroMoney;
import com.huddle.huddle.book.shop.domain.book.Book;
import java.util.List;

public class Customer {

  private final CustomerId id;
  private EuroMoney balance;
  private LoyaltyPoints loyaltyPoints;

  public Customer(CustomerId id, EuroMoney balance, LoyaltyPoints loyaltyPoints) {
    this.id = id;
    this.balance = balance;
    this.loyaltyPoints = loyaltyPoints;
  }

  public void buyBooks(List<Book> booksToBuy) {

    BookBundler bookBundler = new BookBundler(booksToBuy);

    List<Book> newReleaseBooks = bookBundler.getNewReleaseBooks();
    List<Book> oldEditionBooks = bookBundler.getOldEditionBooks();
    List<Book> regularBooks = bookBundler.getRegularBooks();

    EuroMoney newReleaseBookPriceBundle = calculateBundlePriceForNewReleaseBooks(newReleaseBooks);
    EuroMoney oldEditionBooksPriceBundle = calculateBundlePriceForBooksWithLoyaltyPoints(oldEditionBooks);
    EuroMoney regularBooksPriceBundle = calculateBundlePriceForBooksWithLoyaltyPoints(regularBooks);

    EuroMoney totalBundlePrice = subtractBundleBookMoney(newReleaseBookPriceBundle, oldEditionBooksPriceBundle,
        regularBooksPriceBundle);

    this.balance = balance.subtract(totalBundlePrice);

    int bookToBuyCount = booksToBuy.size();
    this.loyaltyPoints = this.loyaltyPoints.addUp(LoyaltyPoints.of(bookToBuyCount));
  }

  private EuroMoney calculateBundlePriceForNewReleaseBooks(List<Book> newReleaseBooks) {
    double totalBundlePrice = calculateTotalBundlePrice(newReleaseBooks);
    return EuroMoney.of(totalBundlePrice);
  }

  private EuroMoney calculateBundlePriceForBooksWithLoyaltyPoints(List<Book> books) {

    double totalBundlePrice = 0;

    if (loyaltyPoints.enoughForFreeBook()) {
      totalBundlePrice = calculateTotalBundlePriceWithLoyaltyPoints(books);
    } else {
      totalBundlePrice = calculateTotalBundlePrice(books);
    }

    return EuroMoney.of(totalBundlePrice);
  }

  private double calculateTotalBundlePriceWithLoyaltyPoints(List<Book> books) {
    double totalBundlePrice = 0;
    int possibleFreeBooks = loyaltyPoints.possibleFreeBooks();

    if (possibleFreeBooks >= books.size()) {
      loyaltyPoints = loyaltyPoints.subtractForBookCount(books.size());
    } else {
      int paidBookCount = books.size() - possibleFreeBooks;
      totalBundlePrice = books.stream()
          .limit(paidBookCount)
          .map(book -> book.price(books.size()))
          .mapToDouble(EuroMoney::rawAmount)
          .sum();
      loyaltyPoints = loyaltyPoints.subtractForBookCount(possibleFreeBooks);
    }

    return totalBundlePrice;
  }

  private double calculateTotalBundlePrice(List<Book> books) {
    double totalBundlePrice;
    totalBundlePrice = books.stream()
        .map(book -> book.price(books.size()))
        .mapToDouble(EuroMoney::rawAmount)
        .sum();
    return totalBundlePrice;
  }

  private EuroMoney subtractBundleBookMoney(EuroMoney newReleaseBookPriceBundle, EuroMoney oldEditionBooksPriceBundle,
      EuroMoney regularBooksPriceBundle) {
    EuroMoney newReleaseAndOldEditionSum = newReleaseBookPriceBundle.addUp(oldEditionBooksPriceBundle);
    return newReleaseAndOldEditionSum.addUp(regularBooksPriceBundle);
  }

  public CustomerId getId() {
    return id;
  }

  public EuroMoney getBalance() {
    return balance;
  }

  public LoyaltyPoints getLoyaltyPoints() {
    return loyaltyPoints;
  }
}

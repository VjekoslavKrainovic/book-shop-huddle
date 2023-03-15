package com.huddle.huddle.book.shop.domain.customer;

public record LoyaltyPoints(Integer points) {

  private static final int POINTS_FOR_FREE_BOOK = 10;

  public static LoyaltyPoints of(Integer points) {
    return new LoyaltyPoints(points);
  }

  public LoyaltyPoints addUp(LoyaltyPoints loyaltyPointsToAdd) {
    Integer newLoyalityPointsBalance = this.points + loyaltyPointsToAdd.points;
    return LoyaltyPoints.of(newLoyalityPointsBalance);
  }

  public boolean enoughForFreeBook() {
    return points >= POINTS_FOR_FREE_BOOK;
  }

  public Integer possibleFreeBooks() {
    return points / POINTS_FOR_FREE_BOOK;
  }

  public LoyaltyPoints subtractForBookCount(Integer bookCount) {
    if (bookCount > possibleFreeBooks()) {
      throw new IllegalArgumentException("Not enough points for free book count of: " + bookCount);
    }
    Integer pointsToSubtract = bookCount * POINTS_FOR_FREE_BOOK;
    Integer subtractedPoints = points - pointsToSubtract;
    return LoyaltyPoints.of(subtractedPoints);
  }

}
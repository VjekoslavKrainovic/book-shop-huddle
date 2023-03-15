package com.huddle.huddle.book.shop.domain.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LoyaltyPointsTest {

  @Test
  void given_Loyalty_Points_Then_Add_Up() {
    // prepare
    LoyaltyPoints loyaltyPointsToAddUp = LoyaltyPoints.of(10);
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(10);

    // execute
    LoyaltyPoints loyaltyPointsAddedUp = loyaltyPoints.addUp(loyaltyPointsToAddUp);

    // verify
    assertThat(loyaltyPointsAddedUp.points()).isEqualTo(20);
  }

  @Test
  void given_Enough_Points_For_Free_Book_Then_Return_True_Case_One() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(10);

    // execute
    boolean isEnoughPointsForFreeBook = loyaltyPoints.enoughForFreeBook();

    // verify
    assertThat(isEnoughPointsForFreeBook).isTrue();
  }

  @Test
  void given_Enough_Points_For_Free_Book_Then_Return_True_Case_Two() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(15);

    // execute
    boolean isEnoughPointsForFreeBook = loyaltyPoints.enoughForFreeBook();

    // verify
    assertThat(isEnoughPointsForFreeBook).isTrue();
  }

  @Test
  void given_Not_Enough_Points_For_Free_Book_Then_Return_False() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(5);

    // execute
    boolean isEnoughPointsForFreeBook = loyaltyPoints.enoughForFreeBook();

    // verify
    assertThat(isEnoughPointsForFreeBook).isFalse();
  }

  @Test
  void given_Points_For_Zero_Book_Then_Return_Zero() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(5);

    // verify
    Integer possibleFreeBooks = loyaltyPoints.possibleFreeBooks();

    // verify
    assertThat(possibleFreeBooks).isZero();
  }

  @Test
  void given_Points_For_One_Book_Then_Return_One_Case_One() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(13);

    // verify
    Integer possibleFreeBooks = loyaltyPoints.possibleFreeBooks();

    // verify
    assertThat(possibleFreeBooks).isEqualTo(1);
  }

  @Test
  void given_Points_For_One_Book_Then_Return_One_Case_Two() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(10);

    // verify
    Integer possibleFreeBooks = loyaltyPoints.possibleFreeBooks();

    // verify
    assertThat(possibleFreeBooks).isEqualTo(1);
  }

  @Test
  void given_Points_For_Two_Book_Then_Return_One_Case_One() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(23);

    // verify
    Integer possibleFreeBooks = loyaltyPoints.possibleFreeBooks();

    // verify
    assertThat(possibleFreeBooks).isEqualTo(2);
  }

  @Test
  void given_Points_For_Two_Book_Then_Return_One_Case_Two() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(20);

    // verify
    Integer possibleFreeBooks = loyaltyPoints.possibleFreeBooks();

    // verify
    assertThat(possibleFreeBooks).isEqualTo(2);
  }

  @Test
  void given_Enough_Points_Then_Subtract_Points_Case_One() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(20);

    // verify
    LoyaltyPoints loyaltyPointsSubtracted = loyaltyPoints.subtractForBookCount(2);

    // verify
    assertThat(loyaltyPointsSubtracted.points()).isZero();
  }

  @Test
  void given_Enough_Points_Then_Subtract_Points_Case_Two() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(18);

    // verify
    LoyaltyPoints loyaltyPointsSubtracted = loyaltyPoints.subtractForBookCount(1);

    // verify
    assertThat(loyaltyPointsSubtracted.points()).isEqualTo(8);
  }

  @Test
  void given_Not_Enough_Points_Then_Subtract_Points() {
    // prepare
    LoyaltyPoints loyaltyPoints = LoyaltyPoints.of(8);

    // execute && verify
    assertThatThrownBy(() -> loyaltyPoints.subtractForBookCount(1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Not enough points for free book count of: 1");
  }

}
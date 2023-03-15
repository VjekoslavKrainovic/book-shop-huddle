package com.huddle.huddle.book.shop.adapter.out.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerDbo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Integer loyaltyPoints;
  private Double balance;

}
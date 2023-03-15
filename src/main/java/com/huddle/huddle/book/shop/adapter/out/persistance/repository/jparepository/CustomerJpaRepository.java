package com.huddle.huddle.book.shop.adapter.out.persistance.repository.jparepository;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.CustomerDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerDbo, Integer> {

}

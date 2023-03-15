package com.huddle.huddle.book.shop.adapter.out.persistance.repository.jparepository;

import com.huddle.huddle.book.shop.adapter.out.persistance.entity.BookDbo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookDbo, Integer> {

  List<BookDbo> findByIdIn(List<Integer> booksId);

}

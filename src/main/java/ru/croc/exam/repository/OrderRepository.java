package ru.croc.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Order;

import java.util.Set;


public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o.books from Order o where o.id = :id")
    Set<Book> getBooksForOrderId(@Param("id") Integer id);

    @Query("select o.sum from Order o where o.id = :id")
    Integer getSumByOrderId(Integer id);
}

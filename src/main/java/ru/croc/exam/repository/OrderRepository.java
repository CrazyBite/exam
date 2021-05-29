package ru.croc.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.exam.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

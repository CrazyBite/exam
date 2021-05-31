package ru.croc.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.exam.domain.OnlineOrder;

public interface OnlineOrderRepository extends JpaRepository<OnlineOrder, Integer> {
}

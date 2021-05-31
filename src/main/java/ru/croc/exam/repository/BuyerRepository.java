package ru.croc.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.exam.domain.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}

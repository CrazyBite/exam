package ru.croc.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.croc.exam.domain.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

    @Query("select b.age from Buyer b where b.id = :id")
    int getBuyerAgeById(@Param("id") Integer id);
}

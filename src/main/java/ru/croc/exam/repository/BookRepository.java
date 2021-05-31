package ru.croc.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.exam.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

package ru.croc.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.exam.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}

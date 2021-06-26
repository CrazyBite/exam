package ru.croc.exam.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b left join b.genres gs where gs.genre = :genre")
    List<Book> getBookByGenre(@Param("genre") Genre genre);

    @Query("select count(b) from Book b left join b.genres gs where gs.genre = :genre")
    Integer getCountBookByGenre(@Param("genre") Genre genre);

    @Query(value = "select b from Book b left join b.genres gs where gs.genre = :genre")
    List<Book> getBookByGenreNumber(@Param("genre") Genre genre, Pageable pageable);
}

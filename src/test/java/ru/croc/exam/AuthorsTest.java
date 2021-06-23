package ru.croc.exam;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yannbriancon.interceptor.HibernateQueryInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import ru.croc.exam.domain.Author;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Genre;
import ru.croc.exam.service.SearchService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@DirtiesContext
@SpringBootTest
@TestPropertySource(properties = {"spring.jpa.show-sql=true"})
public class AuthorsTest {

    /**
     * реализовать сервис.
     * Поиск по вхождению строки.
     * Поиск авторов отсортированных по кол-ву книг определённого занра
     */

    @Autowired
    SearchService service;

    @Autowired
    private HibernateQueryInterceptor hibernateQueryInterceptor;

    @ParameterizedTest
    @MethodSource("data_test1")
    public void test1(String name, int count) {
        hibernateQueryInterceptor.startQueryCount();

        List<Book> books = service.getBooksByAuthor(name);

        assertEquals(count, books.size());
        log.info(books.stream().map(Book::getName).collect(Collectors.joining()));
        log.info(books.stream().map(Book::toString).collect(Collectors.joining()));

        assertTrue(hibernateQueryInterceptor.getQueryCount() <= 5);
    }

    private static Stream<Arguments> data_test1(){
        return Stream.of(
                Arguments.of("Gold", 84),
                Arguments.of("Deaver", 27),
                Arguments.of("Staff", 14),
                Arguments.of("Norris", 13),
                Arguments.of("olden", 48),
                Arguments.of("ashley", 17),
                Arguments.of("acqueli", 28),
                Arguments.of("noman", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("data_test2")
    public void test2(Genre genre, int limit, int size) {
        hibernateQueryInterceptor.startQueryCount();

        List<Author> top = service.getTopWriterByGenre(genre, limit);

        assertEquals(Math.min(limit, size), top.size());

        assertTrue(hibernateQueryInterceptor.getQueryCount() <= 100, "" + hibernateQueryInterceptor.getQueryCount());
    }

    private static Stream<Arguments> data_test2(){
        return Stream.of(
                Arguments.of(Genre.FANTASY, 5, 1491),
                Arguments.of(Genre.ADVENTURE, 10, 1508),
                Arguments.of(Genre.ROMANCE, 20, 1538),
                Arguments.of(Genre.CONTEMPORARY, 1, 1522),
                Arguments.of(Genre.DYSTOPIAN, 100, 1536),
                Arguments.of(Genre.MYSTERY, 2000, 1556),
                Arguments.of(Genre.HORROR, Integer.MAX_VALUE, 1531),
                Arguments.of(Genre.THRILLER, 0, 1542)
        );
    }


}

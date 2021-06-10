package ru.croc.exam;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.BookGenre;
import ru.croc.exam.domain.Genre;
import ru.croc.exam.service.BooksService;

import java.util.List;
import java.util.stream.Stream;

@DirtiesContext
@SpringBootTest
@TestPropertySource(properties = {"spring.jpa.show-sql=false"})
public class FindBooksTest {

    @Autowired
    BooksService service;

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 500})
    public void test1(int size) {

        Genre fantasy = Genre.FANTASY;

        List<Book> booksByGenre = service.getBooksWithGenresByGenre(fantasy, size);

        assertEquals(size, booksByGenre.size());
        for (Book book : booksByGenre) {
            assertTrue(book.getGenres()
                    .stream()
                    .map(BookGenre::getGenre)
                    .anyMatch(fantasy::equals));
        }
    }

    @ParameterizedTest
    @MethodSource("data_method")
    public void test2(Genre genre, int count) {
        Integer booksByGenreCount = service.getBooksByGenreCount(genre);
        assertEquals(count, booksByGenreCount);
    }

    @Test
    public void test3() {
        List<Book> booksByGenre = service.getBooksByGenre(Genre.FANTASY);

        assertEquals(1879, booksByGenre.size());
    }

    private static Stream<Arguments> data_method(){
        return Stream.of(
                Arguments.of(Genre.FANTASY, 1879),
                Arguments.of(Genre.ADVENTURE, 1903),
                Arguments.of(Genre.ROMANCE, 1953),
                Arguments.of(Genre.CONTEMPORARY, 1912),
                Arguments.of(Genre.DYSTOPIAN, 1931),
                Arguments.of(Genre.MYSTERY, 1972),
                Arguments.of(Genre.HORROR, 1928),
                Arguments.of(Genre.THRILLER, 1974)
        );
    }

}

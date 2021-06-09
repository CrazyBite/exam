package ru.croc.exam;


import static org.junit.jupiter.api.Assertions.*;

import com.yannbriancon.interceptor.HibernateQueryInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.BookGenre;
import ru.croc.exam.domain.Genre;
import ru.croc.exam.service.BooksService;

import java.util.List;

@DirtiesContext
@SpringBootTest
@TestPropertySource(properties = {"spring.jpa.show-sql=true"})
public class FindBooksTest {

    @Autowired
    BooksService service;

    @Autowired
    private HibernateQueryInterceptor hibernateQueryInterceptor;

    @Test
    public void test1() {
        hibernateQueryInterceptor.startQueryCount();

        Genre fantasy = Genre.FANTASY;

        List<Book> booksByGenre = service.getBooksWithGenresByGenre(fantasy, 500);

        assertEquals(500, booksByGenre.size());
        for (Book book : booksByGenre) {
            assertTrue(book.getGenres()
                    .stream()
                    .map(BookGenre::getGenre)
                    .anyMatch(fantasy::equals));
        }
        assertTrue(hibernateQueryInterceptor.getQueryCount() <= 7);
    }

    @Test
    public void test2() {
        hibernateQueryInterceptor.startQueryCount();
        Integer booksByGenreCount = service.getBooksByGenreCount(Genre.FANTASY);
        assertEquals(1879, booksByGenreCount);
        assertTrue(hibernateQueryInterceptor.getQueryCount() <= 1);
    }

    @Test
    public void test3() {
        hibernateQueryInterceptor.startQueryCount();

        List<Book> booksByGenre = service.getBooksByGenre(Genre.FANTASY);

        for (Book book : booksByGenre) {
            assertTrue(book.getGenres().stream()
                    .anyMatch(bookGenre -> Genre.FANTASY.equals(bookGenre.getGenre())));
        }

        assertEquals(1879, booksByGenre.size());
        assertTrue(hibernateQueryInterceptor.getQueryCount() <= 2);
    }

}

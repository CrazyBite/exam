package ru.croc.exam.service;

import ru.croc.exam.domain.Author;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Genre;

import java.util.List;

public interface SearchService {

    List<Book> getBooksByAuthor(String string);

    List<Author> getTopWriterByGenre(Genre genre, int limit);

}

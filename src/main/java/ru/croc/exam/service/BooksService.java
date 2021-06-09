package ru.croc.exam.service;

import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Genre;

import java.util.List;

public interface BooksService {

    public List<Book> getBooksByGenre(Genre genre);

    public List<Book> getBooksWithGenresByGenre(Genre genre, Integer count);

    public Integer getBooksByGenreCount(Genre genre);

}

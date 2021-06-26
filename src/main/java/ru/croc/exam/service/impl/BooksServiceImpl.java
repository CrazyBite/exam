package ru.croc.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Genre;
import ru.croc.exam.repository.BookRepository;
import ru.croc.exam.service.BooksService;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookRepository.getBookByGenre(genre);
    }

    @Override
    public List<Book> getBooksWithGenresByGenre(Genre genre, Integer count) {
        PageRequest pageRequest = PageRequest.ofSize(count);
        return  bookRepository.getBookByGenreNumber(genre, pageRequest);
    }

    @Override
    public Integer getBooksByGenreCount(Genre genre) {
        return bookRepository.getCountBookByGenre(genre);
    }
}

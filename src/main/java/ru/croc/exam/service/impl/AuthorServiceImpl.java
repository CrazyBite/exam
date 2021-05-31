package ru.croc.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.exam.domain.Author;
import ru.croc.exam.repository.AuthorRepository;
import ru.croc.exam.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author create() {
        Author author = new Author();
        authorRepository.save(author);
        return author;
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return  authorRepository.findAll();
    }

    @Override
    public Author getOne(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found."));
    }
}

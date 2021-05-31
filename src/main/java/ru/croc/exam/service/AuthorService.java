package ru.croc.exam.service;

import ru.croc.exam.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public Author create();

    public void delete(Author author);

    public void save(Author author);

    public List<Author> getAll();

    public Author getOne(Integer id);
}

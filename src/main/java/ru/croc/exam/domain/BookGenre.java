package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name = "book_genres")
public class BookGenre {

    public BookGenre(Book book, Genre genre) {
        this.book = book;
        this.genre = genre;
    }

    @OneToOne
    private Book book;

    @Enumerated(EnumType.ORDINAL)
    private Genre genre;
}

package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {
    public Book(String name, Author author, Integer cost) {
        this.name = name;
        this.author = author;
        this.cost = cost;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Author author;

    private Integer cost;

    @ElementCollection
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"))
    private List<BookGenre> genres;
}

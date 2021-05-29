package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    private Integer cost;
}

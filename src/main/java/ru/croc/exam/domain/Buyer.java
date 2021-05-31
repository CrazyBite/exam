package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "buyers")
public class Buyer {

    public Buyer(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}

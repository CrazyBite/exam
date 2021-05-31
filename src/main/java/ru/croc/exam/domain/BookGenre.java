package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Embeddable
public class BookGenre {

    @Enumerated(EnumType.STRING)
    private Genre genre;
}

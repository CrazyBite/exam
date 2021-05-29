package ru.croc.exam.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
@Data
@Table(name = "book_order")
public class BookOrder implements Serializable {

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "order_id")
    private Long orderId;
}

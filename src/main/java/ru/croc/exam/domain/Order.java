package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    public Order(Buyer buyer, Integer sum) {
        this.buyer = buyer;
        this.sum = sum;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Timestamp createDate;

    @OneToOne
    @JoinColumn(name = "buyer")
    private Buyer buyer;

    private Integer sum;

}

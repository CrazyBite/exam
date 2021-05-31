package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @GeneratedValue
    private Integer id;

    @CreatedDate
    private Timestamp createDate;

    @OneToOne
    @JoinColumn(name = "buyer")
    private Buyer buyer;

    private Integer sum;

}

package ru.croc.exam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.JOINED)
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

    @ManyToMany
    @JoinTable(name = "book_order", joinColumns = @JoinColumn(name = "order_fk"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

}

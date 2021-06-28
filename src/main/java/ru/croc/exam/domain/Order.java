package ru.croc.exam.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.*;

import javax.persistence.*;

@Entity
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
    @JoinTable(name = "book_order",
            joinColumns = @JoinColumn(name = "order_fk"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "\nOrder{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", buyer=" + buyer +
                ", sum=" + sum +
                '}';
    }
}

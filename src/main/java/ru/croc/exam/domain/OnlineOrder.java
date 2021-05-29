package ru.croc.exam.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "online_orders")
public class OnlineOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String address;
}

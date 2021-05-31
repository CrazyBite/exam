package ru.croc.exam.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "online_orders")
public class OnlineOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private String address;
}

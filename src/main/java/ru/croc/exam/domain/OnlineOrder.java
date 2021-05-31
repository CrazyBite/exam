package ru.croc.exam.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "online_orders")
public class OnlineOrder extends Order {

    private String address;
}

package ru.croc.exam.service;

import ru.croc.exam.domain.Buyer;
import ru.croc.exam.domain.Order;

import java.util.Collection;

public interface OrderDataService {

    Order getOrderDataById(Integer id);

    Collection<Order> getOrdersById(Buyer buyer);

}

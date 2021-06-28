package ru.croc.exam.service;

import ru.croc.exam.domain.Author;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Buyer;
import ru.croc.exam.domain.Order;

import java.util.List;
import java.util.Set;

public interface OrderService {

    public Order createNewOrder(Buyer buyer);

    public Order getOrderById(Integer id);

    public Set<Book> getBooksForOrderId(Integer id);

    public Order addBook(Integer orderId, Book book);

    public Integer getSumByOrderId(Integer id);

}

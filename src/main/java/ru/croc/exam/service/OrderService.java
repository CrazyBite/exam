package ru.croc.exam.service;

import ru.croc.exam.domain.Author;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Buyer;
import ru.croc.exam.domain.Order;

import java.util.List;

public interface OrderService {

    public Order createNewOrder(Buyer buyer);

    public Order getOrderById(Integer id);

    public List<Book> getBooksForOrderId(Integer id);

    public Order addBook(Integer orderId, Book book);

    public Integer getSumByOrderId(Integer id);

}

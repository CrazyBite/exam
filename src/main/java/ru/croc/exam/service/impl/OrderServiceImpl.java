package ru.croc.exam.service.impl;

import lombok.Synchronized;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Buyer;
import ru.croc.exam.domain.Order;
import ru.croc.exam.repository.OrderRepository;
import ru.croc.exam.service.OrderService;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createNewOrder(Buyer buyer) {
        Order order = new Order();
        order.setBuyer(buyer);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.getById(id);
    }

    @Override
    @Transactional
    public Set<Book> getBooksForOrderId(Integer id) {
       return orderRepository.getBooksForOrderId(id);
    }

    @Override
    @Transactional
    @Synchronized
    public Order addBook(Integer id, Book book) {
        return addBookForSync(id, book);
    }

    @Override
    @Transactional
    public Integer getSumByOrderId(Integer id) {
        Integer sum = orderRepository.getSumByOrderId(id);
        return sum != null ? sum : 0;
    }

    private Order addBookForSync(Integer id, Book book) {
        Order order = orderRepository.findById(id).get();
        Set<Book> books = order.getBooks();
        books.add(book);
        order.setBooks(books);
        order.setSum(books.stream().map(Book::getCost).reduce(0, Integer::sum));
        return orderRepository.save(order);
    }
}

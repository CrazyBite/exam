package ru.croc.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.Order;
import ru.croc.exam.repository.BookRepository;
import ru.croc.exam.repository.BuyerRepository;
import ru.croc.exam.repository.OrderRepository;
import ru.croc.exam.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "spring.jpa.show-sql=false",
        "spring.jpa.properties.hibernate.generate_statistics=false",
})
public class OrderTest {

    /**
     * Реализовать методы сервиса OrderService
     * Добавление кники с пересчётом суммы заказа
     */

    @Rule
    public ConcurrentRule concurrently = new ConcurrentRule();
    @Rule
    public RepeatingRule rule = new RepeatingRule();

    static Random rnd = new Random();
    static OrderService service;
    static BookRepository bookRepository;
    static OrderRepository orderRepository;

    static Map<Order, Integer> ordersSum = new ConcurrentHashMap<>();
    static Map<Order, Integer> ordersCount = new ConcurrentHashMap<>();
    static List<Integer> ids = new ArrayList<>();

    @Autowired
    public void prepare(BuyerRepository buyerRepository, BookRepository bookRepository, OrderRepository orderRepository, OrderService service) {
        for (int i = 0; i < 20; i++) {
            Order newOrder = service.createNewOrder(buyerRepository.getById(72));
            ordersSum.put(newOrder, 0);
            ordersCount.put(newOrder, 0);
            ids.add(newOrder.getId());

        }

        OrderTest.service = service;
        OrderTest.bookRepository = bookRepository;
        OrderTest.orderRepository = orderRepository;
    }

    @Test
    @Concurrent(count = 20)
    @Repeating(repetition = 20)
    public void runsMultipleTimes() {
        Integer id = ids.get(rnd.nextInt(ids.size()));
        Book book = bookRepository.findById(rnd.nextInt(15452) + 1).get();
        Order order = service.addBook(id, book);
        ordersSum.put(order, ordersSum.get(order) + book.getCost());
        ordersCount.put(order, ordersCount.get(order) + 1);
    }

    @AfterClass
    public static void assertAndClean() {
        try {
            ordersSum.forEach((order, sum) -> {
                assertEquals(sum, service.getSumByOrderId(order.getId()));
            });
            ordersCount.forEach((order, count) -> {
                assertEquals(count, service.getBooksForOrderId(order.getId()).size());
            });
        } finally {
            orderRepository.deleteAllByIdInBatch(ids);
        }

    }

}

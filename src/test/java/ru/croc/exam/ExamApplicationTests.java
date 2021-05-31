package ru.croc.exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.croc.exam.domain.*;
import ru.croc.exam.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
class ExamApplicationTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OnlineOrderRepository onlineOrderRepository;

    @Test
    public void testEnitities(){
        Author author = new Author("Ivan", "Ivanovich", "Ivanov");
        authorRepository.save(author);
        Assertions.assertNotNull(authorRepository.findById(author.getId()));

        Book book = new Book("Book1", author, 99);
        ArrayList<BookGenre> genresList = new ArrayList<>();
        BookGenre genre = new BookGenre();
        genre.setGenre(Genre.HORROR);
        genresList.add(genre);
        book.setGenres(genresList);

        bookRepository.save(book);

        Assertions.assertNotNull(bookRepository.findById(book.getId()));

        Buyer buyer = new Buyer("Vasiliy");
        buyerRepository.save(buyer);
        Assertions.assertNotNull(buyerRepository.findById(buyer.getId()));

        Order order = new Order(buyer, 999);
        orderRepository.save(order);
        Assertions.assertNotNull(orderRepository.findById(order.getId()));

        OnlineOrder onlineOrder = new OnlineOrder();
        onlineOrder.setAddress("223");
        onlineOrder.setBuyer(buyer);
        onlineOrder.setSum(111);
        onlineOrder.setBooks(Collections.singletonList(book));

        onlineOrderRepository.save(onlineOrder);
        Assertions.assertNotNull(onlineOrderRepository.findById(onlineOrder.getId()));

    }

}

package ru.croc.exam;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yannbriancon.interceptor.HibernateQueryInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import ru.croc.exam.domain.Buyer;
import ru.croc.exam.service.BuyerService;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@DirtiesContext
@SpringBootTest
@TestPropertySource(properties = {"spring.jpa.show-sql=true"})
public class ConstraintsTest {

    @Autowired
    BuyerService service;

    /**
    * Надо написать скрипт миграции. Колонка not null age для всех покупателей.
     * По умолчанию заполнить -1
     *
     * Добавить валидацию на возраст (18; 99)
     * Добавить валидацию на имя - не пустое
    * */

    @Test
    public void test1() {
        int buyerAge = service.getBuyerAgeById(11);
        assertEquals(-1, buyerAge);
    }

    @Test
    public void test2() {
        assertThrows(RuntimeException.class, () -> service.getBuyerAgeById(1002));
    }

    @Test
    @Transactional
    public void test3() {
        Buyer buyer = new Buyer();

        buyer.setName("Vlad");
        buyer.setAge(23);

        Buyer saved = service.saveNewBuyer(buyer);

        assertEquals(buyer.getAge(), saved.getAge());
        assertEquals(buyer.getName(), saved.getName());
    }

    @Test
    public void test4() {
        Buyer buyer = new Buyer();

        buyer.setName("Vlad");

        Throwable exception = assertThrows(RuntimeException.class, () -> service.saveNewBuyer(buyer));
        List<Class> exceptionList = new ArrayList<>();
        do {
            exceptionList.add(exception.getClass());
            exception = exception.getCause();
        } while (exception != null);

        assertTrue(exceptionList.contains(ConstraintViolationException.class));
    }

    @Test
    public void test5() {
        Buyer buyer = new Buyer();

        buyer.setName("Vlad");
        buyer.setAge(13);

        Throwable exception = assertThrows(RuntimeException.class, () -> service.saveNewBuyer(buyer));
        List<Class> exceptionList = new ArrayList<>();
        do {
            exceptionList.add(exception.getClass());
            exception = exception.getCause();
        } while (exception != null);

        assertTrue(exceptionList.contains(ConstraintViolationException.class));
    }

    @Test
    public void test6() {
        Buyer buyer = new Buyer();

        buyer.setName("");
        buyer.setAge(99);

        Throwable exception = assertThrows(RuntimeException.class, () -> service.saveNewBuyer(buyer));
        List<Class> exceptionList = new ArrayList<>();
        do {
            exceptionList.add(exception.getClass());
            exception = exception.getCause();
        } while (exception != null);

        assertTrue(exceptionList.contains(ConstraintViolationException.class));
    }

}

package ru.croc.exam;


import static org.junit.jupiter.api.Assertions.assertFalse;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.yannbriancon.interceptor.HibernateQueryInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import ru.croc.exam.domain.Book;
import ru.croc.exam.domain.OnlineOrder;
import ru.croc.exam.domain.Order;
import ru.croc.exam.repository.BuyerRepository;
import ru.croc.exam.service.OrderDataService;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@DirtiesContext
@SpringBootTest
@TestPropertySource(properties = {"spring.jpa.show-sql=true", "spring.jpa.properties.hibernate.generate_statistics=false"})
public class OrderDataTest {

    @Autowired
    OrderDataService service;

    private Logger logger;

    @Mock
    private Appender mockedAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;

    @Autowired
    private HibernateQueryInterceptor hibernateQueryInterceptor;

    @Autowired
    private BuyerRepository repository;

    @BeforeEach
    public void setup() {
        logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

        logger.addAppender(mockedAppender);
    }

    @Transactional
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 136, 500})
    public void test1(int id) {
        hibernateQueryInterceptor.startQueryCount();

        Order orderDataById = service.getOrderDataById(id);

        logger.info(orderDataById.getBuyer().getName());

        logger.info(orderDataById.getBooks()
                .stream().map(Book::getName).collect(Collectors.joining("; ")));

        logger.info("{}", orderDataById.getSum());

        if (orderDataById instanceof OnlineOrder) {
            logger.info("{}", ((OnlineOrder) orderDataById).getAddress());
        }

        Mockito.verify(mockedAppender, Mockito.atLeast(0)).doAppend(loggingEventCaptor.capture());
        loggingEventCaptor.getAllValues().forEach(loggingEvent -> {
                    assertFalse(loggingEvent.getMessage()
                            .contains("N+1 queries detected"));
                }
        );
    }

    @Transactional
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 136, 500, 744})
    public void test2(int id) {

        Collection<Order> ordersById = service.getOrdersByBuyer(repository.getById(id));

        for (Order order : ordersById) {
            logger.info(order.getBuyer().getName());

            logger.info(order.getBooks()
                    .stream().map(Book::getName).collect(Collectors.joining("; ")));

            logger.info("{}", order.getSum());

            if (order instanceof OnlineOrder) {
                logger.info("{}", ((OnlineOrder) order).getAddress());
            }
        }

        Mockito.verify(mockedAppender, Mockito.atLeast(0)).doAppend(loggingEventCaptor.capture());
        loggingEventCaptor.getAllValues().forEach(loggingEvent -> {
                    assertFalse(loggingEvent.getMessage()
                            .contains("N+1 queries detected"));
                }
        );
    }

}

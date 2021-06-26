package ru.croc.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.exam.domain.Buyer;
import ru.croc.exam.repository.BuyerRepository;
import ru.croc.exam.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Buyer saveNewBuyer(Buyer buyer) {
       return buyerRepository.save(buyer);
    }

    @Override
    public int getBuyerAgeById(Integer id) {
        return buyerRepository.getBuyerAgeById(id);
    }
}

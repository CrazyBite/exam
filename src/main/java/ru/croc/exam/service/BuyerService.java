package ru.croc.exam.service;

import ru.croc.exam.domain.Buyer;


public interface BuyerService {

    public Buyer saveNewBuyer(Buyer buyer);

    public int getBuyerAgeById(Integer id);

}

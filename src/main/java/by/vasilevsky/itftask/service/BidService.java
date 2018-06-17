package by.vasilevsky.itftask.service;

import by.vasilevsky.itftask.entity.Bid;

public interface BidService {

    Bid register(Bid bid);

    Bid findById(Long id);
}

package by.vasilevsky.itftask.service;

import by.vasilevsky.itftask.dao.BidDao;
import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.entity.BidStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService{

    private BidDao bidDao;

    @Autowired
    public BidServiceImpl(BidDao bidDao) {
        this.bidDao = bidDao;
    }

    @Override
    public Bid register(Bid bid) {
        if (bid == null) {
            throw new IllegalArgumentException("Bid can not be null");
        }

        bid.setBidStatus(BidStatus.QUEUE);

        return bidDao.save(bid);
    }

    @Override
    public Bid findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }

        return bidDao.findBidById(id);
    }
}

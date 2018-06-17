package by.vasilevsky.itftask.controller;

import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bids")
public class BidController {

    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public Bid registerBid(@RequestBody Bid bid) {

        return bidService.register(bid);
    }

    @GetMapping("/{bidId}")
    public Bid findById(@PathVariable(name = "bidId") Long bidId) {

        return bidService.findById(bidId);
    }
}

package by.vasilevsky.itftask.service;

import by.vasilevsky.itftask.dao.BidDao;
import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.entity.BidStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BidServiceImplTest {
    private static final Bid GIVEN_BID = new Bid(
            "request",
            new BigDecimal(111),
            LocalDate.of(2018, 5, 5),
            null
    );

    private BidService bidService;

    @Mock
    private BidDao bidDao;

    @Before
    public void setUp() throws Exception {

        bidService = new BidServiceImpl(bidDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerWithNullArgumentTest() throws Exception {

        bidService.register(null);
    }

    @Test
    public void registerNewLegalBidCheckBidStatus() throws Exception {
        ArgumentCaptor<Bid> argument = ArgumentCaptor.forClass(Bid.class);
        bidService.register(GIVEN_BID);

        verify(bidDao).save(argument.capture());

        assertEquals(BidStatus.QUEUE, argument.getValue().getBidStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdWithNullArgument() throws Exception {
        bidService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdWithZeroArgument() throws Exception {
        bidService.findById(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdWithNegativeArgument() throws Exception {
        bidService.findById(-1L);
    }
}
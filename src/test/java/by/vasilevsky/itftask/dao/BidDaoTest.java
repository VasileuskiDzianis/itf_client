package by.vasilevsky.itftask.dao;

import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.entity.BidStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application_test.properties")
public class BidDaoTest {

    private static final String GIVEN_BID_REQUEST = "bid request";
    private static final BigDecimal GIVEN_BID = new BigDecimal(99.90, new MathContext(6));
    private static final LocalDate GIVEN_DUE_DATE = LocalDate.of(2018, 06, 06);
    @Autowired
    private BidDao bidDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(false)
    public void crudTest() throws Exception {
        Bid givenBid = new Bid(
                GIVEN_BID_REQUEST,
                GIVEN_BID,
                GIVEN_DUE_DATE,
                BidStatus.QUEUE
        );


        bidDao.save(givenBid);

        assertTrue(givenBid.getId() > 0);

        Bid storedBid = bidDao.findBidById(givenBid.getId());

        assertNotNull(storedBid);
        assertEquals(givenBid, storedBid);

        storedBid.setBidStatus(BidStatus.REJECTED);

        bidDao.save(storedBid);

        Bid updatedStoredBid = bidDao.findBidById(givenBid.getId());

        assertNotNull(updatedStoredBid);
        assertEquals(updatedStoredBid, storedBid);

        bidDao.deleteById(givenBid.getId());

        Bid deletedBid = bidDao.findBidById(givenBid.getId());

        assertNull(deletedBid);
    }
}
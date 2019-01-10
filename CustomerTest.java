import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the Customer class.
 *
 * @author  Basit Balogun
 * @version 10/14/2018
 */
public class CustomerTest
{
    @Test
    public void testSetDepature(){//test setDepature method
        Customer c = new Customer(21500,1);
        c.setDepature(21600);
        assertEquals(c.depatureTime,21600);
    }
    
    @Test
    public void testSetTimeServed(){//test setTimeServed method
        Customer c = new Customer(21500,1);
        c.setTimeServed(21650);
        assertEquals(c.timeServed,21650);
    }
    
    @Test
    public void testWaitTime(){//test waitTime method
        Customer c = new Customer(21500,1);
        c.setTimeServed(21650);
        int wait = 21650 - 21500;//calculates the waitTime for the customer 
        assertEquals(c.waitTime(),wait);//compare customer wait time to the calculated wait time 
    }
    
    @Test
    public void testGetIndex(){//test getIndex method
        Customer c = new Customer(21500,3);
        assertEquals(c.index,3);//assert that customer index is equal to the given index 
    }
}
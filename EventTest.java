

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the Event class.
 *
 * @author  Basit Balogun
 * @version 10/14/2018
 */
public class EventTest
{
    @Test
    public void testSetTime(){//test setTime method
        Customer c = new Customer(21500,1);
        Event e = new Event(c, 1);
        e.setTime(21700);

        assertEquals(e.time,21700);//assert that the event time is equal to the time we set it to be 
    }
    
    @Test
    public void testSetType(){//test setType method
        Customer c = new Customer(21500,1);
        Event e = new Event(c, 1);
        e.setType(2);
        assertEquals(e.type,2);//assert that the event type is equal to the type we set it to be 
    }
    
    @Test
    public void testCompareTo1(){//test compareTo method with both events being arrivals 
        Customer c1 = new Customer(21500,1);
        Event e1 = new Event(c1, 1);
        
        Customer c2 = new Customer(21600,1);
        Event e2 = new Event(c2, 1);
        
        int compare = e1.compareTo(e2);
        
        assertEquals(compare,-1);//assert that the event time is equal to the time we set it to be 
    }
    
    @Test
    public void testCompareTo2(){//test compareTo method with both events being depature 
        Customer c1 = new Customer(21700,1);
        Event e1 = new Event(c1, 2);
        
        Customer c2 = new Customer(21600,1);
        Event e2 = new Event(c2, 2);
        
        int compare = e1.compareTo(e2);
        
        assertEquals(compare,1);//assert that the event time is equal to the time we set it to be 
    }
    
    @Test
    public void testCompareTo3(){//test compareTo method both arrival and depature events
        Customer c1 = new Customer(21200,2);
        Event e1 = new Event(c1, 2);
        
        Customer c2 = new Customer(21500,1);
        Event e2 = new Event(c2, 2);
        
        int compare = e1.compareTo(e2);
        
        assertEquals(compare,-1);//assert that the event time is equal to the time we set it to be 
    }
    
    @Test
    public void testCompareTo4(){//test compareTo method for the same customer 
        Customer c1 = new Customer(21200,2);
        Event e1 = new Event(c1, 2);
        
        Event e2 = new Event(c1, 1);
        
        int compare = e1.compareTo(e2);
        
        assertEquals(compare,0);//assert that the event time is equal to the time we set it to be 
    }
}

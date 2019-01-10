
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the CoffeeShop class.
 *
 * @author  Basit Balogun
 * @version 10/14/2018
 */
public class CoffeeShopTest
{
    @Test
    public void testReadFile1(){//test readFile method for all non-event contents
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        assert(shop.profit - 2.00 == 0);//asserts that the shop's profit is equal to the profit in the input file
        assert(shop.cpd - 300.00== 0);//asserts that the shop's cost per day of each staff is equal to the cost per day of the input file
        assert(shop.avgTime - 120== 0);//asserts that the shop's average service time is equal to the service time given in the input file
    }

    @Test
    public void testReadFile2(){//test readFile method to make sure events and customers are being added to the pQueue and arrayList
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        assert(shop.pQue.peek() != null);//if priority queue is not empty then events have been added 
        assert(shop.arrayList.get(0) != null);

    }

    @Test
    public void testReadFile3(){//test readFile method to make sure all events and customers are being added 
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        int noEvents = 1054; //total number of customer and events in the input file
        assert(shop.pQue.size() == 1054); 
        assert(shop.arrayList.size() == 1054);

    }

    @Test
    public void testAddToQueue1(){//tests the add to Queue method  when queue is empty
        CoffeeShop shop = new CoffeeShop("input.txt");
        Customer c1 = new Customer(21500,1);
        shop.readFile();
        Event e = new Event(c1, 1);
        shop.no_Cashiers = 2;//fix number of cahiers for testing purposes
        shop.addToQueue(e);
        assert(shop.deque.size() == 1);//if element is added deque size would be 1 

    }

    @Test
    public void testAddToQueue2(){//tests the add to Queue method  when queue is full
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        Customer c1 = new Customer(21500,1);
        Event e = new Event(c1, 1);
        shop.no_Cashiers = 1;//fix number of cahiers for testing purposes

        //fill up the queue
        shop.deque.add(e);
        shop.deque.add(e);
        shop.deque.add(e);
        shop.deque.add(e);
        shop.deque.add(e);
        shop.deque.add(e);
        shop.deque.add(e);
        shop.deque.add(e);

        Customer c2 = new Customer(21555,1);
        Event e2 = new Event(c2, 1);

        shop.addToQueue(e2);
        assert(shop.deque.peekLast().toString().compareTo(e2.toString()) != 0);//assert that e2 was not added to the queue
    }
    
    @Test
    public void testServe1(){// tests the serve method to ensure that the event was change to depature 
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        Customer c1 = new Customer(21500,1);
        Event e = new Event(c1, 1);
        
        shop.serve(e);
        assert(e.type == 2);//assert that the event is now a depature 
    
    }
    
    @Test
    public void testServe2(){// tests the serve method to ensure that the customer is now served 
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        Customer c1 = new Customer(21500,1);
        Event e = new Event(c1, 1);
        
        shop.serve(e);
        assertTrue(shop.arrayList.get(e.customerIndex -1).isServed);//assert that the customer is served    
    }
    
    @Test
    public void testServe3(){// tests the serve method to ensure that the customer is depatureTime and timeServed has been change
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        Customer c1 = new Customer(21500,1);
        Event e = new Event(c1, 1);
        
        shop.serve(e);
        assertTrue(shop.arrayList.get(e.customerIndex -1).depatureTime != 0);
        assertTrue(shop.arrayList.get(e.customerIndex -1).timeServed != 0);
    }
    
    @Test
    public void testIsOpen1(){//test the isOpen method when the event happens during open time 
        CoffeeShop shop = new CoffeeShop("input.txt");
        shop.readFile();
        Customer c1 = new Customer(22000,1);
        Event e = new Event(c1, 1);
        
        assert(shop.isOpen(e));//assert that the shop is open during this time 
    
    }
    
    @Test
    public void testIsOpen2(){//test the isOpen method when the event happens when the store is not open 
        CoffeeShop shop = new CoffeeShop("input.txt");
        Customer c1 = new Customer(21000,1);
        Event e = new Event(c1, 1);
        
        assert(!shop.isOpen(e));//assert that the shop is not open during this time 
    
    }
    
    @Test
    public void testIsOpen3(){//test the isOpen method when the event is a depature 
        CoffeeShop shop = new CoffeeShop("input.txt");
        Customer c1 = new Customer(43000,1);
        Event e = new Event(c1, 2);
        
        assertFalse(shop.isOpen(e));//assert that the shop doesnt open for people departing 
    
    }
    
    @Test
    public void testTotalProfit(){//test the total profit method 
        CoffeeShop  shop = new CoffeeShop("input.txt");
        shop.simulation();//input 2 as the number of cashiers for testing purposes 
        float totalProfit = 1674;//actual total profit when there are 2 cashiers
        
        assert(shop.totalProfit() - totalProfit == 0);//assert that the profit gotten is the same as what its supposed to be 
    }
    
    @Test
    public void testAvgWaitTime(){//test the avgWaitTime method 
        CoffeeShop  shop = new CoffeeShop("input.txt");
        shop.simulation();//input 2 as the number of cashiers for testing purposes 
        int avgWT = 575;//actual average waittime when there are 2 cashiers        
        assert(shop.avgWaitTime() - avgWT == 0);//assert that the average wait time is the same as what its supposed to be 
    }
    
    @Test
    public void testmaxWaitTime(){//test the maxWaitTime method 
        CoffeeShop  shop = new CoffeeShop("input.txt");
        shop.simulation();//input 2 as the number of cashiers for testing purposes 
        int maxWT = 959;//actual average waittime when there are 2 cashiers        
        assert(shop.maxWaitTime() - maxWT == 0);//assert that the max wait time is the same as what its supposed to be 
    }
    
    @Test
    public void testOverflowRate(){//test the overflowRate method 
        CoffeeShop  shop = new CoffeeShop("input.txt");
        shop.simulation();//input 2 as the number of cashiers for testing purposes 
        String oRate = "20.59%";//actual overflow rate  when there are 2 cashiers        
        assert(shop.overflowRate().compareTo(oRate) == 0);//assert that the overflow rate is the same as what its supposed to be 
    }
    
    
    @Test
    public void testNetProfit(){//test the netProfit method 
        CoffeeShop  shop = new CoffeeShop("input.txt");
        shop.simulation();//input 2 as the number of cashiers for testing purposes 
        float netProfit = 1074;//actual netProfit when there are 2 cashiers        
        assert(shop.netProfit() - netProfit == 0);//assert that the netProfit is the same as what its supposed to be 
    }
    
    @Test
    public void testTotalCost(){//test the total Cost method 
        CoffeeShop  shop = new CoffeeShop("input.txt");
        shop.simulation();//input 2 as the number of cashiers for testing purposes 
        float totalCost = 600;//actual totalProfit when there are 2 cashiers        
        assert(shop.totalCost() - totalCost == 0);//assert that the totalCost is the same as what its supposed to be 
    }
}

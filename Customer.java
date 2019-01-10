
/**
 * This class is used to create a customer, and store and set the customer's data
 *
 * @author Basit Balogun
 * @version 10/9/2018
 */
public class Customer 
{
    int arrivalTime;
    int index;
    int depatureTime;
    boolean isServed;
    int waitTime;
    int timeServed;//time the customer starts getting served

    /**Constructor for Customer class*/ 
    public Customer(int t, int index ){//index keeps track of the customer number 
        this.arrivalTime = t;
        this.index = index;
        this.isServed = false;
    }

    /**Setter for depature time*/ 
    public void setDepature(int x){
        this.depatureTime = x;
    }

    /**Setter for time served*/ 
    public void setTimeServed(int x){
        this.timeServed = x;
    }

    /**Calculates and returns wait time*/ 
    public int waitTime(){
        return waitTime = timeServed - arrivalTime ;
    }

    /**Returns customerIndex*/ 
    public int getIndex(){
        return index;
    }

    /**Returns all customer info as a string.*/ 
    public String toString(){//was used for testing purposes
        return index + " Arrival Time: " + arrivalTime + " Depature Time: " + depatureTime+ " is Served: " + isServed+ " time served: "+ timeServed ;
    } 

}

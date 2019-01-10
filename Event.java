import java.util.*;
/**
 * This class creates an object of event which keeps track of who the customer is, event time and type of event
 * Event type 1 is an arrival while event type 2 is a depature.
 * @author Basit Balogun
 * @version (10/9/2018)
 */
public class Event implements Comparable<Event>
{
    // instance variables - replace the example below with your own
    int time;
    int customerIndex;
    int type;
    /** Constructor for objects of class Event */
    public Event(){
    }

    public Event(Customer c, int type)//type 1 represents arrival and type 2 represents depature
    {
        this.time = c.arrivalTime;
        this.customerIndex = c.getIndex();
        this.type = type; 
    }
    
    /**Setter for time event occurs */
    void setTime(int t){
        this.time = t;
    }

    /**Setter for event type */
    void setType(int type){
        this.type = type; 
    }

    /**comparator for events */
    public int compareTo(Event e){
        if (time > e.time)return 1;
        if (time < e.time) return -1;
        else return 0;

    }

    /**Returns event details as a String */
    public String toString(){//was used for testing purposes
        return "Customer Index: " +customerIndex + " event time: " + time + " event type: " +type;
    } 
}
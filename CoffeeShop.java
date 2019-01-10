import java.util.*;
import java.io.*;
/**
 * This class handles the simulation of the coffee shop and calculates all the necessary data
 *
 * @author Basit Balogun
 * @version 10/12/2018
 */
public class CoffeeShop
{
    //Instance Variables
    Scanner sc;
    FileReader entryTimes;//input File
    int no_Cashiers;//number of Cashiers
    PriorityQueue<Event> pQue;
    ArrayList<Customer> arrayList;
    ArrayDeque<Event> deque;//implemented as a queue
    float profit; //profit per customer
    float cpd; //cost per day of each staff
    int avgTime;//average time to serve a customer

    /**Constructor for objects of class CoffeeShop. It takes in the input file of customer entry times gets number of cashiers*/
    public CoffeeShop(String f){
        try{
            entryTimes = new FileReader(f);            
        }
        catch(Exception e) {
            System.out.println(e);//catch exception when inputs do not match required data type 
        }
    }

    /**Gets information from the input file and adds the times and customers to the priority queue and arrayList respectively*/
    public void readFile(){
        sc = new Scanner(entryTimes);
        profit = Float.valueOf(sc.nextLine());//profit per customer
        cpd = Float.valueOf(sc.nextLine());//cost per day of each staff
        avgTime = Integer.valueOf(sc.nextLine());//average time to serve a customer

        //initialize queue and arrayList 
        pQue = new PriorityQueue<Event>();
        arrayList = new ArrayList<Customer>();
        //initialize queue
        deque = new ArrayDeque<Event>();

        //add all customer entry times to the priority queue of events
        int customerIndex = 1;//keeps track of the customer 
        while (sc.hasNextLine()){

            String[] timeA = sc.nextLine().split(":| ");

            int hr = Integer.valueOf(timeA[0]);
            int min = Integer.valueOf(timeA[1]);
            int sec = Integer.valueOf(timeA[2]);
            int time = (hr * 60 * 60) + (min * 60) + sec;//converts arrival time to seconds 
            Customer c = new Customer(time, customerIndex++);
            Event e = new Event(c, 1);
            arrayList.add(c);// adds customer to the array list of customers
            pQue.add(e);// adds event to the priority queue 
        }
    }

    /**Runs the simulation */
    public void simulation(){
        //Ask for number of cashiers
        sc = new Scanner(System.in);
        System.out.println("Input Number of Cashiers");
        no_Cashiers = sc.nextInt();

        readFile();//gets info from input file and adds the times and customers to the priority queue and arrayList respectively 

        Event e = new Event();//An empty event;
        int cashiersFree = no_Cashiers;

        while (pQue.peek() != null){//while the Priority Queue is not empty
            e = pQue.poll();//remove the head of the priority Queue

            if(isOpen(e)){// if event is an arrival when shop is open
                if (cashiersFree > 0){//if a cashier is available
                    cashiersFree--; //reduce the number of cashiers by 1
                    serve(e);//serve the customer;
                }
                else if(cashiersFree == 0){//if there are no cashiers available
                    addToQueue(e);//add event to queue 
                }
            }

            else if(e.type == 2){//if event is a depature
                if(cashiersFree < no_Cashiers)cashiersFree++;//increase number of cashier available only when cashiers available is less that the total number of cashiers
                int newTime = e.time;//new Time is the time the depature occured 
                if (deque.peek() != null){//if que is not empty
                    e = deque.poll();//remove the head of the Queue
                    e.setTime(newTime);//change the time of the event to the time the last customer departed
                    cashiersFree--;
                    serve(e);//serves the customer in the queue
                }
            }

        }
    }

    /**This method adds event to queue when the queue is not full */
    public void addToQueue(Event e){
        int maxSize = 8 * no_Cashiers;//maximum number of events the queue can hold
        int dequeSize = deque.size();//number of events in the array
        if(dequeSize >= maxSize){//if queue is full
            //System.out.println("Customer " + e.customerIndex + " has been turned away with type "+ e.type);
        } 
        else {
            deque.add(e);
            //System.out.println("Customer " + e.customerIndex + " has been added to que with type "+ e.type);
        }
    }

    /**This method handles the serving of a customer */
    public void serve(Event e){
        e.setTime(e.time + avgTime);//change time to depature time.
        e.setType(2);//change event type to depature  
        pQue.add(e);//adds the new depature event back to priority Queue

        //set depature time of the  customer and time served
        Customer c = arrayList.get(e.customerIndex -1);
        c.setDepature(e.time);
        c.setTimeServed(e.time - avgTime);
        c.isServed = true;
        arrayList.set(e.customerIndex-1, c);
    }

    /**Checks if CoffeeShop is Open for new customer arrivals*/
    public boolean isOpen(Event e){
        int openTime = 21600; 
        int closeTime = 79200;
        //Checks if Customer arrives when Store is close
        if (e.time < openTime || e.time > closeTime){
            //System.out.println("Customer " + e.customerIndex + " has been turned away");
            return false;
        }
        else if (e.type == 2)return false;
        else return true;
    }

    /** Returns total daily profit before cost deductions */
    public float totalProfit(){
        int total = 0;
        for (int i=0; i < arrayList.size();i++){
            Customer c = arrayList.get(i);
            if(c.isServed)total += profit;//if customer is served add profit per customer to total profit
        }
        return total;
    }

    /** Returns the average wait time of the customers */
    public int avgWaitTime(){
        int waitTime = 0;
        int avgWT= 0;
        int notServed = 0;//Number of customers not served 
        int no_Customers = arrayList.size();//total number of customers

        for (int i=0; i < arrayList.size();i++){
            Customer c = arrayList.get(i);
            if(c.isServed)waitTime += c.waitTime();//if customer is served add the customer's wait time to wait time
            else notServed++;
        }        
        avgWT = waitTime/(no_Customers - notServed);//Average Wait time
        return avgWT;
    }

    /** Returns the maximum wait time of the customers */
    public int maxWaitTime(){
        int maxWait = 0;
        for (int i=0; i < arrayList.size();i++){
            Customer c = arrayList.get(i);
            if(c.isServed && c.waitTime() > maxWait)maxWait = c.waitTime;
        }
        return maxWait;
    }

    /**Returns the overflow Rate as a String */
    public String overflowRate(){
        String rateS = "";
        float rate= 0;
        int notServed = 0;
        int no_Customers = arrayList.size();//total number of customers

        for (int i=0; i < arrayList.size();i++){
            Customer c = arrayList.get(i);
            if(!c.isServed)notServed++; //if customer is not served increase notServed      
        }  
        rate = ((float)notServed/(float)no_Customers)*100;
        rateS = String.format("%.2f", rate)+ "%";
        return rateS;
    }

    /** Returns net Profit */
    public float netProfit(){
        return totalProfit()-totalCost();
    }

    /**Returns total Daily Cost */
    public float totalCost(){
        return no_Cashiers * cpd;
    }
}

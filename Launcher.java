import java.util.*;
import java.io.*;
/**
 * This class lauches the CoffeeShop and its simulation and prints out all neccessary data 
 *
 * @author Basit Balogun
 * @version 10/7/2018
 */
public class Launcher
{
    /** Constructor for objects of class Launcher*/
    public Launcher()
    {    
    }

    /** the main method */
    public static void main(String[] args) {
        Launcher  launch = new Launcher();
        launch.run();
    }

    /** The run method */
    public void run(){
        CoffeeShop  shop = new CoffeeShop("input.txt");// creates an instance of the coffee shop
        shop.simulation();
        System.out.println("Total Profit: " +  shop.totalProfit() + " Total Cost: "+ shop.totalCost()+ " Net Profit: "+ shop.netProfit()+ " Average Wait Time: "+ shop.avgWaitTime()+" Max Wait Time: "+ shop.maxWaitTime()+" Overflow Rate: "+shop.overflowRate());
    }

}

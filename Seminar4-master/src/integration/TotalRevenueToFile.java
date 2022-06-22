package integration;

import model.Observer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Observer that observes a sale and prints information after each sale to make sure the proper amount of money is correct
 * Stores/prints to a txt file. Txt file gets wiped upon restart of program- in theory the file would be
 * backuped after each workday
 */
public class TotalRevenueToFile implements Observer {
    String fileLoc = "";
    double amount;
    public TotalRevenueToFile(){
        amount = 0;
        fileLoc = "src/integration/totalRevenue.txt";

    }

    /**
     * Updates the observer with the amount of money
     * Observer simply adds the money internally- doesn't retunr anything
     * @param amount amount of money to add- include vat
     */
    @Override
    public void update(double amount) {
        this.amount += amount;
        try {
            PrintWriter logger = new PrintWriter(fileLoc);
            logger.println(totalToString());
            logger.close();
        } catch (FileNotFoundException e) {
            System.out.println("Drive most likely out of space!");
            e.printStackTrace();
        }
    }

    /**
     * Returns a string of what the observed has observed since start of program.
     * @return
     */
    @Override
    public String totalToString(){
        return "OBSERVER: "+ amount + " SEK since start.";
    }
}

package viewer;

import model.Observer;

/**
 * TotalRevenueView sends information to display about the current revenue
 */
public class TotalRevenueViewer implements Observer{
    private double totalPaid;

    /**
     *
     */
    public TotalRevenueViewer(){
        totalPaid = 0;
    }
    /**
     * Updates THIS to what amount of money it should contain
     * @param amount
     */
    @Override
    public void update(double amount) {
        totalPaid += amount;
        System.out.println(totalToString());

    }

    /**
     * Not the full class to a string- therefor omitted classic override tostring.
     * Just sends the totalPid to a string- usually printed in view
     * @return
     */
    public String totalToString(){
        return "OBSERVER: "+ totalPaid + " SEK since start.";
    }
}

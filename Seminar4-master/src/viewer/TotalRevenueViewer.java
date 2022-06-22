package viewer;

import model.Observer;

/**
 * Sends the current revenue amount to the display.
 */
public class TotalRevenueViewer implements Observer{
    private double totalPaid;

    /**
     * TotalRevenueViewer sets the 'totalPaid' value to 0, which is later updated.
     */
    public TotalRevenueViewer(){
        totalPaid = 0;
    }

    /**
     * Updates the totalPaid variable with the value of the paid amount.
     * @param amount the value to be added to the totalPaid variable.
     */
    @Override
    public void update(double amount) {
        totalPaid += amount;
        System.out.println(totalToString());
    }

    /**
     * Sends the totalPaid to a string, and is printed in the Viewer.
     * @return A message and the value of 'totalPaid'.
     */
    public String totalToString(){
        return "Observer: Total amount paid since sale started: "+ totalPaid + " kr.";
    }
}

package integration;
import model.Receipt;

/**
 * Handles the printing of a receipt that will be given to a customer.
 */
public class Printer {

    /**
     * Prints a receipt to the console. Used for debugging.
     * @param receipt the current receipt that is to be printed.
     * @return always true. Supposed to return false when there is no paper to the receipt in real life.
     */
    public boolean printReceiptToConsole(Receipt receipt){
        System.out.println(receipt);
        return true;
    }
}

package integration;
import model.Receipt;

public class Printer {

    //Prints the receipt to the console. Used for debugging.
    public boolean printReceiptToConsole(Receipt receipt){
        System.out.println(receipt);
        return true;
    }
}

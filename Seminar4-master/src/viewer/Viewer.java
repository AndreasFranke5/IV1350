package viewer;

import controller.Controller;
import integration.DatabaseNotFoundException;
import integration.ProductNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Contains hardcoded Controller calls. Would be equivalent to a real life display.
 */
public class Viewer {
    Controller controller;

    /**
     * Constructor for the Viewer. Creates a controller object.
     *
     * @param control the controller object.
     */
    public Viewer(Controller control) {
        controller = control;
    }

    /**
     * Hardcoded controller calls.
     * Adds products from the ProductList.txt with designated product IDs.
     * Prints the purchase process and information.
     * Prints that the amount paid is too low, if this is the case.
     */
    public void hardcodeControlCalls() {
        try {
            System.out.println("Creating new sale. Customer ID: 7");
            controller.beginSale(7);
            controller.addProduct(0, 6);
            controller.addProduct(4, 4);
            controller.endSale(100, "Andreas", "Checkout 5");
            System.out.println("Starts second sale. \n");
            controller.beginSale(1);
            controller.addProduct(2, 1);
            controller.addProduct(5, 5);
            controller.endSale(100, "NotAndreas", "Checkout 3");
        } catch (DatabaseNotFoundException | ProductNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Printing receipt \n\n");

        if (!controller.endSale(9999, "Andreas", "Checkout 5")) System.out.println("Account balance too low.");
    }

    private void addProduct(int id, int count) throws IOException {
        FileWriter logger = null;
        try {
            logger = new FileWriter("src/integration/errorLogs.txt", true);
            controller.addProduct(id, count);
        } catch (
                ProductNotFoundException e) {
            System.out.println(e.getMessage());
            logger.close();
        } catch (
                DatabaseNotFoundException dbException) {
            System.out.println(dbException.getMessage());
            logger.write(dbException.getAdminMessage());
            logger.close();
        }
    }
}
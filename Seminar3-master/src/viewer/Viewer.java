package viewer;

import controller.Controller;

/**
 * Contains hardcoded Controller calls. Would be equivalent to a real life display.
 */
public class Viewer {
    Controller controller;

    /**
     * Constructor for the Viewer. Creates a controller object.
     * @param control the controller object.
     */
    public Viewer(Controller control){
        controller = control;
    }

    /**
     * Hardcoded controller calls.
     * Adds products from the ProductList.txt with designated product IDs.
     * Prints the purchase process and information.
     * Prints that the amount paid is too low, if this is the case.
     */
    public void hardcodeControlCalls(){
        System.out.println("Creating new sale. Customer ID: 7");
        controller.beginSale(7);
        System.out.println("Add: Six clementines");
        controller.addProduct(0, 6) ;
        System.out.println("Add: Four apples");
        controller.addProduct(4);
        System.out.println("Alt. flow 3-4b - Add: Another clementine");
        controller.addProduct(0);
        System.out.println("Add: One pineapple");
        controller.addProduct(2, 1);
        System.out.println("Add: Five bananas");
        controller.addProduct(5);
        System.out.println("Add: Additional items for fruit salad");
        controller.addProduct(4);
        controller.addProduct(5);
        System.out.println("Ending sale");
        System.out.println("Printing receipt \n\n");
        if(!controller.endSale(700, "Andreas","Checkout 5")) System.out.println("Account balance too low.");
    }
}

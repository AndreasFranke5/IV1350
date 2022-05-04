package viewer;

import controller.Controller;

public class Viewer {
    Controller controller;
    public Viewer(Controller control){
        controller = control;
    }

    public void hardcodeControlCalls(){
        System.out.println("Creating new sale. Customer ID: 7");
        controller.beginSale(7);
        System.out.println("Add: Six clementines");
        controller.addProduct(0, 6) ; //Add: Six clementines
        System.out.println("Add: Four apples");
        controller.addProduct(4); //Add: Four apples
        System.out.println("Alt. flow 3-4b - Add: Another clementine");
        controller.addProduct(0); //Alt. flow 3-4B - Add: Another clementine
        System.out.println("Add: One pineapple");
        controller.addProduct(2, 1); //Add: One pineapple
        System.out.println("Add: Five bananas");
        controller.addProduct(5); //Add: Five bananas
        System.out.println("Add: Additional items for fruit salad");
        controller.addProduct(4); //Add: Pack of grapes
        controller.addProduct(5); //Add: Strawberries
        System.out.println("Ending sale");
        System.out.println("Printing receipt \n\n");
        if(!controller.endSale(700, "Andreas","Checkout 5")) System.out.println("Account balance too low.");
    }
}

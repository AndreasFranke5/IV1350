package controller;

import dataTransferObjects.SaleDTO;
import dataTransferObjects.StoreDTO;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.Printer;
import integration.TotalRevenueToFile;
import integration.*;
import model.Receipt;
import model.Sale;
import model.Scanner;
import viewer.TotalRevenueViewer;

/**
 * The Controller class that controls the sale after it has been called. Responsible for database and external system integration.
 */
public class Controller {
    private ExternalAccountingSystem accounting;
    private ExternalInventorySystem external;
    private Printer printer;
    private Sale saleCurrent;
    private Scanner scanner;
    private StoreDTO storeDTO;
    private TotalRevenueToFile trfo;
    private TotalRevenueViewer trv;

    /**
     * Constructor for Controller that has hardcoded calls, containing the name of the store, the address and the phone number.
     */
    public Controller(TotalRevenueToFile file, TotalRevenueViewer viewer){
        storeDTO = new StoreDTO("COOP", "Järfällavägen 5", "0701234567");
        external = ExternalInventorySystem.getInstance();
        accounting = new ExternalAccountingSystem();
        printer = new Printer();
        scanner = new Scanner(external);
        trfo = file;
        trv = viewer;
    }


    /**
     * Begins a sale. Runs the Sale constructor.
     * @param customerID is the ID of the customer. Used for discounts, although not applicable at the moment.
     */
    public boolean beginSale(int customerID){
        saleCurrent = new Sale();
        saleCurrent.addObserver(trfo);
//        saleCurrent.addObserver(trv);
        return true;
    }

    /**
     * Adds a product to currentSale.
     * @param productID is the ID of the product.
     */
    public boolean addProduct(int productID) throws ProductNotFoundException, DatabaseNotFoundException {
        return scanner.addProductWithID(productID, saleCurrent, 1);
    }

    /**
     * Ends the ongoing sale. Register the sale in Accounting and prints a receipt with Printer.
     * @param paidAmount is the amount of money that was paid. Returns false if the amount is less than the total cost.
     * @param cashier is the name of the cashier that did the sale.
     * @param position is the position of the sale, as in which Checkout was used.
     */
    public boolean endSale(double paidAmount, String cashier, String position){
        SaleDTO DataTransferObject = saleCurrent.endSale(cashier, position);
        if(paidAmount > DataTransferObject.fetchTotal() + DataTransferObject.fetchVAT()) {
            Receipt receipt = new Receipt(DataTransferObject, storeDTO, paidAmount);
            printer.printReceiptToConsole(receipt);
            accounting.registerSale(DataTransferObject);
            return true;
        }

        return false;
    }


    /**
     * Ends the sale and doesn't store information.
     */
    public void endSale(){
        saleCurrent.endSale();
        saleCurrent = null;
    }

    /**
     * Adds a product.
     * @param productID is the ID of the product.
     * @param productCount is the amount of products.
     */
    public boolean addProduct(int productID, int productCount) throws ProductNotFoundException, DatabaseNotFoundException {
        scanner.addProductWithID(productID, saleCurrent, productCount);
        return true;
    }

    /**
     * Gets a string of the current sale.
     * @return will return a string representing the controller.
     */
    public String getString(){
        if(saleCurrent.fetchCurrentSaleProgress()){
            String string;
            string = saleCurrent.toString();
            return string;
        }

        return null;
    }
    /**
     * Undos the last addItem using memento of the previous saleitems
     */
    public void undo() {
        scanner.undo(saleCurrent);
    }

    /**
     * Undos the previous i addItems using a memento of the previous list of items
     *
     * @param i amount of undos
     */
    public void undo(int i) {
        scanner.undo(saleCurrent, i);
    }
}

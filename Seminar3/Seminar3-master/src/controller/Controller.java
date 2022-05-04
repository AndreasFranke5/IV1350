package controller;

import dataTransferObjects.SaleDTO;
import dataTransferObjects.StoreDTO;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.Printer;
import model.Receipt;
import model.Sale;
import model.Scanner;

public class Controller {
    private ExternalAccountingSystem accounting;
    private ExternalInventorySystem external;
    private Printer printer;
    private Sale saleCurrent;
    private Scanner scanner;
    private StoreDTO storeDTO;

    public Controller(){
        storeDTO = new StoreDTO("COOP", "Järfällavägen 5", "0701234567");
        external = new ExternalInventorySystem();
        accounting = new ExternalAccountingSystem();
        printer = new Printer();
        scanner = new Scanner(external);
    }

    public boolean beginSale(int customerID){
        saleCurrent = new Sale();
        return true;
    }

    public boolean addProduct(int productID){
        return scanner.addProductWithID(productID, saleCurrent, 1);
    }

    public boolean endSale(double paidAmount, String string, String position){
        SaleDTO DataTransferObject = saleCurrent.endSale(string, position);
        if(paidAmount > DataTransferObject.fetchTotal() + DataTransferObject.fetchVAT()) {
            Receipt receipt = new Receipt(DataTransferObject, storeDTO);
            System.out.print("Returned amount: " + (paidAmount-DataTransferObject.fetchTotal()- DataTransferObject.fetchVAT()) + " kr\n");
            printer.printReceiptToConsole(receipt);
            accounting.registerSale(DataTransferObject);
            return true;
        }

        return false;
    }

    public void endSale(){
        saleCurrent.endSale();
    }
    public boolean addProduct(int productID, int productCount){
        scanner.addProductWithID(productID, saleCurrent, productCount);
        return true;
    }

    public String getString(){
        if(saleCurrent.fetchCurrentSaleProgress()){
            String string;
            string = saleCurrent.toString();
            return string;
        }

        return null;
    }

}

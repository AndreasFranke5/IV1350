package model;

import integration.DatabaseNotFoundException;
import integration.ExternalInventorySystem;
import integration.ProductNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Works like a handler for product adding. Uses the ExternalInventorySystem instead of the Product class.
 * Provides good encapsulation and handles all the products edits from the ProductList.txt.
 */
public class Scanner {
    private ExternalInventorySystem externalInventorySystem;
    private List<Sale.SaleMemento> undoList;
    /**
     * Constructor for the Scanner.
     * @param externalInventorySystem the External Inventory System.
     */
    public Scanner (ExternalInventorySystem externalInventorySystem) {
        this.externalInventorySystem = externalInventorySystem;
        this.undoList = new ArrayList<>();
    }

    /**
     * Adds an item to a sale using the barcode. In this case, it's however identical to the productID.
     * @param barcode The productID/Barcode. Integer.
     * @param saleToAddTo Which sale to add a product to.
     * @param count Product quantity that should be added.
     * @throws DatabaseNotFoundException If productID is 6 or database not connected.
     * @throws ProductNotFoundException If productID is not a correct item.
     * @return the product.
     */
    public boolean addItemFromBarcode(int barcode, Sale saleToAddTo, int count) throws ProductNotFoundException, DatabaseNotFoundException {
        undoList.add(saleToAddTo.getMemento());
        Product ret;
        Product temp;
        temp = externalInventorySystem.fetchProduct(barcode);
        ret = new Product(count, temp.productCost, temp.vatRate, temp.productDescription, temp.productID);
        return saleToAddTo.addProduct(ret, externalInventorySystem);

    }
    /**
     * Due to the Memento implementation, Scanner is the caretaker and therefore needs an undo method.
     * @param saleToUse should only operate on the current sale.
     */
    public void undo(Sale saleToUse){
        saleToUse.restoreFromMemento(undoList.get(undoList.size()-1));
        undoList.remove(undoList.size()-1);
    }

    /**
     * Does an 'undo' operation to the productList, based on the value of i.
     * @param saleToUse should only operate on the current sale.
     * @param i is the number of operations to 'undo', and needs to be less than steps that have been done.
     */
    public void undo(Sale saleToUse, int i){
        int temp = undoList.size() - i;
        saleToUse.restoreFromMemento(undoList.get(undoList.size()-i));
        for(int j = undoList.size()-1; j >= temp   ; j--){
            undoList.remove(undoList.size()-1);
        }
    }

}



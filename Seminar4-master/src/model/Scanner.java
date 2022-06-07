package model;

import integration.ExternalInventorySystem;

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
    }

    /**
     * Adds a product based on the productID.
     * @param productID the ID of the product.
     * @param sale which sale the product should be added to.
     * @param productCount the amount of products to be added.
     * @return the product.
     */
    public boolean addProductWithID (int productID, Sale sale, int productCount) {
        Product temp = externalInventorySystem.fetchProduct(productID);
        Product returnProduct = new Product(productCount, temp.productCost, temp.vatRate, temp.productDescription, temp.productID);
        return sale.addProduct(returnProduct, externalInventorySystem);
    }
    /**
     * As our ItemScanner acts as the caretaker for our memento pattern, this also has to include an undo function.
     * @param saleToUse usually, this is simply currentSale.
     */
    public void undo(Sale saleToUse){
        saleToUse.restoreFromMemento(undoList.get(undoList.size()-1));
        undoList.remove(undoList.size()-1);
    }

    /**
     * Undo's an i amount of actions to the itemList
     * As of current implementation, this also "tries" to undo failed addItems
     * @param saleToUse usually currentSale- other sale's shouldn't really be undone
     * @param i         amount of steps to "undo". Has to be less than steps done.
     */
    public void undo(Sale saleToUse, int i){
        int temp = undoList.size() - i;
        saleToUse.restoreFromMemento(undoList.get(undoList.size()-i));
        for(int j = undoList.size()-1; j >= temp   ; j--){
            undoList.remove(undoList.size()-1);
        }
    }

}



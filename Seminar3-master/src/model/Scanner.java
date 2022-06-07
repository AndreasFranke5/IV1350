package model;

import integration.ExternalInventorySystem;

/**
 * Works like a handler for product adding. Uses the ExternalInventorySystem instead of the Product class.
 * Provides good encapsulation and handles all the products edits from the ProductList.txt.
 */
public class Scanner {
    private ExternalInventorySystem externalInventorySystem;

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
}



package model;

import integration.ExternalInventorySystem;

public class Scanner {
    private ExternalInventorySystem externalInventorySystem;

    public Scanner (ExternalInventorySystem externalInventorySystem) {
        this.externalInventorySystem = externalInventorySystem;
    }

    //Method that adds products based on a designated integer value.
    public boolean addProductWithID (int productID, Sale sale, int productCount) {
        Product temp = externalInventorySystem.fetchProduct(productID);
        Product returnProduct = new Product(productCount, temp.productCost, temp.vatRate, temp.productDescription, temp.productID);
        return sale.addProduct(returnProduct, externalInventorySystem);
    }
}



package model;

/**
 * Handles products. Includes product description, ID, quantity, cost and VAT-rate.
 */
public class Product {

    String productDescription;
    int productID;
    int productQuantity;
    double productCost;
    double vatRate;

    /**
     * Creates a readable string from a product.
     * @return string with all product information.
     */
    public String toString(){
        String string = "";
        string += this.productQuantity;
        string += "* " + this.productDescription;
        string += " -  " + this.productCost + "SEK";
        return string;
    }

    /**
     * Product constructor.
     * @param quantity product quantity.
     * @param cost product cost.
     * @param vat product VAT-rate.
     * @param description product description.
     * @param id product ID.
     */
    public Product(int quantity, double cost, double vat, String description, int id){
        productCost = cost;
        this.productQuantity = quantity;
        this.productDescription = description;
        this.vatRate = vat;
        this.productID = id;
    }

    /**
     * Fetches the product quantity.
     * @return product quantity.
     */
    public int fetchProductQuantity(){
        return productQuantity;
    }

    /**
     * Creates a deep copy of a product before adding it to the cart.
     * @param product product that is to be copied.
     */
    public Product(Product product){
        this.productDescription = product.productDescription;
        this.productID = product.productID;
        this.productCost = product.productCost;
        this.productQuantity = 1;
        this.vatRate = product.vatRate;
    }
}

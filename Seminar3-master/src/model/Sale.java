package model;

import dataTransferObjects.SaleDTO;
import java.util.*;
import integration.ExternalInventorySystem;

/**
 * Contains sale and product information. Modifies a sale as it takes place.
 */
public class Sale {
    private boolean ongoingSale;
    private double currentTotal;
    private double totalCost;
    private int saleID;
    private List<Product> listofProducts = new ArrayList<Product>();

    /**
     * Constructs a new sale. The saleID is randomly generated between 0-99999.
     */
    public Sale() {
        Random random = new Random();
        listofProducts.clear();
        ongoingSale = true;
        currentTotal = 0;
        totalCost = 0;
        saleID = random.nextInt(99999);
    }

    /**
     * Adds product to a sale.
     * @param product the product that should be added.
     * @param externalInventorySystem the externalInventorySystem of the product.
     * @return true if product exists in the inventory, otherwise false.
     */
    boolean addProduct (Product product, ExternalInventorySystem externalInventorySystem){
        boolean correct = false;
        if(externalInventorySystem.checkIfProductIsInStock(product.productID)){
            for (Product index:
                    listofProducts) {
                if(index.productID == product.productID){
                    index.productQuantity += product.productQuantity;
                    correct = true;
                }
            }
            if(!correct){
                listofProducts.add(product);
            }
            totalCost +=product.productCost *product.productQuantity;
            return true;
        }
        return false;
    }

    /**
     * Adds several products of the same type to a sale.
     * @param productID the ID of the product.
     * @param productQuantity the quantity of the product.
     * @param externalInventorySystem the externalInventorySystem of the product.
     * @return true if all products are in stock, otherwise false.
     */
    public boolean addProducts (int productID, int productQuantity, ExternalInventorySystem externalInventorySystem){
        boolean correct = false;
        if (externalInventorySystem.checkIfProductIsInStock(productID, productQuantity)) {
            for(int i = 0; i < productQuantity; i++){
                correct = this.addProducts(productID, 1, externalInventorySystem);
            }
        }
        return correct;
    }

    /**
     * Ends a sale by setting an ongoing sale to false.
     */
    public void endSale() {
        ongoingSale = false;
    }

    /**
     * Creates and stores the DTO, based on a sale.
     * @param cashier name of the cashier.
     * @param POS the point of sale (which checkout was used).
     * @return SaleDTO with ended sale.
     */
    public SaleDTO endSale(String cashier, String POS){
        double totalVAT = 0;
        for (Product index : listofProducts) {
            totalVAT += index.vatRate * index.productCost *index.productQuantity;
        }

        SaleDTO thisSale = new SaleDTO(totalCost,totalVAT , listofProducts, POS, saleID);
        this.ongoingSale = false;
        thisSale.designateCashier(cashier);
        return thisSale;
    }

    /**
     * Fetches the current sale progress.
     * @return the ongoing sale progress.
     */
    public boolean fetchCurrentSaleProgress(){
        return ongoingSale;
    }

    /**
     * Changes a sale into a readable string.
     * @return the products in a sale.
     */
    public String toString(){
        StringBuilder str = new StringBuilder() ;
        for (Product i : listofProducts) {
            str.append(i + "\n");
        }

        return str.toString();
    }
}

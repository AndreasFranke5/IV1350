package model;

import dataTransferObjects.SaleDTO;
import java.util.*;
import integration.ExternalInventorySystem;

public class Sale {
    private boolean ongoingSale;
    private double currentTotal;
    private double totalCost;
    private int saleID;
    private List<Product> listofProducts = new ArrayList<Product>();

    public Sale() {
        Random random = new Random();
        listofProducts.clear();
        ongoingSale = true;
        currentTotal = 0;
        totalCost = 0;
        saleID = random.nextInt(99999); // Randomizes the saleID.
    }

    //Method that is used to add products to a sale.
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

    public boolean addProducts (int productID, int productQuantity, ExternalInventorySystem externalInventorySystem){
        boolean correct = false;
        if (externalInventorySystem.checkIfProductIsInStock(productID, productQuantity)) {
            for(int i = 0; i < productQuantity; i++){
                correct = this.addProducts(productID, 1, externalInventorySystem);
            }
        }
        return correct;
    }

    //Method that ends the sale by changing the boolean value of the ongoing sale to false.
    public void endSale() {
        ongoingSale = false;
    }

    //Method that creates and stores the DTO, based on a sale.
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

    public boolean fetchCurrentSaleProgress(){
        return ongoingSale;
    }

    //Method that changes a sale into a readable string.
    public String toString(){
        StringBuilder str = new StringBuilder() ;
        for (Product i : listofProducts) {
            str.append(i + "\n");
        }

        return str.toString();
    }
}

package model;

import java.util.*;
import dataTransferObjects.SaleDTO;
import dataTransferObjects.StoreDTO;

/**
 * Simulates a physical receipt that the cashier gives to the customer in the end of a purchase.
 */
public class Receipt {
    private final double totalCost;
    private final double totalVatPaymentAmount;
    private final String time;
    private final String date;
    private final List<Product> productList;
    private final String pointOfSale;
    private final String storeInfo;
    private final String cashier;
    private final int saleID;
    private double paidAmount;

    /**
     * Creates a receipt based on information from the DTO
     * @param dto SaleDTO that creates the Receipt.
     * @param store Store information such as name, address and phone number.
     * @param paidAmount amount of money paid, in the form of double.
     */
    public Receipt(SaleDTO dto, StoreDTO store, double paidAmount){
        this.cashier = dto.fetchCashier();
        this.totalCost = dto.fetchTotal();
        this.productList = dto.fetchProducts();
        this.storeInfo = "Store name: " + store.fetchStoreName() + "\n" + "Store address: " +
                store.fetchStoreAddress() + "\n" + "Store phone number: " + store.fetchStorePhoneNumber() + "\n";
        double vat = 0;
        this.paidAmount = paidAmount;
        for (Product item : productList) {
            vat += item.vatRate *item.productCost *item.productQuantity;
        }
        this.totalVatPaymentAmount = vat;
        this.date = java.time.LocalDate.now().toString();
        this.time = java.time.LocalTime.now().toString().substring(0,8);
        this.pointOfSale = dto.fetchPointOfSale();
        this.saleID = dto.fetchSaleID();
    }

    /**
     * Fetches the saleID.
     * @return the saleID.
     */
    public int fetchSaleID(){return this.saleID;}

    /**
     * Changes a receipt into a readable string by appending multiple methods.
     * @return string meant for printout.
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\n"+storeInfo);
        str.append("\nName of cashier: " + this.cashier + "\n");
        str.append("Time: " + time + "\n" + "Date: " + date+ "\n");
        str.append("Checkout used: " + pointOfSale + "\n" + "\n" + "Products:" + "\n" + "\n" + "QTY - Description - Cost" + " \n" + " \n");
        for (Product item : productList) {str.append(item.productQuantity + " - " + item.productDescription + " - " + item.productCost + " kr" + " \n");}
        str.append(" \n" + "Returned amount: " + (this.paidAmount - this.totalCost - this.totalVatPaymentAmount) + " kr\n");
        str.append(" \n" + "Subtotal: " + this.totalCost + " kr" + " \n");
        str.append("Sales tax: " + this.totalVatPaymentAmount + " kr" + "\n");
        str.append("Total: " + (this.totalCost + this.totalVatPaymentAmount) + " kr" + " \n");
        str.append("Receipt number: "+ saleID);
        return str.toString();
    }
}


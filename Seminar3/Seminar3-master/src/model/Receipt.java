package model;

import java.util.*;
import dataTransferObjects.SaleDTO;
import dataTransferObjects.StoreDTO;

public class Receipt {
    private final double totalCost; //Total cost of sale.
    private final double totalVatPaymentAmount; //Total tax cost.
    private final String time; //Time of sale.
    private final String date; //Date of sale.
    private final List<Product> productList; //Lists the products in the sale.
    private final String pointOfSale; //Shows which register/checkout was used for the sale.
    private final String storeInfo; //Includes all the information about the store, including name, address and phone number.
    private final String cashier; //Name of cashier.
    private final int saleID; //Shows the receipt number / ID of the sale.

    //Method to create a receipt based on information from the DTO.
    public Receipt(SaleDTO dto, StoreDTO store){
        this.cashier = dto.fetchCashier();
        this.totalCost = dto.fetchTotal();
        this.productList = dto.fetchProducts();
        this.storeInfo = "Store name: " + store.fetchStoreName() + "\n" + "Store address: " +
                store.fetchStoreAddress() + "\n" + "Store phone number: " + store.fetchStorePhoneNumber() + "\n";

        //Function to calculate the total cost of the VAT rate.
        double vat = 0;
        for (Product item : productList) {
            vat += item.vatRate *item.productCost *item.productQuantity;
        }
        this.totalVatPaymentAmount = vat;

        //Functions for making the date and time work.
        this.date = java.time.LocalDate.now().toString();
        this.time = java.time.LocalTime.now().toString().substring(0,8);

        //Fetches the register/checkout number.
        this.pointOfSale = dto.fetchPointOfSale();
        
        //Fetches the saleID/receipt number.
        this.saleID = dto.fetchSaleID();
    }

    public int getSaleID(){return this.saleID;}

    //Method that changes a receipt into a readable string.
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\n"+storeInfo);
        str.append("\nName of cashier: " + this.cashier + "\n");
        str.append("Time: " + time + "\n" + "Date: " + date+ "\n");
        str.append("Checkout used: " + pointOfSale + "\n" + "\n" + "Products:" + "\n" + "\n" + "QTY - Description - Cost" + " \n" + " \n");
        for (Product item : productList) {str.append(item.productQuantity + " - " + item.productDescription + " - " + item.productCost + " kr" + " \n");}

        str.append(" \n" + "Subtotal: " + this.totalCost + " kr" + " \n");
        str.append("Sales tax: " + this.totalVatPaymentAmount + " kr" + "\n");
        str.append("Total: " + (this.totalCost + this.totalVatPaymentAmount) + " kr" + " \n");
        str.append("Receipt number: "+ saleID);
        return str.toString();
    }
}


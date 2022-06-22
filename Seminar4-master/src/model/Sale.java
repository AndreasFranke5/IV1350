package model;

import dataTransferObjects.SaleDTO;
import integration.DatabaseNotFoundException;
import integration.ExternalInventorySystem;
import integration.ProductNotFoundException;
import integration.TotalRevenueToFile;
import viewer.TotalRevenueViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Contains sale and product information. Modifies a sale as it takes place.
 */
public class Sale {
    private boolean ongoingSale;
    private double currentTotal;
    private List<Product> productList = new ArrayList<Product>();
    private double totalCost;
    private int saleID;
    private List<Observer> observerList = new ArrayList<model.Observer>();

    /**
     * Constructs a new sale. The saleID is randomly generated between 0-99999.
     */
    public Sale() {
        Random random = new Random();
        productList.clear();
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
                    productList) {
                if(index.productID == product.productID){
                    index.productQuantity += product.productQuantity;
                    correct = true;
                }
            }
            if(!correct){
                productList.add(product);
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
    boolean addProducts(int productID, int productQuantity, ExternalInventorySystem externalInventorySystem) {
        Product temporaryVariable = null;
        try{
            temporaryVariable = externalInventorySystem.fetchProduct(productID);}
        catch (DatabaseNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean addProductWasSuccess = false;
        if (externalInventorySystem.inStock(productID, productQuantity) && temporaryVariable != null) {
            for (int i = 0; i < productQuantity; i++) {
                addProductWasSuccess = this.addProduct(temporaryVariable, externalInventorySystem);
            }
        }
        return addProductWasSuccess;
    }

    /**
     * Ends a sale by setting an ongoing sale to false.
     */
    public void terminateSale() {
        ongoingSale = false;
    }

    /**
     * Creates and stores the DTO, based on a sale.
     * @param cashier name of the cashier.
     * @param POS the point of sale (which checkout was used).
     * @return SaleDTO with ended sale.
     */
    public SaleDTO endCurrentSale(String cashier, String POS){
        double totalVAT = 0;
        for (Product index : productList) {
            totalVAT += index.vatRate * index.productCost *index.productQuantity;
        }

        SaleDTO thisSale = new SaleDTO(totalCost,totalVAT , productList, POS, saleID);
        this.ongoingSale = false;
        thisSale.designateCashier(cashier);
        updateObservers(this.totalCost + totalVAT);
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
        for (Product i : productList) {
            str.append(i + "\n");
        }

        return str.toString();
    }


    /**
     * Updates all observers in the observerList. Usually called after every ended sale.
     * @param amount amount of money to update observers of
     */
    public void updateObservers(double amount){
        for (Observer o : observerList) {
            o.update(amount);
        }
    }
    /**
     *  Adds an observer to the observerList. According to the obsrever pattern.
     *  Currently, we're just observing one thing in two ways, the revenue
     * @param observer the observer to add.
     */
    public void addObserver(Observer observer){
        observerList.add(observer);
    }

    /**
     * Returns a sales memenento- the storage class used for the MementoPattern.
     * Used after every modification of the itemList.
     * @return a memento containing the listOfItems and the price.
     */
    public SaleMemento getMemento(){
        return new SaleMemento(this.productList, this.totalCost);
    }

    /**
     * Restores a currently active sale to a previous state- using the salemMementos
     * listOfItems and price- therefore allowing us to undo edits
     * @param memento the memento to restore to- doesn't actually matter if its from this sale or another one
     */
    public void restoreFromMemento(SaleMemento memento){
        this.productList = memento.getOldSaleItems();
        this.totalCost = memento.getPrice();
    }
    /**
     * Our implementation of the Memento pattern
     * We store the Sale's listOfItems and totalPrice into a memento for further use.
     * <a href="https://en.wikipedia.org/wiki/Memento_pattern#Java_example">https://en.wikipedia.org/wiki/Memento_pattern#Java_example</a>
     *
     * We decided to store our state as the full list.
     */
    public static class SaleMemento {
        private final List<Product> oldSaleItems;
        private final double price;

        /**
         * Construtor for the saleMemento
         * We could've just used the Sale class instance itself- but that's a lot more data
         * then we need.
         * @param items a list of items from the previous sale.
         * @param price the price of the list
         */
        public SaleMemento(List<Product> items, double price){
            oldSaleItems = new ArrayList<>();
            for (Product i :
                    items) {
                oldSaleItems.add(i.clone());
            }
            this.price = price;
        }

        /**
         * Get's a list of items from the saleMemento.
         * @return full list of items in a java.lang.List
         */
        private List<Product> getOldSaleItems(){
            return oldSaleItems;
        }

        /**
         * gets the price of the items in a saleMemento
         * @return price in a double
         */
        private double getPrice(){
            return price;
        }
    }
}

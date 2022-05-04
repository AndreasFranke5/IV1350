package dataTransferObjects;
import java.util.ArrayList;
import java.util.List; 
import model.Product;

public class SaleDTO{

    private double totalCost;
    private int saleID;

    private double totalVAT;
    private String cashier;
    private List<Product> productList = new ArrayList<Product>();
    private String pointOfSale;

    //Constructor method for the SaleDTO.
    public SaleDTO(double cost, double vatRate, List<Product>list, String position, int i){
        this.totalCost = cost;
        this.pointOfSale = position;
        this.productList = list;
        this.saleID = i;
        this.totalVAT = vatRate;
    }

    //Method that creates a copy of a sale, which allows editing while saving the values of an original sale.
    public SaleDTO copyOfSale(){
        SaleDTO tempProductValues = new SaleDTO(this.totalCost,this.totalVAT, this.productList, this.pointOfSale, this.saleID);
        tempProductValues.designateCashier(this.cashier);
        return tempProductValues;
    }

    //Method that compares some variables of two different SaleDTO. This is to verify that they are identical.
    public boolean compareSaleDTO(SaleDTO saleObject) {
        if(saleObject.fetchSaleID() == this.saleID && saleObject.cashier.equals(this.cashier)
        && saleObject.productList.equals(this.productList)) return true;
        return false;
    }

    //Methods for designating.
    public void designateCashier(String cashier){ //sets Cashier
        this.cashier = cashier;
    }

    //Methods for fetching.
    public List<Product> fetchProducts(){
        return productList;
    } //Fetches the product list.
    public int fetchSaleID(){ return saleID ; } //Fetches the sale ID.
    public String fetchCashier(){ // gets Cashier as String
        return cashier;
    } //Fetches the cashier.
    public String fetchPointOfSale(){
        return pointOfSale;
    } //Fetches the point of sale.
    public double fetchTotal(){return totalCost;} //Fetches total cost, excluding the VAT rate.
    public double fetchVAT(){ // gets VAT
        return totalVAT;
    } //Fetches the VAT rate.





}
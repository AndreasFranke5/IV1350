package dataTransferObjects;
import java.util.ArrayList;
import java.util.List; 
import model.Product;

/**
 * Used for data transfer and sale storage.
 */
public class SaleDTO{

    private double totalCost;
    private int saleID;

    private double totalVAT;
    private String cashier;
    private List<Product> productList = new ArrayList<Product>();
    private String pointOfSale;

    /**
     * Constructor for the SaleDTO.
     * @param cost is the total cost of the sale, excluding VAT.
     * @param vatRate is the VAT rate of the sale.
     * @param list is the product list, which contains further data such as product cost, quantity and description.
     * @param position is the position of the sale, or point of sale.
     * @param i is the sale ID that is randomized between 0 and 10000.
     */
    public SaleDTO(double cost, double vatRate, List<Product>list, String position, int i){
        this.totalCost = cost;
        this.pointOfSale = position;
        this.productList = list;
        this.saleID = i;
        this.totalVAT = vatRate;
    }

    /**
     * Creates a copy of a sale, which allows editing while saving the values of an original sale.
     * @return a copy.
     */
    public SaleDTO copyOfSale(){
        SaleDTO tempProductValues = new SaleDTO(this.totalCost,this.totalVAT, this.productList, this.pointOfSale, this.saleID);
        tempProductValues.designateCashier(this.cashier);
        return tempProductValues;
    }

    /**
     * Compares some variables of two different SaleDTO. This is to verify that they are identical.
     * @param saleObject the second saleDTO that is being compared.
     * @return true if they are identical, otherwise false.
     */
    public boolean compareSaleDTO(SaleDTO saleObject) {
        return saleObject.fetchSaleID() == this.saleID && saleObject.cashier.equals(this.cashier)
                && saleObject.productList.equals(this.productList);
    }

    /**
     * Designates the cashier to a certain string.
     * @param cashier a new cashier.
     */
    public void designateCashier(String cashier){this.cashier = cashier;}


    /**
     * Fetches the product list.
     * @return List<Product> of products</Product>.
     */
    public List<Product> fetchProducts(){return productList;}

    /**
     * Fetches the sale ID.
     * @return the sale ID.
     */
    public int fetchSaleID(){return saleID;}

    /**
     * Fetches the cashier as a String.
     * @return the cashier as a String.
     */
    public String fetchCashier(){return cashier;}

    /**
     * Fetches the point of sale.
     * @return the point of sale.
     */
    public String fetchPointOfSale(){return pointOfSale;}

    /**
     * Fetches total cost, excluding the VAT rate.
     * @return total cost, excluding the VAT rate.
     */
    public double fetchTotal(){return totalCost;}

    /**
     * Fetches the VAT rate.
     * @return the VAT rate.
     */
    public double fetchVAT(){return totalVAT;}
}
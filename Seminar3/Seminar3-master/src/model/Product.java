package model;
public class Product {

    String productDescription; //Variable that stores the product description.
    int productID; //Variable that stores the product ID.
    int productQuantity; //Variable that stores the product quantity.
    double productCost; //Variable that stores the product cost.
    double vatRate; //Variable that uses a decimal value for deciding the VAT rate (0.25 for 25%).

    //Method that converts a product into a String, so that it's able to be read.
    public String toString(){
        String string = "";
        string += this.productQuantity;
        string += "* " + this.productDescription;
        string += " -  " + this.productCost + "SEK";
        return string;
    }

    //Method that collects the product variables.
    public Product(int quantity, double cost, double vat, String description, int id){
        productCost = cost;
        this.productQuantity = quantity;
        this.productDescription = description;
        this.vatRate = vat;
        this.productID = id;
    }

    //Method that fetches the product quantity and returns it.
    public int fetchProductQuantity(){
        return productQuantity;
    }

    //Method that creates a deep copy of a product before adding it to the cart.
    public Product(Product product){
        this.productDescription = product.productDescription;
        this.productID = product.productID;
        this.productCost = product.productCost;
        this.productQuantity = 1;
        this.vatRate = product.vatRate;
    }
}

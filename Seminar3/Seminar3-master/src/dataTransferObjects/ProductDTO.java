package dataTransferObjects;

public class ProductDTO {
    private double productCost; //Variable that stores the product cost.
    private double productDescription; //Variable that stores the product description.
    private int productID; //Variable that stores the product ID.
    private double vatRate; //Variable that uses a decimal value for deciding the VAT rate (0.25 for 25%).

    //Rendered obsolete. Could be made useful by being changed into only using the product quantity and product ID.
    public ProductDTO(int productID){
    }
}

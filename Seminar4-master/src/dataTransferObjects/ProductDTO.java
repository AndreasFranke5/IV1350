package dataTransferObjects;

/**
 * ProductDTO is currently not in use, because the product is sent instead. Could be made useful by being changed into only using the product quantity and product ID.
 */
public class ProductDTO {
    private double productCost;
    private double productDescription;
    private int productID;
    private double vatRate;

    public ProductDTO(int productID){
    }
}

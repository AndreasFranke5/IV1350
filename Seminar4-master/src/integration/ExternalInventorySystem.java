package integration;
import model.Product;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Handles the product inventory and stock of products in the store.
 * Controls if products are in stock, adds products and quantity of products.
 */
public class ExternalInventorySystem {
    private static ExternalInventorySystem instance = null;
    private List<Product> currentInventory = new ArrayList<>();

    /**
     * Constructor that creates an ExternalInventorySystem based on a .txt-document containing all the products.
     * Products are edited by editing directly in the .txt-document.
     */
    public ExternalInventorySystem(){
        try {
            Scanner scnr = new Scanner(new File("src/integration/ProductList.txt"));
            String[] temp;
            Product product;
            int row = 0;

            while(scnr.hasNextLine()){
                temp = scnr.nextLine().split("#");
                product = new Product(Integer.parseInt(temp[3]), Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), temp[2], row);
                currentInventory.add(product);
                row++;
            }
        }

        catch(FileNotFoundException e){
            System.out.println("Error, file not found. ");
            e.printStackTrace();
        }
    }

    /**
     * Fetches a product from the inventory.
     * @param id the product ID. Corresponds to the index in the currentInventory list.
     * @return The product on currentInventory[id].
     */
    public Product fetchProduct(int id){
        return currentInventory.get(id);
    }

    /**
     * Changes the entire inventory into a string. Practical when updating and wanting to print the entire inventory.
     * @return String of products, quantity and price.
     */
    public String toString(){
        String string = "";
        for (Product product : currentInventory) {
            string += "\n" + product.toString();
        }
        return string;
    }

    /**
     * Controls if a product is in stock using the product ID.
     * @param productID the ID of the product
     * @return true if >=1 product is in stock, otherwise false.
     */
    public boolean checkIfProductIsInStock(int productID){
        if(currentInventory.size() >= productID) {
            Product id = currentInventory.get(productID);
            if (id.fetchProductQuantity() > 0)
                return true;
        }
        return false;
    }

    /**
     * Controls if a product is in stock using the product ID. Same as above but for multiple items with the same ID.
     * @param productID the ID of the product
     * @param productQuantity the quantity of products.
     * @return true if >=1 product is in stock, otherwise false.
     */
    public boolean checkIfProductIsInStock(int productID, int productQuantity){
        if(currentInventory.size() >= productID){
            Product id = currentInventory.get(productID);
            if(id.fetchProductQuantity() >= productQuantity)
                return true;
        }
        return false;
    }

    /**
     * Gets length of inventory. Basically amount of indexes used.
     * @return integer with length
     */
    public int getLength(){
        return currentInventory.size();
    }

    /**
     * Looks if an item has more than quantity in stock
     * @param id item to look for
     * @param quantity amount of item to look for
     * @return true if enough items exist
     */
    public boolean inStock(int id, int quantity) {
        //checks if multiple items of the same ID are in stock, for example 4 bananas
        if (currentInventory.size() >= id) {
            Product it = currentInventory.get(id);
            if (it.fetchProductQuantity() >= quantity)
                return true;

        }
        return false;
    }
    /**
     * Adds a new product to the ExternalInventorySystem. Product needs to be new for it to be used properly.
     * ProductID has to be length of currentInventory, or the indes and productID will not correspond.
     * @param product which product to add.
     */
    public void addProduct(Product product){
        currentInventory.add(product);
    }
    /**
     * This is our getInstance function for the Singleton pattern. It's a constructor that only runs the first time
     * it is called- and thereafter only returning the already existing one. This gives us still a public access
     *
     * @return Singleton ExternalInventorySystem
     */
    public static ExternalInventorySystem getInstance(){
        if(instance == null){
            instance = new ExternalInventorySystem();
        }
        else
            System.out.println("ExternalInventory instance already exists- giving you the existing one.");
        return instance;
    }

}

package integration;
import model.Product;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ExternalInventorySystem {
    private List<Product> currentInventory = new ArrayList<>();

    //Method that created an External Inventory System based on a .txt-document containing all the items.
    public ExternalInventorySystem(){
        try {
            Scanner scnr = new Scanner(new File("src/integration/ProductList.txt"));
            String[] temp;
            Product product;
            int row = 0;

            //While-loop that continues as long as there is another product in the .txt-document.
            while(scnr.hasNextLine()){
                temp = scnr.nextLine().split("#");
                product = new Product(Integer.parseInt(temp[3]), Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), temp[2], row);
                currentInventory.add(product); //Parses the product information, and adds it to the inventory.
                row++;
            }
        }

        //Throws an exception if the file is not found.
        catch(FileNotFoundException e){
            System.out.println("Error, file not found. ");
            e.printStackTrace();
        }
    }

    public Product fetchProduct(int id){
        return currentInventory.get(id);
    }

    //Method to change a product into a readable string.
    public String toString(){
        String string = "";
        for (Product product : currentInventory) {
            string += "\n" + product.toString();
        }
        return string;
    }

    //Method that controls if a product is in stock using the product ID.
    public boolean checkIfProductIsInStock(int productID){
        //checks if a single item is in stock using barcode/ID
        if(currentInventory.size() >= productID) {
            Product id = currentInventory.get(productID);
            if (id.fetchProductQuantity() > 0)
                return true;
        }
        return false;
    }

    //Method that controls if several product are in stock using the product ID.
    public boolean checkIfProductIsInStock(int productID, int productQuantity){
        if(currentInventory.size() >= productID){
            Product id = currentInventory.get(productID);
            if(id.fetchProductQuantity() >= productQuantity)
                return true;
        }
        return false;
    }

    //Method to add a product to the External Inventory System.
    public void addProduct(Product product){
        currentInventory.add(product);
    }
}

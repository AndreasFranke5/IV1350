package integration;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class ExternalInventorySystemTest {
        private Product product;
        private ExternalInventorySystem ext;

    //Before each test, this method clears the inventory system and then adds products.
    @BeforeEach
    void initialize() {
        //beforeEach test is ran, we reset add some items to an inventory.
        ext = new ExternalInventorySystem();
        product = new Product(1, 8, 0.25, "Melon", 9);
        ext.addProduct(product);
        product = new Product(50, 2, 0.25, "Orange", 17);
        ext.addProduct(product);
    }

    @Test
    void checkIfProductIsInStock() {
        assertTrue("The product is out of stock", ext.checkIfProductIsInStock(8));
    }

    @Test
    void testAddProduct(){
        assertTrue("The product is in stock", ext.checkIfProductIsInStock(2));
    }
}
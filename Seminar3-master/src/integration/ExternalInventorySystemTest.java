package integration;

import model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests for the ExternalInventorySystem.
 */
class ExternalInventorySystemTest {
        private Product product;
        private ExternalInventorySystem ext;

    /**
     * Before each test, this method clears the inventory system and then adds products.
     */
    @BeforeEach
    void initialize() {
        ext = new ExternalInventorySystem();
        product = new Product(1, 8, 0.25, "Melon", 9);
        ext.addProduct(product);
        product = new Product(50, 2, 0.25, "Orange", 17);
        ext.addProduct(product);
    }

    /**
     * After each test, dismantles to null information.
     */
    @AfterEach
    void dismantle() {
        product = null;
        ext = null;
    }

    /**
     * Tests if a product is in stock.
     */
    @Test
    void checkIfProductIsInStock() {
        assertTrue("The product is out of stock", ext.checkIfProductIsInStock(8));
    }

    /**
     * Tests adding a product if it is in stock.
     */
    @Test
    void testAddProduct(){
        assertTrue("The product is in stock", ext.checkIfProductIsInStock(2));
    }
}
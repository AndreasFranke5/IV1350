package model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for products.
 */
class ProductTest {
    private Product product;
    private String str;

    /**
     * Before each test, this method adds a product.
     */
    @BeforeEach
    void initialize(){
        product = new Product(8, 4, 0.25, "\"Pear\"", 75);
        str = "1 \"pear\" - 4.0 kr";
    }

    /**
     * After each test, dismantles and changes the variable to null.
     */
    @AfterEach
    void dismantle(){
        product = null;
        str = null;
    }

    /**
     * Tests the toString method.
     */
    @Test
    void toStringTest(){
        boolean anticipatedResult = true;
        boolean result = str.equals(product.toString());
        assertEquals(anticipatedResult, result, "The toString method failed testing.");
        }
}
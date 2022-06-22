package integration;

import controller.Controller;
import model.Product;
import model.Scanner;
import model.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ProductNotFoundException.
 */
class ProductNotFoundExceptionTest {
    private ExternalInventorySystem ext;
    @BeforeEach
    void setUp() {
        ext = ExternalInventorySystem.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetIncorrectID() {
        boolean isCorrect = false;
        try{
            ext.fetchProduct(1);
        }
        catch (ProductNotFoundException productNotFoundException){
            if(productNotFoundException.getIncorrectID() == 1) isCorrect = true;
        }
        catch (DatabaseNotFoundException databaseNotFoundException){
            databaseNotFoundException.getAdminMessage();
        }
        assertTrue(isCorrect, "The fetchProductID method from ProductNotFoundException failed.");
    }

    @Test
    void testGetMessage() {
        boolean msgEqualsExpectedMessage = false;
        try{
            ext.fetchProduct(111);
        }
        catch (ProductNotFoundException exception){
            if(exception.getMessage().equals("The barcode is incorrect, product with ID 111 could not be found.")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseNotFoundException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "The getMessage method for ProductNotFoundException failed.");
    }

    @Test
    void testGetAdminMessage() {
        boolean msgEqualsExpectedMessage = false;
        try{
            ext.fetchProduct(5555);
        }
        catch (ProductNotFoundException exception){
            String s = exception.getAdminMessage();
            System.out.println(s);
            if(s.contains("\n" +
                    "The barcode is incorrect, product with ID 5555 could not be found.\n" +
                    "\n" +
                    " End of Log \n" +
                    "\n")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseNotFoundException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "The getAdminMessage method for ProductNotFoundException failed.");
    }
}
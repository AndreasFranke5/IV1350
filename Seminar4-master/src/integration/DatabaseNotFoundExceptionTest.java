package integration;

import model.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests if the database is found or not, using the hardcoded productID 1, and verifying the error message.
 */

class DatabaseNotFoundExceptionTest {

    private ExternalInventorySystem ext;
    @BeforeEach
    void setUp() {
        ext = ExternalInventorySystem.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests the getAdminMessage.
     */
    @Test
    void testGetAdminMessage() {
        boolean databaseErrorCorrespondsToError = false;

        try{
            ext.fetchProduct(1);
        }
        catch (DatabaseNotFoundException dbExc){
            String s = dbExc.getAdminMessage().substring(dbExc.getAdminMessage().indexOf('\n'));
            System.out.println(s);
            if(s.contains("Database was not found.")) databaseErrorCorrespondsToError = true;

        }
        catch (ProductNotFoundException ProductNoFound){
            ProductNoFound.getStackTrace();
        }
        assertTrue(databaseErrorCorrespondsToError, "Error in the database.");

    }
}
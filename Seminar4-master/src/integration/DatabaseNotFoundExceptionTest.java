package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests a databse not connecting by calling the hardcoded 6 to check if the error message is correct.
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
     * Should be changed to getMessage override- we should make our own function for user messages.
     */
    @Test
    void testGetAdminMessage() {
        boolean databaseErrorCorrespondsToError = false;

        ext.fetchProduct(6);
        assertTrue(databaseErrorCorrespondsToError, "Database error");

    }
}
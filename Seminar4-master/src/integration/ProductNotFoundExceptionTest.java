package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the itemNotFoundException
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
        ext.fetchProduct(29);
        assertTrue(isCorrect, "ID getter from internal exception ItemNotFoundException not working");



    }

    @Test
    void testGetMessage() {
        boolean msgEqualsExpectedMessage = false;

        ext.fetchProduct(213);
        assertTrue(msgEqualsExpectedMessage, "getMessage for itemNotFoundException not handled correctly");
    }

    @Test
    void testGetAdminMessage() {
        boolean msgEqualsExpectedMessage = false;

        ext.fetchProduct(2321);
        assertTrue(msgEqualsExpectedMessage, "getAdminMessage for itemNotFoundException test failed");
    }
}
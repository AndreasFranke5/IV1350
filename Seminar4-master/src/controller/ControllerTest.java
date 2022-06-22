package controller;

import controller.Controller;
import integration.DatabaseNotFoundException;
import integration.ProductNotFoundException;
import integration.TotalRevenueToFile;
import viewer.TotalRevenueViewer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the Controller.
 */
class ControllerTest {
    Controller control1;
    String string;

    /**
     * Sets up a basic controller before each test.
     */
    @BeforeEach
    void setUp() {
        control1 = new Controller(new TotalRevenueToFile(), new TotalRevenueViewer());
        control1.beginSale(1);
    }

    /**
     * Dismantles the test after completed.
     */
    @AfterEach
    void dismantle() {
        control1 = null;
        string = null;
    }

    /**
     * Test for beginning a new sale.
     * @throws DatabaseNotFoundException Never.
     * @throws ProductNotFoundException Never.
     */
    @Test
    void beginSaleTest() throws DatabaseNotFoundException, ProductNotFoundException {
        control1.beginSale(5);
        control1.addProduct(5);
        boolean result = control1.endSale(9999, "Test", "Test");
        assertTrue(result, "beginSaleTest failed.");
    }

    /**
     * Tests adding a product. Controls if the string contains the correct information.
     * @throws DatabaseNotFoundException Never.
     * @throws ProductNotFoundException Never.
     */
    @Test
    void addProductTest() throws DatabaseNotFoundException, ProductNotFoundException {
        boolean result = false;
        control1.addProduct(5);
        if (control1.getString().equals("7 - Clementine - 12.3 kr\n")) {
            result = true;
        }

        assertTrue(result, "Controller.addProduct failed testing.");
    }

    /**
     * Tests if a sale has ended. Does this by starting an empty sale.
     * Contains an empty string of items.
     */
    @Test
    void endSaleTest() {
        boolean result = false;
        control1.endSale(33, "Andreas", "Something");
        if ((control1.getString() == null || control1.getString() == "")) {
            result = true;
        }
        assertTrue(result, "Controller.endSaleTest failed the JUnit testing.");
    }

    /**
     * Test for the termination.
     */
    @Test
    void terminateTest() {
        boolean result = false;
        control1.terminate();
        if (control1.getString() == null) {
            result = true;
        }
        assertTrue(result, "Controller.terminateTest failed the JUnit testing.");
    }

    /**
     * Test for adding several products. Works by comparing strings.
     *
     * @throws DatabaseNotFoundException Never.
     * @throws ProductNotFoundException Never.
     */
    @Test
    void addProductsTest() throws DatabaseNotFoundException, ProductNotFoundException {
        boolean result = false;
        control1.addProduct(0, 2);
        if (control1.getString().equals("7 - Clementine - 12.3 kr\n")) {
            result = true;
        }
        assertTrue(result, "Controller.addProducts failed testing.");
    }

    /**
     * Checks if the 'undo' method works by adding a product, undoing it, then comparing with an empty cart.
     * @throws DatabaseNotFoundException Never.
     * @throws ProductNotFoundException Never.
     */
    @Test
    void undoTest() throws DatabaseNotFoundException, ProductNotFoundException {
        boolean isCorrect = false;
        control1.addProduct(1);
        control1.undo();
        if (control1.getString().equals("")) isCorrect = true;
        assertTrue(isCorrect, "The undo method fails to reset the shopping cart to 0.");
    }

    /**
     * Checks the 'undo' method by adding two items and then undoing both steps, then checking if it's equal
     * to an empty string.
     * @throws DatabaseNotFoundException Never.
     * @throws ProductNotFoundException Never.
     */
    @Test
    void undosTest() throws DatabaseNotFoundException, ProductNotFoundException {
        boolean isCorrect = false;
        control1.addProduct(1, 2);
        control1.addProduct(2, 2);
        control1.undo(2);
        if (control1.getString().equals("")) isCorrect = true;
        assertTrue(isCorrect, "The undo method fails to undo multiple operations.");


    }
}
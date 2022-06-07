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
/*class ControllerTest {
    Controller control1;
    String string;

    /**
     * Sets up a basic controller before each test.
     */
 /*   @BeforeEach
 /*   void setUp() {
        control1 = new Controller(new TotalRevenueToFile(), new TotalRevenueViewer());
        control1.beginSale(8);
    }

    /**
     * Dismantles the test after completed.
     */
/*    @AfterEach
    void dismantle() {
        control1 = null;
        string = null;
    }

    /**
     * Test for beginning a new sale.
     */
 /*   @Test
    void beginSale() throws DatabaseNotFoundException, integration.ProductNotFoundException {
        control1.beginSale(8);
        control1.addProduct(1);
        boolean result = control1.beginSale(3);
        assertTrue(result, "startNewSale controller error");
    }

    /**
     * Tests adding a product. Controls if the string contains the correct information.
     */
 /*   @Test
    void addProduct() {
        boolean result = false;
        control1.addProduct(5);
        Controller ctr2 = new Controller();
        ctr2.beginSale(4);
        ctr2.addProduct(6);
        if(ctr2.getString().equals(control.getString())){
            result = true;
        }

        assertTrue(result, "Controller.addProduct failed testing.");
    }

    /**
     * Tests if a sale has ended. Does this by starting an empty sale. Contains an empty string of items.
     */
/*    @Test
    void endSale() {
        boolean result = false;
        control.endSale(33, "Andreas", "Something");
        if(control.getString() == null){
            result = true;
        }
        assertTrue(result, "Controller.endSale failed the JUnit testing.");
    }

    /**
     * Test for the termination.
     */
/*    @Test
    void terminate() {
        boolean result = false;
        control.endSale();
        if(control.getString() == null){
            result = true;
        }
        assertTrue(result, "Controller.endSale failed the JUnit testing.");
    }

    /**
     * Test for adding several items. Works by comparing strings.
     */
/*    @Test
/*    void addProducts() {
        boolean result = false;
        control.addProduct(1, 4);
        Controller ctr2 = new Controller();
        ctr2.beginSale(2);
        ctr2.addProduct(5, 7);
        if(ctr2.getString().equals(control.getString())){
            result = true;
        }
        assertTrue(result, "Controller.addItems failed testing.");
    }
}*/
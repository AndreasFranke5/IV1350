package model;

import dataTransferObjects.SaleDTO;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Sale class.
 */
class SaleTest {
    private Sale sale;
    private ExternalAccountingSystem ac;

    /**
     * Before each test, this method adds a sale and External Accounting System instance.
     */
    @BeforeEach
    void initialize(){
        sale = new Sale();
        ac = new ExternalAccountingSystem();

    }

    /**
     * Tests adding a product.
     */
    @Test
    void addProduct() {
        boolean addingProduct = sale.addProducts(6,3,  new ExternalInventorySystem());
        boolean expectedResult = true;
        assertEquals(expectedResult, addingProduct, "The adding of a product failed the test.");
    }

    /**
     * Tests adding several products.
     */
    @Test
    void addProducts() {
        boolean trueCheck = sale.addProducts(1, 9, new ExternalInventorySystem());
        boolean anticipatedResult = true;
        assertEquals(anticipatedResult, trueCheck, "The adding of several products failed the test - control the supply or the product ID.");
    }

    /**
     * Tests ending a sale.
     */
    @Test
    void endSale() {
        sale.endSale();
        boolean result = sale.fetchCurrentSaleProgress();
        boolean anticipatedResult = false;
        assertEquals(anticipatedResult,result,"The sale was not successfully ended.");
    }

    /**
     * Tests fetching the current sale progress.
     */
    @Test
    void fetchCurrentSaleProgress() {
        sale.addProducts(73,55,new ExternalInventorySystem());
        boolean result = false;
        boolean anticipatedResult = true;
        SaleDTO t = sale.endSale("Andreas", "Checkout 3");
        ac.registerSale(t);
        if(ac.fetchSaleDTO(t.fetchSaleID()) != null && !sale.fetchCurrentSaleProgress()){
            result = true;
        }
        assertEquals(anticipatedResult, result, "The sale was not successfully fetched.");
    }
}
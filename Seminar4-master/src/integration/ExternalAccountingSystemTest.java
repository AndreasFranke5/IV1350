package integration;

import controller.Controller;
import dataTransferObjects.SaleDTO;
import model.Sale;
import viewer.TotalRevenueViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the ExternalAccountingSystem.
 */
class ExternalAccountingSystemTest {
    private Controller controller;
    private ExternalAccountingSystem ac;
    private SaleDTO dto;

    /**
     * Initialization method for testing the ExternalAccountingSystem.
     * @throws DatabaseNotFoundException Never.
     * @throws ProductNotFoundException Never.
     */
    @BeforeEach
    void initialize() throws DatabaseNotFoundException, ProductNotFoundException {
        controller = new Controller(new TotalRevenueToFile(), new TotalRevenueViewer());
        ac = new ExternalAccountingSystem();
        Sale temp = new Sale();
        controller.beginSale(5);
        controller.addProduct(5,5);
        dto = temp.endCurrentSale("NotAndreas", "Checkout 6");
    }

    /**
     * Testing if registerSale has the correct products when done.
     */
    @Test
    void registerSaleTest() {
        boolean result = false;
        ac.registerSale(dto);
        if(ac.fetchSaleDTO(dto.fetchSaleID()) != null){
            result = true;
        }
        assertTrue(result, "Registering a sale failed testing.");
    }

    /**
     * Testing if SaleDTO has received all necessary data after the fetch.
     */
    @Test
    void fetchSaleDTOTest() {
        boolean expected = true;
        ac.registerSale(dto);
        boolean result = dto.compareSaleDTO(ac.fetchSaleDTO(dto.fetchSaleID()));
        assertEquals(expected,result,"Fetching the SaleDTO failed testing.");
    }

    /**
     * Currently not used and can be ignored.
     */
    @Test
    void testFindAndGetSaleDTO() {
    }
}
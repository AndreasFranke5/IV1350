package integration;

import dataTransferObjects.SaleDTO;
import model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExternalAccountingSystemTest {
    private ExternalAccountingSystem ac;
    private SaleDTO dto;

    //Before each test, this method clears the accounting system and then adds a product.
    @BeforeEach
    void initialize() {
        ac = new ExternalAccountingSystem();
        Sale temp = new Sale();
        temp.addProducts(7,4 ,new ExternalInventorySystem());
        dto = temp.endSale("NotAndreas", "Checkout 6");
    }

    //Testing if registerSale has the correct products when done.
    @Test
    void registerSale() { //makes sure listOfSales contains the correct items after registering sales
        boolean result = false;
        ac.registerSale(dto);
        if(ac.fetchSaleDTO(dto.fetchSaleID()) != null){
            result = true;
        }
        assertTrue(result, "Registering a sale failed testing.");
    }

    //Testing if SaleDTO has received all necessary data after the fetch.
    @Test
    void fetchSaleDTO() {
        boolean expected = true;
        ac.registerSale(dto);
        boolean result = dto.equals(ac.fetchSaleDTO(dto.fetchSaleID()));
        assertEquals(expected,result,"Fetching the SaleDTO failed testing.");
    }
}
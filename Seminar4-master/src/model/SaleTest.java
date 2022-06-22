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
        sale.addProducts(1,1,ExternalInventorySystem.getInstance());

    }

    /**
     * Tests adding a product.
     */

    @Test
    void addProductTest() {
        boolean result = false;
        Sale newSale = new Sale();
        newSale.addProducts(1, 1, ExternalInventorySystem.getInstance());
        SaleDTO sDto = sale.endCurrentSale("","");
        SaleDTO nDTO = newSale.endCurrentSale("","");
        if(nDTO.fetchProducts().get(0).productID == sDto.fetchProducts().get(0).productID){
            result = true;
        }
        boolean expectedResult = true;
        assertEquals(expectedResult, result, "Failed adding the product.");
    }

    /**
     * Tests adding several products.
     */
    @Test
    void addProductsTest() {
        boolean addProductSuccess = sale.addProducts(1, 1, new ExternalInventorySystem());
        boolean anticipatedResult = true;
        assertEquals(anticipatedResult, addProductSuccess, "The adding of several products" +
                " failed the test - control the supply or the product ID.");
    }

    /**
     * Tests ending a sale.
     */
    @Test
    void terminateSaleTest() {
        sale.terminateSale();
        boolean result = sale.fetchCurrentSaleProgress();
        boolean anticipatedResult = false;
        assertEquals(anticipatedResult,result,"The sale was not successfully ended.");
    }

    /**
     * Tests fetching the current sale progress.
     */
    @Test
    void endSaleTest() {
        sale.addProduct(new Product(1, 1, 1, "Something", 1), ExternalInventorySystem.getInstance());
        boolean result = false;
        boolean anticipatedResult = true;
        SaleDTO t = sale.endCurrentSale("Andreas", "Checkout 3");
        ac.registerSale(t);
        if(ac.fetchSaleDTO(t.fetchSaleID()) != null && !sale.fetchCurrentSaleProgress()){
            result = true;
        }
        assertEquals(anticipatedResult, result, "The sale was not successfully fetched.");
    }
}
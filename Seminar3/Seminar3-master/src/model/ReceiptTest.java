package model;

import dataTransferObjects.SaleDTO;
import dataTransferObjects.StoreDTO;
import integration.ExternalInventorySystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptTest {
    private String str;
    private Receipt rec;

    //Tests if a string works with DTO.
    @BeforeEach
    void initialize() {
        str = "COOP\n" + "Järfällavägen 5\n" + " 0701234567\n\n" +
                "Cashier: Andreas\n" + java.time.LocalTime.now().toString().substring(0, 8) +
                 " " + java.time.LocalDate.now().toString() + "\n" +
                "Store\n" + "5 bananas - 15.0 kr \n" + "Subtotal: 90.0 \n" + "Sales tax: 34.567 kr\n" + "Total: 112.5 kr\n";

        Product bananas = new Product(6, 15.0, 0.25, "\"chips\"", 3);
        List<Product> productList = new ArrayList<>();
        productList.add(bananas);
        Sale sale = new Sale();
        sale.addProducts(55, 5, new ExternalInventorySystem());
        SaleDTO s = sale.endSale("Andreas", "Store");
        rec = new Receipt(s, new StoreDTO("COOP", "Järfällavägen 5", "0701234567"));

    }

    //Changes the variable to null after test.
    @AfterEach
    void dismantle() {
        str = null;
        rec = null;
    }

    //Tests if toString works.
    @Test
    void receiptToStringTest(){
        boolean anticipatedResult = true;
        String st2 = rec.toString();
        boolean result = str.equals(st2);
        assertEquals(anticipatedResult, result, "The receipt and preferred output do not match.");
    }
}
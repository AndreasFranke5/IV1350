package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    Controller control;
    @BeforeEach
    void setUp() {
        control = new Controller();
        control.beginSale(8);
    }

    @AfterEach
    void dismantle() {
    }

    @Test
    void beginSale() {
        boolean result = control.beginSale(3);
        assertTrue(result, "startNewSale controller error");
    }

    @Test
    void addProduct() {
        boolean result = false;
        control.addProduct(5);
        Controller ctr2 = new Controller();
        ctr2.beginSale(4);
        ctr2.addProduct(6);
        if(ctr2.getString().equals(control.getString())){
            result = true;
        }

        assertTrue(result, "Controller.addProduct failed testing.");
    }

    @Test
    void endSale() {
        boolean result = false;
        control.endSale(33, "Andreas", "Something");
        if(control.getString() == null){
            result = true;
        }
        assertTrue(result, "Controller.endSale failed the JUnit testing.");
    }

    @Test
    void terminate() {
        boolean result = false;
        control.endSale();
        if(control.getString() == null){
            result = true;
        }
        assertTrue(result, "Controller.endSale failed the JUnit testing.");
    }

    @Test
    void addProducts() {
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
}
package integration;

import dataTransferObjects.SaleDTO;
import java.util.ArrayList;
import java.util.List;

public class ExternalAccountingSystem {
    List<SaleDTO> everySale;

    //Adds a new constructor for the AccountingSystem.
    public ExternalAccountingSystem() {
        everySale = new ArrayList<>();
        for(int i = 0; i < 99999 ; i ++){
            everySale.add(null);
        }
    }

    //Method that registers a new sale onto the SaleList stored in AccountingSystem.
    public boolean registerSale(SaleDTO saleDTO){
        if(everySale.get(saleDTO.fetchSaleID()) != null){
            everySale.set(saleDTO.fetchSaleID(),saleDTO);
            return true;
        }
        return false;
    }

    //Method that uses the saleID to fetch the DTO. This is possible due to assigning every sale an index value.
    public SaleDTO fetchSaleDTO(int saleID) {
        SaleDTO temporary = everySale.get(saleID);
        return temporary.copyOfSale(); //Uses a deep copy to not change the original sale value.
    }
}

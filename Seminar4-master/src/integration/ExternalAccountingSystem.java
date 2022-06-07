package integration;

import dataTransferObjects.SaleDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * ExternalAccountingSystem is responsible for the handling of past ended sales. Limited to 99999 different values of SaleDTO.
 */
public class ExternalAccountingSystem {
    List<SaleDTO> everySale;

    /**
     * Adds a new constructor for the ExternalAccountingSystem. Adds null sales to a list.
     */
    public ExternalAccountingSystem() {
        everySale = new ArrayList<>();
        for(int i = 0; i < 99999 ; i ++){
            everySale.add(null);
        }
    }

    /**
     * Registers a new sale onto the SaleList stored in ExternalAccountingSystem.
     * @param saleDTO added to the ExternalAccountingSystem.
     * @return false if the SaleID already exists, otherwise true.
     */
    public boolean registerSale(SaleDTO saleDTO){
        if(everySale.get(saleDTO.fetchSaleID()) != null){
            everySale.set(saleDTO.fetchSaleID(),saleDTO);
            return true;
        }
        return false;
    }

    /**
     * Uses the SaleID to fetch the DTO. This is possible due to assigning every sale an index value.
     * Uses a deep copy to not change the original sale value.
     * @param saleID of the DTO.
     * @return a temporary copy of the sale.
     */
    public SaleDTO fetchSaleDTO(int saleID) {
        SaleDTO temporary = everySale.get(saleID);
        return temporary.copyOfSale();
    }
}

package dataTransferObjects;

/**
 * StoreDTO contains the store name, store address and store phone number.
 */
public class StoreDTO{

    private String storeName; 
    private String storeAddress;
    private String storePhoneNumber;

    /**
     * Constructor method for the StoreDTO.
     * @param name is the store name.
     * @param address is the store address.
     * @param number is the store phone number.
     */
    public StoreDTO(String name, String address, String number){
        storeName = name;
        storeAddress = address;
        storePhoneNumber = number;
    }

    /**
     * Fetches the store name.
     * @return the store name.
     */
    public String fetchStoreName(){
        return storeName;
    }

    /**
     * Fetches the store address.
     * @return the store address.
     */
    public String fetchStoreAddress(){
        return storeAddress;
    }

    /**
     * Fetches the store phone number.
     * @return the store phone number.
     */
    public String fetchStorePhoneNumber(){
        return storePhoneNumber;
    }
}
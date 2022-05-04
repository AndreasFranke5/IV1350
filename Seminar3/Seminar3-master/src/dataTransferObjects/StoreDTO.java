package dataTransferObjects;

public class StoreDTO{

    private String storeName; 
    private String storeAddress;
    private String storePhoneNumber;
     
    //Constructor method for StoreDTO.
    public StoreDTO(String name, String address, String number){
        storeName = name;
        storeAddress = address;
        storePhoneNumber = number;
    }

    public String fetchStoreName(){
        return storeName;
    }
    public String fetchStoreAddress(){
        return storeAddress;
    }
    public String fetchStorePhoneNumber(){
        return storePhoneNumber;
    }
}
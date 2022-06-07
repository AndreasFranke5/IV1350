package integration;

/**
 * ProductNotFoundException handles an item missing when getting it. Usually this is just an indication of indexOutOfBounds
 * in the big array- but this will be expanded when we change to a hashmap
 */
public class ProductNotFoundException extends Exception {
    private int itemIdentifier;
    /**
     * Constructor for INFException
     */
    public ProductNotFoundException(int id, String errorMsg){
        super(errorMsg);
        this.itemIdentifier = id;
    }

    /**
     * Returns the id of the error
     * @return the ID that was requested for
     */
    public int getIncorrectID(){
        return this.itemIdentifier;
    }


    /**
     * Admin message for writing to errorLogs
     * @return
     */
    public String getAdminMessage() {
        StringBuilder strBu = new StringBuilder();
        strBu.append("Item Exception -" + java.time.LocalDate.now() + java.time.LocalTime.now() + "\n");
        strBu.append(this.getMessage() + "\n");
        strBu.append("\n End of Log \n\n");
        return strBu.toString();
    }
}

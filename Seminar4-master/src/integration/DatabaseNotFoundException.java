package integration;

/**
 * Exception for handling when a database has been disconnected from the client.
 * Saves data to errorLogs and to System.print
 */
public class DatabaseNotFoundException extends Exception{

    public DatabaseNotFoundException(String messageForUser, Exception excep){
        super(messageForUser,excep);
    }

    /**
     * GetAdmin message is used to store the final version of the stacktrace- should honestly store the full amount
     * by running through the Array.
     * @return Returns a string containing the stack, a message, the time and date.
     */
    public String getAdminMessage(){
        StringBuilder strBu = new StringBuilder();
        strBu.append("Database Exception -  " + java.time.LocalDateTime.now()+ "\n");
        strBu.append(this.getMessage() + "\n");
        strBu.append(this.getStackTrace()[0].toString());
        strBu.append("\nEnd of Log \n\n");
        return strBu.toString();
    }
}

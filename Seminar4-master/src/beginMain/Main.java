package beginMain;

import controller.*;
import viewer.*;
import integration.TotalRevenueToFile;

public class Main {
    /**
     * Starts the program. Calls the viewer that then creates the rest, with hardcoded calls to create a grocery list.
     * @param args
     */
    public static void main(String[] args) {
        TotalRevenueViewer view = new TotalRevenueViewer();
        TotalRevenueToFile file = new TotalRevenueToFile();
        Controller controller = new Controller(file, view);
        Viewer viewer = new Viewer(controller);
        viewer.hardcodeControlCalls();
    }
}

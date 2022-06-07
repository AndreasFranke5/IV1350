package beginMain;

import controller.Controller;
import integration.DatabaseNotFoundException;
import integration.ProductNotFoundException;
import viewer.TotalRevenueViewer;
import viewer.Viewer;
import integration.TotalRevenueToFile;

public class Main {
    /**
     * Starts the program. Calls the viewer that then creates the rest, with hardcoded calls to create a grocery list.
     * @param args
     */
    public static void main(String[] args) throws DatabaseNotFoundException, ProductNotFoundException {
        TotalRevenueViewer view = new TotalRevenueViewer();
        TotalRevenueToFile file = new TotalRevenueToFile();
        Controller controller = new Controller(file, view);
        Viewer viewer = new Viewer(controller);
        viewer.hardcodeControlCalls();
    }
}

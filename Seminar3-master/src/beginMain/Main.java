package beginMain;

import controller.Controller;
import viewer.Viewer;

public class Main {
    /**
     * Starts the program. Calls the viewer that then creates the rest, with hardcoded calls to create a grocery list.
     * @param args
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        Viewer viewer = new Viewer(controller);
        viewer.hardcodeControlCalls();
    }
}

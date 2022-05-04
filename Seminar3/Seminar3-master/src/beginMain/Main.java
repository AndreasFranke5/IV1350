package beginMain;

import controller.Controller;
import viewer.Viewer;

public class Main {
    public static void main(String[] args) {
        //Hardcoded calls to view -> hardcoded calls to controller
        Controller controller = new Controller();
        Viewer viewer = new Viewer(controller);
        viewer.hardcodeControlCalls();
    }
}

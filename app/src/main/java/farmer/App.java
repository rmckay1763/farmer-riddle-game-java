package farmer;

import javax.swing.SwingUtilities;

import farmer.controller.GameController;
import farmer.model.Model;
import farmer.view.GameView;

public class App {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Model model = new Model();
                GameView view = new GameView();
                GameController controller = new GameController(model, view);
                controller.addListeners();
                controller.addItems();
            }
        });
    }
}

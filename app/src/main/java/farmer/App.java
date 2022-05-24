package farmer;

import javax.swing.SwingUtilities;

import farmer.view.View;

public class App {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                view.foo();
            }
        });
    }
}

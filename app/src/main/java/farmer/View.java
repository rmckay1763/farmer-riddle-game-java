package farmer;

import javax.swing.JFrame;

public class View extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;
    private static final int PADDING = 20;

    public View() {
        super("Farmer Riddle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(PADDING, PADDING);
        setVisible(true);
    }
    
}

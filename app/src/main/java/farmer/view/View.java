package farmer.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class View extends JFrame {

    private static final int WIDTH = 1250;
    private static final int HEIGHT = 750;
    private static final int PADDING = 20;

    private River river;

    public View() {
        super("Farmer Riddle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(PADDING, PADDING);
        river = new River(false);
        JPanel riverPanel = new JPanel();
        riverPanel.add(river);
        add(riverPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void moveBoat() {
        river = new River(true);
        JPanel riverPanel = new JPanel();
        riverPanel.add(river);
        add(riverPanel, BorderLayout.CENTER);
    }
    
}

package farmer.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class View extends JFrame {

    private static final int WIDTH = 1250;
    private static final int HEIGHT = 750;
    private static final int PADDING = 20;

    private ImageLib images;
    private River river;
    JButton crossRiverButton;

    public View() {
        super("Farmer Riddle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(PADDING, PADDING);
        images = ImageLib.getInstance();

        river = new River(images.river, images.boatEmpty);
        JPanel riverPanel = new JPanel();
        riverPanel.add(river);

        crossRiverButton = new JButton("Cross River");
        crossRiverButton.addActionListener(e -> {
            river.startAnimation();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(crossRiverButton);

        add(riverPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void moveBoat() {
        river.startAnimation();
    }

    public void foo() {}
    
}

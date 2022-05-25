package farmer.view;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import farmer.model.ImageLib;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameView extends JFrame {

    private static final int WIDTH = 1250;
    private static final int HEIGHT = 750;
    private static final int PADDING = 20;

    private RiverView river;
    private JPanel eastRiverBank;
    private JPanel westRiverBank;
    private JPanel contentPanel;
    private JButton crossRiverButton;

    public GameView() {
        super("Farmer Riddle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(PADDING, PADDING);

        river = new RiverView(ImageLib.getRiver());
        contentPanel = new JPanel();
        contentPanel.add(river);

        // eastRiverBank = new JPanel(new GridLayout(0, 1, 5, 5));
        // westRiverBank = new JPanel(new GridLayout(0, 1, 5, 5));
        eastRiverBank = new JPanel();
        eastRiverBank.setLayout(new BoxLayout(eastRiverBank, BoxLayout.Y_AXIS));
        westRiverBank = new JPanel();
        westRiverBank.setLayout(new BoxLayout(westRiverBank, BoxLayout.Y_AXIS));

        crossRiverButton = new JButton("Cross River");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(crossRiverButton);

        add(contentPanel, BorderLayout.CENTER);
        add(westRiverBank, BorderLayout.WEST);
        add(eastRiverBank, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public RiverView getRiver() {
        return river;
    }

    public void addItemOnEastRiverBank(ImageIcon itemIcon) {
        JButton itemButton = new JButton(itemIcon);
        eastRiverBank.add(itemButton);
    }

    public void addItemOnWestRiverBank(ImageIcon itemIcon) {
        JButton itemButton = new JButton(itemIcon);
        westRiverBank.add(itemButton);
    }

    public void moveBoat(int boatOffset) {
        river.setBoatOrigin(boatOffset);
        river.repaint();
    }

    public void addCrossRiverButtonListener(ActionListener listener) {
        crossRiverButton.addActionListener(listener);
    }

    public void enableActions(boolean enable) {
        crossRiverButton.setEnabled(enable);
    }
    
}

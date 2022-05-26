package farmer.view;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import farmer.model.Item;
import farmer.model.Location;

public class GameView extends JFrame {

    public static final String DEFAULT_MESSAGE = "Help the farmer get all items across the river";
    public static final String GAME_LOST_MESSAGE = "Game over. You lost.";
    public static final String GAME_WON_MESSAGE = "YOU WON!";

    private static final int WIDTH = 1250;
    private static final int HEIGHT = 750;
    private static final int PADDING = 20;

    private RiverView river;
    private RiverBankView eastRiverBank;
    private RiverBankView westRiverBank;
    private JButton unloadItemButton;
    private JButton crossRiverButton;
    private JLabel gameStatusLabel;

    public GameView() {
        super("Farmer Riddle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(PADDING, PADDING);

        river = new RiverView(ImageLib.getRiver());
        westRiverBank = new RiverBankView("West River Bank", Location.WEST_BANK);
        westRiverBank.showAllItems();
        eastRiverBank = new RiverBankView("East River Bank", Location.EAST_BANK);

        JPanel contentPanel = new JPanel();
        contentPanel.add(westRiverBank);
        contentPanel.add(river);
        contentPanel.add(eastRiverBank);

        unloadItemButton = new JButton("Unload Item");
        unloadItemButton.setEnabled(false);
        crossRiverButton = new JButton("Cross River");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(unloadItemButton);
        buttonPanel.add(crossRiverButton);

        gameStatusLabel = new JLabel(DEFAULT_MESSAGE);
        JPanel gameStatusPanel = new JPanel();
        gameStatusPanel.add(gameStatusLabel);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(gameStatusPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void setBoatImage(Item item) {
        switch(item) {
            case NONE:
                river.setBoat(ImageLib.getBoatDefault());
                break;
            case CHICKEN:
                river.setBoat(ImageLib.getBoatChicken());
                break;
            case FOX:
                river.setBoat(ImageLib.getBoatFox());
                break;
            case GRAIN:
                river.setBoat(ImageLib.getBoatGrain());
                break;
        }
        river.repaint();
    }

    public void setGameStatusMessage(String message) {
        gameStatusLabel.setText(message);
    }

    public void moveBoat(int offset) {
        river.setBoatOrigin(offset);
        river.repaint();
    }

    public void showItem(Item item, Location location) {
        switch(location) {
            case EAST_BANK:
                eastRiverBank.showItem(item);
                break;
            case WEST_BANK:
                westRiverBank.showItem(item);
                break;
        }
    }

    public void hideItem(Item item, Location location) {
        switch(location) {
            case EAST_BANK:
                eastRiverBank.hideItem(item);
                break;
            case WEST_BANK:
                westRiverBank.hideItem(item);
                break;
        }
    }

    public void setUnloadItemEnabled(boolean enable) {
        unloadItemButton.setEnabled(enable);
    }

    public void setCrossRiverEnabled(boolean enable) {
        crossRiverButton.setEnabled(enable);
    }

    public void disableAllActions() {
        setUnloadItemEnabled(false);
        setCrossRiverEnabled(false);
    }

    public void addOnItemSelectedListener(ActionListener listener) {
        eastRiverBank.addOnItemSelectedListener(listener);
        westRiverBank.addOnItemSelectedListener(listener);
    }

    public void addOnUnloadItemListener(ActionListener listener) {
        unloadItemButton.addActionListener(listener);
    }

    public void addOnCrossRiverListener(ActionListener listener) {
        crossRiverButton.addActionListener(listener);
    }
}

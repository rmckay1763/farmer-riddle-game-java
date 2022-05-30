package farmer.view;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import farmer.model.Item;
import farmer.model.Location;

public class GameView extends JFrame {

    public static final String DEFAULT_MESSAGE = "Help the farmer get all items across the river";
    private static final int OFFSET = 20;
    private static final Color GREEN = new Color(5, 105, 0);
    private static final Color BLONDE = new Color(246, 236, 213);

    private RiverView river;
    private RiverBankView eastRiverBank;
    private RiverBankView westRiverBank;
    private JButton unloadItemButton;
    private JButton crossRiverButton;
    private JButton restartGameButton;
    private JLabel statusLabel;

    public GameView() {
        super("Farmer Riddle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(OFFSET, OFFSET);

        river = new RiverView(ImageLib.RIVER);
        westRiverBank = new RiverBankView(Location.WEST_BANK);
        westRiverBank.showAllItems();
        eastRiverBank = new RiverBankView(Location.EAST_BANK);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(GREEN);
        contentPanel.add(westRiverBank);
        contentPanel.add(river);
        contentPanel.add(eastRiverBank);

        unloadItemButton = new ControlButton("Unload Boat", ImageLib.UNLOAD_ICON, ImageLib.UNLOAD_ICON_HOVER);
        crossRiverButton = new ControlButton("Cross River", ImageLib.CROSS_ICON, ImageLib.CROSS_ICON_HOVER);
        restartGameButton = new ControlButton("Restart Game", ImageLib.RESTART_ICON, ImageLib.RESTART_ICON_HOVER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(BLONDE);
        controlPanel.add(unloadItemButton);
        controlPanel.add(crossRiverButton);
        controlPanel.add(restartGameButton);

        statusLabel = new JLabel(DEFAULT_MESSAGE);
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(BLONDE);
        statusPanel.add(statusLabel);

        add(contentPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(statusPanel, BorderLayout.NORTH);

        pack();
        setVisible(true);
    }

    public void setInitialState() {
        eastRiverBank.hideAllItems();
        westRiverBank.showAllItems();
        setBoatImage(Item.NONE);
        moveBoat(0);
        setStatusMessage(GameView.DEFAULT_MESSAGE);
    }

    public void setBoatImage(Item item) {
        switch(item) {
            case NONE:
                river.setBoat(ImageLib.BOAT_DEFAULT);
                break;
            case CHICKEN:
                river.setBoat(ImageLib.BOAT_CHICKEN);
                break;
            case FOX:
                river.setBoat(ImageLib.BOAT_FOX);
                break;
            case GRAIN:
                river.setBoat(ImageLib.BOAT_GRAIN);
                break;
        }
        river.repaint();
    }

    public void setStatusMessage(String message) {
        statusLabel.setText(message);
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

    public void resetItems() {
        westRiverBank.showAllItems();
        eastRiverBank.hideAllItems();
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

    public void addOnRestartGameListener(ActionListener listener) {
        restartGameButton.addActionListener(listener);
    }
}

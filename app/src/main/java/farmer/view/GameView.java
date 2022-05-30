package farmer.view;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import farmer.model.Item;
import farmer.model.Location;

/**
 * Main view for the game. Contains the content panel, control panel, and status panel.
 */
public class GameView extends JFrame {

    private static final int OFFSET = 20;

    /**
     * The panel for displaying the status message after each action.
     */
    private StatusPanel statusPanel;

    /**
     * The panel for displaying the game content: river, boat, items on each river bank.
     */
    private ContentPanel contentPanel;

    /**
     * The panel for displaying the control buttons to perform game actions.
     */
    private ControlPanel controlPanel;

    /**
     * Constructor. Instantiates and adds the content panel, control panel, and status panel.
     */
    public GameView() {

        super("Farmer Riddle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(OFFSET, OFFSET);

        controlPanel = new ControlPanel();
        contentPanel = new ContentPanel();
        statusPanel = new StatusPanel();

        add(statusPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }

    /**
     * Sets the initial state of the content panel for a new game.
     */
    public void setInitialState() {

        contentPanel.setInitialState();
    }

    /**
     * Sets the boat image in the content panel.
     * 
     * @param item The item in the boat for selecting the boat image.
     */
    public void setBoatImage(Item item) {

        contentPanel.setBoatImage(item);
    }

    /**
     * Sets the status message in the status panel.
     * 
     * @param message Message from the model with the result of an action.
     */
    public void setStatusMessage(String message) {

        statusPanel.setMessage(message);
    }

    /**
     * Moves the boat in the content panel.
     * 
     * @param offset The offset from the west river bank to paint the boat.
     */
    public void moveBoat(int offset) {

        contentPanel.moveBoat(offset);
    }

    /**
     * Shows an item in the content panel for the specified river bank.
     * 
     * @param item The item to show.
     * @param location The location of the item to show.
     */
    public void showItem(Item item, Location location) {

        contentPanel.showItem(item, location);
    }

    /**
     * Hides an item in the content panel for the specified river bank.
     * 
     * @param item The item to hide.
     * @param location The location of the item to hide.
     */
    public void hideItem(Item item, Location location) {

        contentPanel.hideItem(item, location);
    }

    /**
     * Adds a listener for when the user selects an item in the content panel.
     * 
     * @param listener ActionListener for when the user selects an item.
     */
    public void addItemSelectedListener(ActionListener listener) {

        contentPanel.addItemSelectedListener(listener);
    }

    /**
     * Adds a listener for when the user selects the unload boat button.
     * 
     * @param listener ActionListener for processing events from the unload boat button.
     */
    public void addUnloadBoatListener(ActionListener listener) {

        controlPanel.addUnloadBoatListener(listener);
    }

    /**
     * Adds a listener for the when user selects to cross the river.
     * 
     * @param listener ActionListener for processing events from the cross river button.
     */
    public void addCrossRiverListener(ActionListener listener) {

        controlPanel.addCrossRiverListener(listener);
    }

    /**
     * Adds a listener for when the user selects the restart button.
     * 
     * @param listener ActionListener for processing events from the restart button.
     */
    public void addRestartListener(ActionListener listener) {

        controlPanel.addRestartListener(listener);
    }

    /**
     * Adds a listener for when the user selects the help button.
     * 
     * @param listener ActionListener for processing events from the help button.
     */
    public void addHelpListener(ActionListener listener) {
        
        controlPanel.addHelpListener(listener);
    }
}

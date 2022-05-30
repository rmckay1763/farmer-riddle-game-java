package farmer.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import farmer.model.Item;
import farmer.model.Location;

/**
 * Displays the content for the game including the river, boat, and items on each river bank.
 */
public class ContentPanel extends JPanel {

    private static final Color GREEN;

    static {
        GREEN = new Color(5, 105, 0);
    }

    /**
     * The RiverView for displaying the river image and the boat.
     */
    private RiverView river;

    /**
     * RiverBankView for displaying items on the east bank of the river.
     */
    private RiverBankView eastRiverBank;

    /**
     * RiverBankView for displaying items on the west bank of the river.
     */
    private RiverBankView westRiverBank;

    /**
     * Constructor. Instantiates the panel for the river view and the east and west bank views.
     */
    public ContentPanel() {

        super();
        setBorder(new EmptyBorder(0, 50, 0, 50));
        setBackground(GREEN);

        river = new RiverView(ImageLib.RIVER);
        westRiverBank = new RiverBankView(Location.WEST_BANK);
        eastRiverBank = new RiverBankView(Location.EAST_BANK);

        add(westRiverBank);
        add(river);
        add(eastRiverBank);
    }

    /**
     * Sets the inital state for a new game. All items on west bank and default boat image.
     */
    public void setInitialState() {

        eastRiverBank.hideAllItems();
        westRiverBank.showAllItems();
        setBoatImage(Item.NONE);
        moveBoat(RiverView.BOAT_START_OFFSET);
    }

    /**
     * Sets the boat image and repaints the river view.
     * 
     * @param item Item to determine which boat image to paint.
     */
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
    
    /**
     * Moves the boat one step accross the river.
     * 
     * @param offset Step offset from the west bank of the river.
     */
    public void moveBoat(int offset) {

        river.setBoatOrigin(offset);
        river.repaint();
    }

    /**
     * Shows an item on the river bank for the specified location.
     * 
     * @param item The item to show.
     * @param location The location of the item to show.
     */
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

    /**
     * Hides an item on the river bank for the specified location.
     * 
     * @param item The item to hide.
     * @param location The location of the item to hide.
     */
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

    /**
     * Adds a listener for when the user selects and item from a river bank.
     * 
     * @param listener ActionListener for processing events when the user selects an item.
     */
    public void addItemSelectedListener(ActionListener listener) {

        eastRiverBank.addItemSelectedListener(listener);
        westRiverBank.addItemSelectedListener(listener);
    }

}

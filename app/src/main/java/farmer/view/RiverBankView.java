package farmer.view;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JPanel;
import farmer.model.Item;
import farmer.model.Location;

/**
 * Displays the items on the river bank. 
 * Uses setVisible(false) to hide items and setVisible(true) to show items.
 */
public class RiverBankView extends JPanel {

    /**
     * List of ItemButtons for each of the farmers items.
     */
    private ArrayList<ItemButton> itemButtons;

    /**
     * Constructor. Instantiates a ItemButton for each item and adds to the panel.
     * All ItemButtons are initially not visible.
     * 
     * @param location The location of the river bank (EAST_BANK or WEST_BANK).
     */
    public RiverBankView(Location location) {

        super();
        setOpaque(false);
        GridLayout layout = new GridLayout(3, 1);
        setLayout(layout);

        itemButtons = new ArrayList<>();
        itemButtons.add(new ItemButton(ImageLib.CHICKEN_ICON, Item.CHICKEN, location));
        itemButtons.add(new ItemButton(ImageLib.FOX_ICON, Item.FOX, location));
        itemButtons.add(new ItemButton(ImageLib.GRAIN_ICON, Item.GRAIN, location));

        for (ItemButton button : itemButtons) {
            add(button);
        }
    }

    /**
     * Makes the specified item in this river bank view visible.
     * 
     * @param target The item to show.
     */
    public void showItem(Item target) {

        for (ItemButton button : itemButtons) {

            if (button.getItem() == target) {
                button.setVisible(true);
                return;
            }
        }
    }

    /**
     * Makes the specified item in this river bank view not visible.
     * 
     * @param target The item to hide.
     */
    public void hideItem(Item target) {

        for (ItemButton button : itemButtons) {

            if (button.getItem() == target) {
                button.setVisible(false);
                return;
            }
        }
    }

    /**
     * Makes all items in this river bank view visible.
     */
    public void showAllItems() {

        for (ItemButton button : itemButtons) {
            button.setVisible(true);
        }
    }

    /**
     * Makes all items in this river bank view not visible.
     */
    public void hideAllItems() {

        for (ItemButton button : itemButtons) {
            button.setVisible(false);
        }
    }

    /**
     * Adds a listener for when the user selects an item from this river bank view.
     * @param listener ActionListener for processing events when the user selects an item.
     */
    public void addItemSelectedListener(ActionListener listener) {
        
        for (ItemButton item : itemButtons) {
            item.addActionListener(listener);
        }
    }
}

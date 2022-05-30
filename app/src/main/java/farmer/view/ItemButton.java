package farmer.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import farmer.model.Item;
import farmer.model.Location;

/**
 * Button for displaying an item on the river bank.
 */
public class ItemButton extends JButton implements  MouseListener {

    private static final Color BROWN;
    private static final Color BLONDE;

    static {
        BROWN = new Color(94, 57, 32);
        BLONDE = new Color(246, 236, 213);
    }

    /**
     * The type of item this button displays.
     */
    private Item item;

    /**
     * The location of the item this button displays
     */
    private Location itemLocation;

    /**
     * Constructor. Sets the button icon, item and location.
     * 
     * @param itemIcon The icon the button displays.
     * @param item The type of item this button represents.
     * @param itemLocation The location of the item. (EAST_BANK or WEST_BANK).
     */
    public ItemButton(ImageIcon itemIcon, Item item, Location itemLocation) {

        super(itemIcon);
        this.item = item;
        this.itemLocation = itemLocation;

        setBorder(BorderFactory.createBevelBorder(1, BROWN, BLONDE));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setVisible(false);
        addMouseListener(this);
    }

    /**
     * Gets the Item this button represents.
     * 
     * @return The Item for this button.
     */
    public Item getItem() {

        return item;
    }

    /**
     * Gets the location of the button.
     * @return The location of the item relative to the river (EAST_BANK or WEST_BANK).
     */
    public Location getItemLocation() {

        return itemLocation;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        setBorderPainted(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {

        setBorderPainted(false);
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}

    
}

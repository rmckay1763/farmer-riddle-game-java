package farmer.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import farmer.model.Item;
import farmer.model.Location;

public class ItemButton extends JButton {

    private Item item;
    private Location itemLocation;

    public ItemButton(ImageIcon itemIcon, Item item, Location location) {
        super(itemIcon);
        this.item = item;
        this.itemLocation = location;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setVisible(false);
    }

    public Item getItem() {
        return item;
    }

    public Location getItemLocation() {
        return itemLocation;
    }
}

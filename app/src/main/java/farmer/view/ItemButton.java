package farmer.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import farmer.model.Item;
import farmer.model.Location;

public class ItemButton extends JButton implements  MouseListener {

    private static final Color BROWN = new Color(94, 57, 32);
    private static final Color BLONDE = new Color(246, 236, 213);

    private Item item;
    private Location itemLocation;

    public ItemButton(ImageIcon itemIcon, Item item, Location location) {
        super(itemIcon);
        this.item = item;
        this.itemLocation = location;
        this.setBorder(BorderFactory.createBevelBorder(1, BROWN, BLONDE));
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setVisible(false);
        this.addMouseListener(this);
    }

    public Item getItem() {
        return item;
    }

    public Location getItemLocation() {
        return itemLocation;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {}

    
    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {
        this.setBorderPainted(true);
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        this.setBorderPainted(false);
    }
}

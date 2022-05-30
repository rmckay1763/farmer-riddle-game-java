package farmer.view;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JPanel;
import farmer.model.Item;
import farmer.model.Location;

public class RiverBankView extends JPanel {

    private ArrayList<ItemButton> itemButtons;

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

    public void showItem(Item target) {
        for (ItemButton button : itemButtons) {
            if (button.getItem() == target) {
                button.setVisible(true);
                return;
            }
        }
    }

    public void hideItem(Item target) {
        for (ItemButton button : itemButtons) {
            if (button.getItem() == target) {
                button.setVisible(false);
                return;
            }
        }
    }

    public void showAllItems() {
        for (ItemButton button : itemButtons) {
            button.setVisible(true);
        }
    }

    public void hideAllItems() {
        for (ItemButton button : itemButtons) {
            button.setVisible(false);
        }
    }

    public void addOnItemSelectedListener(ActionListener listener) {
        for (ItemButton item : itemButtons) {
            item.addActionListener(listener);
        }
    }
}

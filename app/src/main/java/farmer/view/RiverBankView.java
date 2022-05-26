package farmer.view;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import farmer.model.Item;
import farmer.model.Location;

public class RiverBankView extends JPanel {
    private JLabel title;
    private ArrayList<ItemButton> itemButtons;

    public RiverBankView(String title, Location location) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        itemButtons = new ArrayList<>();
        itemButtons.add(new ItemButton(ImageLib.getChickeIcon(), Item.CHICKEN, location));
        itemButtons.add(new ItemButton(ImageLib.getFoxIcon(), Item.FOX, location));
        itemButtons.add(new ItemButton(ImageLib.getGrainIcon(), Item.GRAIN, location));
        this.title = new JLabel(title);
        add(this.title);
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

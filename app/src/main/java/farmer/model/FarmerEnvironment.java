package farmer.model;

import java.util.ArrayList;

public class FarmerEnvironment {

    private ArrayList<Item> eastBankItems;
    private ArrayList<Item> westBankItems;
    private Item itemInBoat;

    public FarmerEnvironment() {
        eastBankItems = new ArrayList<>();
        westBankItems = new ArrayList<>();
        setInitialState();
    }

    public void setInitialState() {
        eastBankItems.clear();
        westBankItems.clear();
        westBankItems.add(Item.CHICKEN);
        westBankItems.add(Item.FOX);
        westBankItems.add(Item.GRAIN);
        itemInBoat = Item.NONE;
    }

    public void setItemInBoat(Item item) {
        itemInBoat = item;
    }

    public Item getItemInBoat() {
        return itemInBoat;
    }

    public ArrayList<Item> getEastBankItems() {
        return eastBankItems;
    }

    public ArrayList<Item> getWestBankItems() {
        return westBankItems;
    }

    public void addItemToEastBank(Item item) {
        eastBankItems.add(item);
    }

    public void addItemToWestBank(Item item) {
        westBankItems.add(item);
    }

    public void removeItemFromEastBank(Item item) {
        eastBankItems.remove(item);
    }

    public void removeItemFromWestBank(Item item) {
        westBankItems.remove(item);
    }

    public boolean hasWon() {
        return westBankItems.isEmpty();
    }

    public boolean hasLost(Location farmerLocation) {
        switch (farmerLocation) {
            case EAST_BANK:
                return hasConflict(westBankItems);
            case WEST_BANK:
                return hasConflict(eastBankItems);
            default:
                return false;
        }
    }

    protected boolean hasConflict(ArrayList<Item> items) {
        return (
            (items.contains(Item.CHICKEN) && items.contains(Item.FOX)) ||
            (items.contains(Item.CHICKEN) && items.contains(Item.GRAIN))
        );
    }
}

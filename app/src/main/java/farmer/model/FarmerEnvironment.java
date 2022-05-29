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

    public boolean allItemsAcross() {
        return 
            eastBankItems.contains(Item.CHICKEN) && 
            eastBankItems.contains(Item.FOX) && 
            eastBankItems.contains(Item.GRAIN);
    }

    public boolean foxAteChicken(Location farmerLocation) {
        switch (farmerLocation) {
            case EAST_BANK:
                return westBankItems.contains(Item.CHICKEN) && westBankItems.contains(Item.FOX);
            case WEST_BANK:
                return eastBankItems.contains(Item.CHICKEN) && eastBankItems.contains(Item.FOX);
            default:
                return false;
        }
    }

    public boolean chickenAteGrain(Location farmerLocation) {
        switch (farmerLocation) {
            case EAST_BANK:
                return westBankItems.contains(Item.CHICKEN) && westBankItems.contains(Item.GRAIN);
            case WEST_BANK:
                return eastBankItems.contains(Item.CHICKEN) && eastBankItems.contains(Item.GRAIN);
            default:
                return false;
        }
    }
}

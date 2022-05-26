package farmer.model;

import java.util.ArrayList;

public class GameModel {

    private Location farmerLocation;
    private Item selectedItem;
    private ArrayList<Item> westRiverBank;
    private ArrayList<Item> eastRiverBank;

    public GameModel() {
        westRiverBank = new ArrayList<>();
        eastRiverBank = new ArrayList<>();
        initGame();
    }

    public void initGame() {
        farmerLocation = Location.WEST_BANK;
        selectedItem = Item.NONE;
        westRiverBank.clear();
        eastRiverBank.clear();
        westRiverBank.add(Item.CHICKEN);
        westRiverBank.add(Item.FOX);
        westRiverBank.add(Item.GRAIN);
    }

    public Location getFarmerLocation() {
        return farmerLocation;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public boolean itemSelected() {
        return selectedItem != Item.NONE;
    }

    public boolean noItemSelected() {
        return selectedItem == Item.NONE;
    }

    public void loadItemIntoBoat(Item item) {
        selectedItem = item;
        switch(farmerLocation) {
            case EAST_BANK:
                eastRiverBank.remove(item);
                break;
            case WEST_BANK:
                westRiverBank.remove(item);
                break;
        }
    }

    public void unloadItemFromBoat() {
        if (selectedItem == Item.NONE) return;
        switch(farmerLocation) {
            case EAST_BANK:
                eastRiverBank.add(selectedItem);
                break;
            case WEST_BANK:
                westRiverBank.add(selectedItem);
                break;
        }
        selectedItem = Item.NONE;
    }

    public void moveFarmerAcrossRiver() {
        switch(farmerLocation) {
            case EAST_BANK:
                farmerLocation = Location.WEST_BANK;
                break;
            case WEST_BANK:
                farmerLocation = Location.EAST_BANK;
        }
    }

    public boolean gameWon() {
        return (
            eastRiverBank.contains(Item.CHICKEN) &&
            eastRiverBank.contains(Item.FOX) &&
            eastRiverBank.contains(Item.GRAIN)
        );
    }

    public boolean gameLost() {
        switch(farmerLocation) {
            case EAST_BANK:
                if (isConflict(westRiverBank)) return true;
            case WEST_BANK:
                if (isConflict(eastRiverBank)) return true;
        }
        return false;
    }

    private boolean isConflict(ArrayList<Item> items) {
        return (
            (items.contains(Item.CHICKEN) && items.contains(Item.FOX)) ||
            (items.contains(Item.CHICKEN) && items.contains(Item.GRAIN))
        );
    }
}

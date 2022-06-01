package farmer.model;

import java.util.Set;
import java.util.EnumSet;

/**
 * <pre>
 * FarmerEnvironment maintains the state of the game.
 * 
 *  - eastBankItems: list of items currently on the east bank of the river.
 *  - westBankItems: list of items currently on the west bank of the river.
 *  - itemInBoat: item currently loaded in the boat.
 * 
 * </pre>
 */
public class FarmerEnvironment {

    /**
     * List of Items on the east bank of the river.
     */
    private Set<Item> eastBankItems;

    /**
     * List of Items on the west bank of the river.
     */
    private Set<Item> westBankItems;

    /**
     * The Item loaded in the boat.
     */
    private Item itemInBoat;

    /**
     * Constructor. Instantiates the list of items for each river bank.
     */
    public FarmerEnvironment() {

        eastBankItems = EnumSet.noneOf(Item.class);
        westBankItems = EnumSet.noneOf(Item.class);
    }

    /**
     * Sets the initial state for the game environment. 
     * All items are on the west bank and itemInBoat is Item.NONE.
     */
    public void setInitialState() {

        eastBankItems.clear();
        westBankItems.clear();

        westBankItems.add(Item.CHICKEN);
        westBankItems.add(Item.FOX);
        westBankItems.add(Item.GRAIN);

        itemInBoat = Item.NONE;
    }

    /**
     * Sets the Item currently loaded in the boat.
     * 
     * @param item Item to load in the boat.
     */
    public void setItemInBoat(Item item) {

        itemInBoat = item;
    }

    /**
     * Gets the Item currently loaded in the boat.
     * 
     * @return Item loaded in the boat.
     */
    public Item getItemInBoat() {

        return itemInBoat;
    }

    /**
     * Gets the list of items on the east bank of the river.
     * 
     * @return List of Items on the east bank.
     */
    public Set<Item> getEastBankItems() {

        return eastBankItems;
    }

    /**
     * Gets the list of items on the west bank of the river.
     * 
     * @return List of Items on the west bank.
     */
    public Set<Item> getWestBankItems() {

        return westBankItems;
    }

    /**
     * Adds an item to the east bank of the river.
     * 
     * @param item Item to add to the east bank.
     */
    public void addItemToEastBank(Item item) {

        eastBankItems.add(item);
    }

    /**
     * Adds an item to the west bank of the river.
     * 
     * @param item Item to add to the west bank.
     */
    public void addItemToWestBank(Item item) {

        westBankItems.add(item);
    }

    /**
     * Removes an item from the east bank of the river.
     * 
     * @param item Item to remove from the east bank.
     */
    public void removeItemFromEastBank(Item item) {

        eastBankItems.remove(item);
    }

    /**
     * Removes an item from the west bank of the river.
     * 
     * @param item Item to remove from the west bank.
     */
    public void removeItemFromWestBank(Item item) {

        westBankItems.remove(item);
    }

    /**
     * Checks if the all items have been moved across the river to the east bank.
     * 
     * @return True if the east bank contains Item.CHICKEN, Item.FOX, and Item.GRAIN.
     */
    public boolean allItemsAcross() {

        return 
            eastBankItems.contains(Item.CHICKEN) && 
            eastBankItems.contains(Item.FOX) && 
            eastBankItems.contains(Item.GRAIN);
    }

    /**
     * Checks if the fox and the chicken are on the opposite river bank from the farmer.
     * 
     * @param farmerLocation Location of the farmer.
     * @return True if the river bank opposite the farmers location contains the chicken and the fox.
     */
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

    /**
     * Checks if the chicken and the grain are on the opposite river bank from the farmer.
     * 
     * @param farmerLocation Location of the farmer.
     * @return True if the river bank opposite the farmers location contains the chicken and the grain.
     */
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

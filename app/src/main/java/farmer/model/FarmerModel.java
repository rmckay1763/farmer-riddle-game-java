package farmer.model;

/**
 * <pre>
 * FarmerModel represents the farmer in the game and defines actions for the farmer.
 * 
 *  - loadItemInBoat
 *  - unloadItemFromBoat
 *  - startCrossingRiver
 *  - doneCrossingRiver
 * </pre>
 */
public class FarmerModel {

    /**
     * The farmers location relative to the river. EAST_BANK or WEST_BANK.
     */
    private Location farmerLocation;

    /**
     * True if the farmer is in the process of crossing the river.
     */
    private boolean crossingRiver;

    /**
     * Sets the inital state for a new game. 
     */
    public void setInitialState() {

        farmerLocation = Location.WEST_BANK;
        crossingRiver = false;
    }

    /**
     * Checks if the farmer is in the process of crossing the river.
     * 
     * @return True if the farmer is crossing the river.
     */
    public boolean isCrossingRiver() {

        return crossingRiver;
    }

    /**
     * Gets the Location of the farmer.
     * @return EAST_BANK or WEST_BANK.
     */
    public Location getFarmerLocation() {

        return farmerLocation;
    }
    
    /**
     * Loads an item into the boat.
     * 
     * @param environment FarmerEnvironment for maintaining state.
     * @param item The item to load in the boat.
     */
    public void loadItemInBoat(FarmerEnvironment environment, Item item) {

        switch (farmerLocation) {

            case EAST_BANK:
            environment.removeItemFromEastBank(item);
            break;

            case WEST_BANK:
            environment.removeItemFromWestBank(item);
            break;
        }

        environment.setItemInBoat(item);
    }

    /**
     * Unloads the item in the boat.
     * Adds the item to the river bank corresponding to the farmers current location.
     * 
     * @param environment FarmerEnvironment maintaining state.
     */
    public void unloadItemFromBoat(FarmerEnvironment environment) {

        Item itemInBoat = environment.getItemInBoat();

        switch (farmerLocation) {

            case EAST_BANK:
            environment.addItemToEastBank(itemInBoat);
            break;

            case WEST_BANK:
            environment.addItemToWestBank(itemInBoat);
            break;
        }

        environment.setItemInBoat(Item.NONE);
    }

    /**
     * Sets the boolean field crossingRiver to true.
     */
    public void startCrossingRiver() {
        crossingRiver = true;
    }

    /**
     * Sets the boolean field crossingRiver to false.
     * Switches the farmers location to the other bank.
     */
    public void doneCrossingRiver() {

        crossingRiver = false;

        switch (farmerLocation) {

            case EAST_BANK:
            farmerLocation = Location.WEST_BANK;
            break;

            case WEST_BANK:
            farmerLocation = Location.EAST_BANK;
            break;
        }
    }
}

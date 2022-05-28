package farmer.model;

public class GameModel {

    public static final String RESTART_WHILE_CROSSING_RIVER_ERROR = 
        "Cannot restart game while farmer is crossing river";

    public static final String SELECT_ITEM_WHILE_CROSSING_RIVER_ERROR =
        "Farmer cannot select item while crossing river";

    public static final String SELECT_ITEM_ON_OPPOSITE_BANK_ERROR = 
        "Farmer cannot select item on opposite bank";

    public static final String SELECT_ITEM_WHEN_BOAT_FULL_ERROR = 
        "Farmer can only have one item in the boat at a time";

    public static final String UNLOAD_ITEM_WHILE_CROSSING_RIVER_ERROR = 
        "Farmer cannot unload item while crossing river";

    public static final String NO_ITEM_SELECTED_ERROR = 
        "Farmer cannot unload item from empty boat";

    public static final String START_CROSSING_RIVER_ERROR =
        "Farmer is in process of crossing river";

    public static final String DONE_CROSSING_RIVER_ERROR = 
        "Farmer is not in process of crossing river";

    private FarmerEnvironment environment;
    private FarmerModel farmer;

    public GameModel() {
        environment = new FarmerEnvironment();
        farmer = new FarmerModel();
    }

    public void initGame() {
        environment.setInitialState();
        farmer.setInitialState();
    }

    public FarmerEnvironment getEnvironment() {
        return environment;
    }

    public FarmerModel getFarmer() {
        return farmer;
    }

    public void loadItemInBoat(Item item, Location itemLocation) throws IllegalActionException {

        if (farmer.isCrossingRiver()) {
            throw new IllegalActionException(SELECT_ITEM_WHILE_CROSSING_RIVER_ERROR);
        }

        if (farmer.getFarmerLocation() != itemLocation) {
            throw new IllegalActionException(SELECT_ITEM_ON_OPPOSITE_BANK_ERROR);
        }

        if (environment.getItemInBoat() != Item.NONE) {
            throw new IllegalActionException(SELECT_ITEM_WHEN_BOAT_FULL_ERROR);
        }

        farmer.loadItemInBoat(environment, item);

    }

    public Item unloadItemFromBoat() throws IllegalActionException {

        if (farmer.isCrossingRiver()) {
            throw new IllegalActionException(UNLOAD_ITEM_WHILE_CROSSING_RIVER_ERROR);
        }

        Item itemInBoat = environment.getItemInBoat();
        if (itemInBoat == Item.NONE) {
            throw new IllegalActionException(NO_ITEM_SELECTED_ERROR);
        }

        farmer.unloadItemFromBoat(environment);
        return itemInBoat;
    }

    public void startCrossingRiver() {

        if (farmer.isCrossingRiver()) {
            throw new IllegalActionException(START_CROSSING_RIVER_ERROR);
        }

        farmer.startCrossingRiver();
    }

    public void doneCrossingRiver() {

        if (!farmer.isCrossingRiver()) {
            throw new IllegalActionException(DONE_CROSSING_RIVER_ERROR);
        }
        
        farmer.doneCrossingRiver();
    }
}

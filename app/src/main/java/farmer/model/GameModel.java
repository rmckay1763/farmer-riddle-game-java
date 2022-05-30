package farmer.model;

public class GameModel {

    public static final String CROSSING_RIVER_ERROR = "Action not allowed while crossing river";
    public static final String OPPOSITE_BANK_ERROR = "Farmer cannot select item on opposite bank";
    public static final String BOAT_FULL_ERROR = "Farmer can only have one item at a time in boat";
    public static final String BOAT_EMPTY_ERROR = "Farmer cannot unload item from empty boat";
    public static final String GAME_OVER_ERROR = "Game over. Restart to play again.";

    public static final String FOX_ATE_CHICKEN_MESSAGE = "The fox ate the chicken! Game Over.";
    public static final String CHICKEN_ATE_GRAIN_MESSAGE = "The chicken ate the grain! Game Over";
    public static final String GAME_WON_MESSAGE = "The farmer got all items across. You Win!";
    public static final String ITEM_LOADED_MESSAGE = "%s loaded into boat";
    public static final String ITEM_UNLOADED_MESSAGE = "%s unloaded from boat";
    public static final String START_CROSSING_MESSAGE = "Farmer leaving %s of river";
    public static final String DONE_CROSSING_MESSAGE = "Farmer on %s of river";

    private FarmerEnvironment environment;
    private FarmerModel farmer;
    private boolean gameWon;
    private boolean foxAteChicken;
    private boolean chickenAteGrain;
    private String gameStatus;

    public GameModel() {
        environment = new FarmerEnvironment();
        farmer = new FarmerModel();
        setInitialState();
    }

    public void setInitialState() {
        environment.setInitialState();
        farmer.setInitialState();
        gameWon = false;
        foxAteChicken = false;
        chickenAteGrain = false;
    }

    public FarmerEnvironment getEnvironment() {
        return environment;
    }

    public FarmerModel getFarmer() {
        return farmer;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public boolean isGameOver() {
        return gameWon || foxAteChicken || chickenAteGrain;
    }

    public void loadItemInBoat(Item item, Location itemLocation) throws IllegalActionException {

        throwErrorIfGameOver();
        throwErrorIfCrossingRiver();
        throwErrorIfItemOnOppositeBank(itemLocation);
        throwErrorIfBoatFull();

        farmer.loadItemInBoat(environment, item);
        gameStatus = String.format(ITEM_LOADED_MESSAGE, item);

    }

    public Item unloadItemFromBoat() throws IllegalActionException {

        
        throwErrorIfGameOver();
        throwErrorIfCrossingRiver();
        throwErrorIfBoatEmpty();

        Item item = environment.getItemInBoat();
        farmer.unloadItemFromBoat(environment);
        gameWon = environment.allItemsAcross();

        if (gameWon) {
            gameStatus = GAME_WON_MESSAGE;

        } else {
            gameStatus = String.format(ITEM_UNLOADED_MESSAGE, item);
        }

        return item;
    }

    public void startCrossingRiver() throws IllegalActionException {

        throwErrorIfGameOver();
        throwErrorIfCrossingRiver();

        farmer.startCrossingRiver();
        gameStatus = String.format(START_CROSSING_MESSAGE, farmer.getFarmerLocation());
    }

    public void doneCrossingRiver() throws IllegalActionException {

        throwErrorIfGameOver();
        throwErrorIfNotCrossingRiver();

        farmer.doneCrossingRiver();
        foxAteChicken = environment.foxAteChicken(farmer.getFarmerLocation());
        chickenAteGrain = environment.chickenAteGrain(farmer.getFarmerLocation());

        if (foxAteChicken) {
            gameStatus = FOX_ATE_CHICKEN_MESSAGE;

        } else if (chickenAteGrain) {
            gameStatus = CHICKEN_ATE_GRAIN_MESSAGE;

        } else {
            gameStatus = String.format(DONE_CROSSING_MESSAGE, farmer.getFarmerLocation());
        }
    }

    private void throwErrorIfGameOver() throws IllegalActionException {
        if (gameWon || foxAteChicken || chickenAteGrain) {
            throw new IllegalActionException(GAME_OVER_ERROR);
        }
    }

    private void throwErrorIfCrossingRiver() throws IllegalActionException {
        if (farmer.isCrossingRiver()) {
            throw new IllegalActionException(CROSSING_RIVER_ERROR);
        }
    }

    private void throwErrorIfNotCrossingRiver() throws IllegalActionException {
        if (!farmer.isCrossingRiver()) {
            throw new IllegalActionException();
        }
    }

    private void throwErrorIfItemOnOppositeBank(Location itemLocation) throws IllegalActionException {
        if (farmer.getFarmerLocation() != itemLocation) {
            throw new IllegalActionException(OPPOSITE_BANK_ERROR);
        }
    }

    private void throwErrorIfBoatFull() throws IllegalActionException {
        if (environment.getItemInBoat() != Item.NONE) {
            throw new IllegalActionException(BOAT_FULL_ERROR);
        }
    }

    private void throwErrorIfBoatEmpty() throws IllegalActionException {
        if (environment.getItemInBoat() == Item.NONE) {
            throw new IllegalActionException(BOAT_EMPTY_ERROR);
        }
    }
    
}

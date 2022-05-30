package farmer.model;

/**
 * GameModel implements the logic for the rules of the game.
 * Main model for the controller to interact with. 
 * Handles validating actions depending on current state. 
 * Updates a status message after each action.
 * 
 */
public class GameModel {

    // error description messages
    public static final String CROSSING_RIVER_ERROR;
    public static final String OPPOSITE_BANK_ERROR;
    public static final String BOAT_FULL_ERROR;
    public static final String BOAT_EMPTY_ERROR;
    public static final String GAME_OVER_ERROR;

    // status messages for beginning of game and win/loss cases.
    public static final String DEFAULT_MESSAGE;
    public static final String FOX_ATE_CHICKEN_MESSAGE;
    public static final String CHICKEN_ATE_GRAIN_MESSAGE;
    public static final String GAME_WON_MESSAGE;

    // status messages with updated state after performing an action.
    public static final String ITEM_LOADED_MESSAGE;
    public static final String ITEM_UNLOADED_MESSAGE;
    public static final String START_CROSSING_MESSAGE;
    public static final String DONE_CROSSING_MESSAGE;

    static {
        CROSSING_RIVER_ERROR = "Action not allowed while crossing river";
        OPPOSITE_BANK_ERROR = "Farmer cannot select item on opposite bank";
        BOAT_FULL_ERROR = "Farmer can only have one item at a time in boat";
        BOAT_EMPTY_ERROR = "Farmer cannot unload item from empty boat";
        GAME_OVER_ERROR = "Game over. Restart to play again.";

        DEFAULT_MESSAGE = "Help the farmer by selecting an item to load in the boat";
        FOX_ATE_CHICKEN_MESSAGE = "The fox ate the chicken! Game Over.";
        CHICKEN_ATE_GRAIN_MESSAGE = "The chicken ate the grain! Game Over";
        GAME_WON_MESSAGE = "The farmer got all items across. You Win!";

        ITEM_LOADED_MESSAGE = "%s loaded into boat";
        ITEM_UNLOADED_MESSAGE = "%s unloaded from boat";
        START_CROSSING_MESSAGE = "Farmer leaving %s of river";
        DONE_CROSSING_MESSAGE = "Farmer on %s of river";
    }

    /**
     * FarmerEnvironment for maintaining items on each bank on item in boat.
     */
    private FarmerEnvironment environment;

    /**
     * FarmerModel for acting on the environment to update state.
     */
    private FarmerModel farmer;

    /**
     * True if the game has reach a winning state.
     */
    private boolean gameWon;

    /**
     * True if the fox ate the chicken.
     */
    private boolean foxAteChicken;

    /**
     * True if the chicken ate the grain.
     */
    private boolean chickenAteGrain;

    /**
     * Status message to display in the view after each action.
     */
    private String gameStatus;

    /**
     * Constructor. Instantiates the FarmerEnvironment and the FarmerModel.
     */
    public GameModel() {

        environment = new FarmerEnvironment();
        farmer = new FarmerModel();
    }

    /**
     * Sets the initial state for a new game.
     */
    public void setInitialState() {

        environment.setInitialState();
        farmer.setInitialState();
        gameWon = false;
        foxAteChicken = false;
        chickenAteGrain = false;
    }

    /**
     * Gets the FarmerEnvironment.
     * @return The FarmerEnvrionment.
     */
    public FarmerEnvironment getEnvironment() {

        return environment;
    }

    /**
     * Gets the FarmerModel.
     * @return The FarmerModel.
     */
    public FarmerModel getFarmer() {

        return farmer;
    }

    /**
     * Gets the status message for the current state of the game.
     * 
     * @return Description of the updated state after performing an action.
     */
    public String getGameStatus() {

        return gameStatus;
    }

    /**
     * Loads the specified Item into the boat.
     * Updates the status message with the result of the action.
     * 
     * @param item The Item to load in the boat.
     * @param itemLocation Location of the item to load.
     * @throws IllegalActionException If the current state does not allow loading the item in the boat.
     */
    public void loadItemInBoat(Item item, Location itemLocation) throws IllegalActionException {

        throwErrorIfGameOver();
        throwErrorIfCrossingRiver();
        throwErrorIfItemOnOppositeBank(itemLocation);
        throwErrorIfBoatFull();

        farmer.loadItemInBoat(environment, item);
        gameStatus = String.format(ITEM_LOADED_MESSAGE, item);

    }

    /**
     * Unloads the Item in the boat.
     * Adds the item to the river bank corresponding to the farmers current location.
     * Checks if the farmer has gotten all items across to the east bank.
     * Updates the status message with the result of the action.
     * 
     * @return The item unloaded from the boat.
     * @throws IllegalActionException If the current state does not allow unloading the item in the boat.
     */
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

    /**
     * Calls farmer.startCrossingRiver().
     * Updates the status message with the result of the action.
     * 
     * @throws IllegalActionException If the current state does not allow the farmer to start crossing the river.
     */
    public void startCrossingRiver() throws IllegalActionException {

        throwErrorIfGameOver();
        throwErrorIfCrossingRiver();

        farmer.startCrossingRiver();
        gameStatus = String.format(START_CROSSING_MESSAGE, farmer.getFarmerLocation());
    }

    /**
     * Calls farmer.doneCrossingRiver().
     * Checks if the fox ate the chicken or the chicken ate the grain.
     * Updates the status message with the result of the action.
     * 
     * @throws IllegalActionException If the current state does not allow the action.
     */
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

    /**
     * Throws error to indicate an action is not allowed if the game is over.
     * 
     * @throws IllegalActionException If the game has reached a terminal state.
     */
    private void throwErrorIfGameOver() throws IllegalActionException {

        if (gameWon || foxAteChicken || chickenAteGrain) {
            throw new IllegalActionException(GAME_OVER_ERROR);
        }
    }

    /**
     * Throws an error to indicate an action is not allowed while the farmer is crossing the river.
     * 
     * @throws IllegalActionException If the farmer is in the process of crossing the river.
     */
    private void throwErrorIfCrossingRiver() throws IllegalActionException {

        if (farmer.isCrossingRiver()) {
            throw new IllegalActionException(CROSSING_RIVER_ERROR);
        }
    }

    /**
     * Throws an error to indicate an action is not allowed while the farmer is not crossing the river.
     * 
     * @throws IllegalActionException If the farmer is not crossing the river.
     */
    private void throwErrorIfNotCrossingRiver() throws IllegalActionException {

        if (!farmer.isCrossingRiver()) {
            throw new IllegalActionException();
        }
    }

    /**
     * Throws an error to indicate the selected item is not on the same river bank as the farmer.
     * 
     * @param itemLocation Location of the selected item.
     * @throws IllegalActionException If the selected item location and farmers location differ.
     */
    private void throwErrorIfItemOnOppositeBank(Location itemLocation) throws IllegalActionException {

        if (farmer.getFarmerLocation() != itemLocation) {
            throw new IllegalActionException(OPPOSITE_BANK_ERROR);
        }
    }

    /**
     * Throws an error to indicate the farmer can only have one item in the boat at a time.
     * 
     * @throws IllegalActionException If the item in the boat is anything other than Item.NONE.
     */
    private void throwErrorIfBoatFull() throws IllegalActionException {

        if (environment.getItemInBoat() != Item.NONE) {
            throw new IllegalActionException(BOAT_FULL_ERROR);
        }
    }

    /**
     * throws and error to indicate the farmer cannot unload an item from an empty boat.
     * 
     * @throws IllegalActionException If the item in the boat is Item.NONE.
     */
    private void throwErrorIfBoatEmpty() throws IllegalActionException {

        if (environment.getItemInBoat() == Item.NONE) {
            throw new IllegalActionException(BOAT_EMPTY_ERROR);
        }
    }
    
}

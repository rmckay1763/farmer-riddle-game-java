package farmer.controller;

import java.awt.event.ActionEvent;
import farmer.model.GameModel;
import farmer.model.IllegalActionException;
import farmer.model.Item;
import farmer.model.Location;
import farmer.view.GameView;
import farmer.view.ItemButton;

/**
 * Listens for events from the GameView. 
 * 
 *  - onCrossRiver is called when the user clicks the cross river button.
 *  - onItemSelected is called when the user clicks an item in the view. 
 *  - onUnloadItemFromBoat is called when the user clicks the unload item button.
 *  - onResartGame is called when the user clicks the restart game button.
 * 
 * Each listener calls methods in GameModel to process the event and update the view. 
 * GameModel updates state and a status message to send back to the view.
 * 
 */
public class GameController {
    
    private GameModel model;
    private GameView view;
    private CrossRiverAnimator crossRiverAnimator;

    /**
     * Constructor.
     * @param model The model for the game.
     * @param view The user interface for the game.
     */
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        crossRiverAnimator = new CrossRiverAnimator(view, this);
    }

    /**
     * Registers listeners for each action element in the view.
     */
    public void addListeners() {
        view.addOnCrossRiverListener(event -> onCrossRiver());
        view.addOnItemSelectedListener(event -> onItemSelected(event));
        view.addOnUnloadItemListener(event -> onUnloadItemFromBoat());
        view.addOnRestartGameListener(event -> onRestartGame());
    }

    /**
     * Determines which item was selected in the view and notifies the model.
     * Updates the boat image in the view with the selected item.
     * 
     * @param event ActionEvent with the source of the event.
     */
    private void onItemSelected(ActionEvent event) {

        ItemButton button = (ItemButton)event.getSource();
        Item item = button.getItem();
        Location itemLocation = button.getItemLocation();

        try {
            model.loadItemInBoat(item, itemLocation);
            view.hideItem(item, itemLocation);
            view.setBoatImage(item);
            view.setStatusMessage(model.getGameStatus());

        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

    /**
     * Calls unloadItemFromBoat in the model to update state and retrieve the item.
     * Notifies the view to show the item and updates the boat image. 
     */
    public void onUnloadItemFromBoat() {

        try {
            Item item = model.unloadItemFromBoat();
            view.showItem(item, model.getFarmer().getFarmerLocation());
            view.setBoatImage(Item.NONE);
            view.setStatusMessage(model.getGameStatus());

        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

    /**
     * Calls startCrossingRiver in the model to update state.
     * Starts the animation for the boat moving across the river.
     */
    private void onCrossRiver() {
        try {
            model.startCrossingRiver();
            crossRiverAnimator.start();
            view.setStatusMessage(model.getGameStatus());
        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

    /**
     * Stops the animation of the boat crossing the river. 
     * Notifies the model to update state.
     */
    public void onCrossRiverDone() {
        try {
            crossRiverAnimator.stop();
            model.doneCrossingRiver();
            view.setStatusMessage(model.getGameStatus());
        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

    /**
     * Resets the model and the view to inital state.
     */
    public void onRestartGame() {
        crossRiverAnimator.stop();
        crossRiverAnimator.setBoatOffset(0);
        model.initGame();
        view.setBoatImage(Item.NONE);
        view.moveBoat(0);
        view.resetItems();
        view.setStatusMessage(GameView.DEFAULT_MESSAGE);
        
    }
}

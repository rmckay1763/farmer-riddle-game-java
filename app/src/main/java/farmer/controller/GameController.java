package farmer.controller;

import java.awt.event.ActionEvent;
import farmer.model.GameModel;
import farmer.model.IllegalActionException;
import farmer.model.Item;
import farmer.model.Location;
import farmer.view.GameView;
import farmer.view.HelpWindow;
import farmer.view.ItemButton;
import farmer.view.RiverView;

/**
 * <pre>
 * Listens for events from the GameView. 
 * 
 *  - onCrossRiver is called when the user clicks the cross river button.
 *  - onItemSelected is called when the user clicks an item in the view. 
 *  - onUnloadItemFromBoat is called when the user clicks the unload item button.
 *  - onResartGame is called when the user clicks the restart game button.
 * 
 * Each listener calls methods in GameModel to process the event and update the view. 
 * GameModel updates state and a status message to send back to the view.
 * </pre>
 */
public class GameController {
    
    /**
     * GameModel for processing actions.
     */
    private GameModel model;

    /**
     * GameView for displaying state and generating events.
     */
    private GameView view;

    private HelpWindow helpWindow;

    /**
     * CrossRiverAnimator for the boat crossing the river animation.
     */
    private CrossRiverAnimator crossRiverAnimator;

    /**
     * Constructor.
     * @param model The model for the game.
     * @param view The user interface for the game.
     */
    public GameController(GameModel model, GameView view) {

        this.model = model;
        this.view = view;
        helpWindow = new HelpWindow();
        crossRiverAnimator = new CrossRiverAnimator(view, this);
        addListeners();
    }

    /**
     * Registers listeners for each action element in the view.
     */
    private void addListeners() {

        view.addCrossRiverListener(event -> onCrossRiver());
        view.addItemSelectedListener(event -> onItemSelected(event));
        view.addUnloadBoatListener(event -> onUnloadItemFromBoat());
        view.addRestartListener(event -> onRestartGame());
        view.addHelpListener(event -> onHelpSelected());
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
        crossRiverAnimator.setBoatOffset(RiverView.BOAT_START_OFFSET);
        model.setInitialState();
        view.setInitialState();
        view.setStatusMessage(GameModel.DEFAULT_MESSAGE);  
    }

    /**
     * Displays the help window.
     */
    public void onHelpSelected() {

        if (helpWindow.isVisible()) {
            helpWindow.setVisible(false);

        } else {
            helpWindow.setVisible(true);
        }
        
    }
}

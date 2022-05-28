package farmer.controller;

import java.awt.event.ActionEvent;
import farmer.model.GameModel;
import farmer.model.IllegalActionException;
import farmer.model.Item;
import farmer.model.Location;
import farmer.view.GameView;
import farmer.view.ItemButton;

public class GameController {

    public static final String ITEM_LOADED_MESSAGE = "%s loaded into boat";
    public static final String ITEM_UNLOADED_MESSAGE = "%s unloaded from boat";
    public static final String START_CROSSING_MESSAGE = "Farmer leaving %s of river";
    public static final String DONE_CROSSING_MESSAGE = "Farmer on %s of river";
    public static final String GAME_WON_MESSAGE = "You win!";
    public static final String GAME_LOST_MESSAGE = "You lost";
    
    private GameModel model;
    private GameView view;
    private CrossRiverAnimator crossRiverAnimator;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        crossRiverAnimator = new CrossRiverAnimator(view, this);
    }

    public void addListeners() {
        view.addOnCrossRiverListener(event -> onCrossRiver());
        view.addOnItemSelectedListener(event -> onItemSelected(event));
        view.addOnUnloadItemListener(event -> onUnloadItemFromBoat());
        view.addOnRestartGameListener(event -> onRestartGame());
    }

    private void onItemSelected(ActionEvent event) {

        ItemButton button = (ItemButton)event.getSource();
        Item item = button.getItem();
        Location itemLocation = button.getItemLocation();
        try {
            model.loadItemInBoat(item, itemLocation);
            view.hideItem(item, itemLocation);
            view.setBoatImage(item);
            view.setStatusMessage(String.format(ITEM_LOADED_MESSAGE, item));
        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

    public void onUnloadItemFromBoat() {

        try {
            Item item = model.unloadItemFromBoat();
            view.showItem(item, model.getFarmer().getFarmerLocation());
            view.setBoatImage(Item.NONE);
            if (model.getEnvironment().hasWon()) {
                view.setStatusMessage(GAME_WON_MESSAGE);;
            } else {
                view.setStatusMessage(String.format(ITEM_UNLOADED_MESSAGE, item));
            }
        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

    private void onCrossRiver() {
        try {
            model.startCrossingRiver();
            crossRiverAnimator.start();
            Location location = model.getFarmer().getFarmerLocation();
            view.setStatusMessage(String.format(START_CROSSING_MESSAGE, location));
        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

    public void onCrossRiverDone() {
        try {
            crossRiverAnimator.stop();
            model.doneCrossingRiver();
            Location location = model.getFarmer().getFarmerLocation();
            if (model.getEnvironment().hasLost(location)) {
                view.setStatusMessage(GAME_LOST_MESSAGE);
            } else {
                view.setStatusMessage(String.format(DONE_CROSSING_MESSAGE, location));
            }
        } catch (IllegalActionException error) {
            view.setStatusMessage(error.getMessage());
        }
    }

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

package farmer.controller;

import java.awt.event.ActionEvent;
import farmer.model.GameModel;
import farmer.model.Item;
import farmer.model.Location;
import farmer.view.GameView;
import farmer.view.ItemButton;

public class GameController {
    
    private GameModel model;
    private GameView view;
    private OnCrossRiverListener onCrossRiverListener;
    private boolean crossingRiver;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        onCrossRiverListener = new OnCrossRiverListener(view, this);
        crossingRiver = false;
    }

    public void addListeners() {
        view.addOnCrossRiverListener(event -> onCrossRiver());
        view.addOnItemSelectedListener(event -> onItemSelected(event));
        view.addOnUnloadItemListener(event -> onUnloadItemFromBoat());
    }

    private void onItemSelected(ActionEvent event) {
        if (model.itemSelected() || crossingRiver) return;
        ItemButton button = (ItemButton)event.getSource();
        Location itemLocation = button.getItemLocation();
        Location farmerLocation = model.getFarmerLocation();
        if (itemLocation != farmerLocation) return;
        Item item = button.getItem();
        view.hideItem(item, itemLocation);
        view.setBoatImage(item);
        view.setUnloadItemEnabled(true);
        model.loadItemIntoBoat(item);
    }

    private void onCrossRiver() {
        crossingRiver = true;
        view.disableAllActions();
        onCrossRiverListener.crossRiver();
    }

    public void onCrossRiverDone() {
        model.moveFarmerAcrossRiver();
        view.setCrossRiverEnabled(true);
        if (model.itemSelected()) {
            view.setUnloadItemEnabled(true);
        }
        crossingRiver = false;
        checkGameStatus();
    }

    public void onUnloadItemFromBoat() {
        Item item = model.getSelectedItem();
        Location location = model.getFarmerLocation();
        view.showItem(item, location);
        view.setBoatImage(Item.NONE);
        view.setUnloadItemEnabled(false);
        model.unloadItemFromBoat(); 
        checkGameStatus();
    }

    private void checkGameStatus() {
        if (model.gameLost()) {
            view.setGameStatusMessage(GameView.GAME_LOST_MESSAGE);
        } else if (model.gameWon()) {
            view.setGameStatusMessage(GameView.GAME_WON_MESSAGE);
        } else {
            view.setGameStatusMessage(GameView.DEFAULT_MESSAGE);
        }
    }

}

package farmer.controller;

import farmer.model.Model;
import farmer.model.ImageLib.Item;
import farmer.view.GameView;

public class GameController {
    
    private Model model;
    private GameView gameView;
    private CrossRiverAdapter riverAdapter;

    public GameController(Model model, GameView gameView) {
        this.model = model;
        this.gameView = gameView;
        riverAdapter = new CrossRiverAdapter(gameView.getRiver(), this);

    }

    public void addItems() {
        for (Item item : model.getWestRiverBank()) {
            gameView.addItemOnWestRiverBank(item.getImageIcon());
        }
        for (Item item : model.getEastRiverBank()) {
            gameView.addItemOnEastRiverBank(item.getImageIcon());
        }
    }

    public void onCrossRiverDone() {
        gameView.enableActions(true);
    }

    public void addListeners() {
        gameView.addCrossRiverButtonListener(e -> onCrossRiver());
    }

    private void onCrossRiver() {
        gameView.enableActions(false);
        riverAdapter.crossRiver();
    }

}

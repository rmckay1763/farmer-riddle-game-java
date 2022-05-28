package farmer.controller;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import farmer.view.GameView;
import farmer.view.RiverView;

public class CrossRiverAnimator {

    private GameView view;
    private GameController controller;
    private int boatOffset;
    private Timer fowardTimer;
    private Timer backwardTimer;

    public CrossRiverAnimator(GameView view, GameController controller) {
        this.view = view;
        this.controller = controller;
        boatOffset = RiverView.BOAT_START_OFFSET;
        fowardTimer = new Timer(RiverView.ANIMATION_DELAY, event -> moveBoatFoward(event));
        backwardTimer = new Timer(RiverView.ANIMATION_DELAY, event -> moveBoatBackward(event));
    }

    public void setBoatOffset(int boatOffset) {
        this.boatOffset = boatOffset;
    }

    public void start() {
        if (boatOffset == RiverView.BOAT_START_OFFSET) {
            fowardTimer.start();
        } else if (boatOffset == RiverView.BOAT_END_OFFSET) {
            backwardTimer.start();
        } else {
            return;
        }
    }

    public void stop() {
        if (fowardTimer.isRunning()) {
            fowardTimer.stop();
        }
        if (backwardTimer.isRunning()) {
            backwardTimer.stop();
        }
    }
    
    private void moveBoatFoward(ActionEvent event) {
        view.moveBoat(++boatOffset);
        if (boatOffset == RiverView.BOAT_END_OFFSET) {
            controller.onCrossRiverDone();
        }
    }

    private void moveBoatBackward(ActionEvent event) {
        view.moveBoat(--boatOffset);
        if (boatOffset == RiverView.BOAT_START_OFFSET) {
            controller.onCrossRiverDone();
        }
    }
}

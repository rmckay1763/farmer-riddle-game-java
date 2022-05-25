package farmer.controller;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import farmer.view.RiverView;

public class CrossRiverAdapter {

    private RiverView riverView;
    private GameController controller;
    private int boatOffset;
    private Timer fowardTimer;
    private Timer backwardTimer;

    public CrossRiverAdapter(RiverView riverView, GameController controller) {
        this.riverView = riverView;
        this.controller = controller;
        boatOffset = RiverView.BOAT_START_OFFSET;
        fowardTimer = new Timer(RiverView.ANIMATION_DELAY, event -> moveBoatFoward(event));
        backwardTimer = new Timer(RiverView.ANIMATION_DELAY, event -> moveBoatBackward(event));
    }

    public void crossRiver() {
        if (boatOffset == RiverView.BOAT_START_OFFSET) {
            fowardTimer.start();
        } else if (boatOffset == RiverView.BOAT_END_OFFSET) {
            backwardTimer.start();
        } else {
            return;
        }
    }
    
    private void moveBoatFoward(ActionEvent event) {
        riverView.setBoatOrigin(++boatOffset);
        riverView.repaint();
        if (boatOffset == RiverView.BOAT_END_OFFSET) {
            fowardTimer.stop();
            controller.onCrossRiverDone();
        }
    }

    private void moveBoatBackward(ActionEvent event) {
        riverView.setBoatOrigin(--boatOffset);
        riverView.repaint();
        if (boatOffset == RiverView.BOAT_START_OFFSET) {
            backwardTimer.stop();
            controller.onCrossRiverDone();
        }
    }
}

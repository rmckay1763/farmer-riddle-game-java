package farmer.controller;

import javax.swing.Timer;
import farmer.view.GameView;
import farmer.view.RiverView;

/**
 * <pre>
 * Implements animating the boat to cross the river in the view.
 * 
 *  - boatOffset keeps track of the horizontal offset from the west bank of the river.
 *  - fowardTimer fires an event every 50 ms to repaint the boat one step foward.
 *  - backwardTimer fires an evnet every 50 ms to repaint the boat one step backward.
 * </pre>
 */
public class CrossRiverAnimator {

    /**
     * Reference to the main view for moving the boat origin.
     */
    private GameView view;

    /**
     * Reference to the controller for callback when the animation finishes.
     */
    private GameController controller;

    /**
     * The step offset of the boats x origin from the west river bank.
     */
    private int boatOffset;

    /**
     * Fires an event every RiverView.ANIMATION_DELAY ms to move the boat foward one step.
     */
    private Timer fowardTimer;

    /**
     * Fires an event every RiverView.ANIMATION_DEALY ms to move the baot backward one step.
     */
    private Timer backwardTimer;

    /**
     * Constructor.
     * 
     * @param view The user interface for the game.
     * @param controller The controller for the view and the model.
     */
    public CrossRiverAnimator(GameView view, GameController controller) {

        this.view = view;
        this.controller = controller;
        boatOffset = RiverView.BOAT_START_OFFSET;
        fowardTimer = new Timer(RiverView.ANIMATION_DELAY, event -> moveBoatFoward());
        backwardTimer = new Timer(RiverView.ANIMATION_DELAY, event -> moveBoatBackward());
    }

    /**
     * Setter for the boatOffset.
     * 
     * @param boatOffset The boats horizontal offset from the west bank of the river image.
     */
    public void setBoatOffset(int boatOffset) {

        this.boatOffset = boatOffset;
    }

    /**
     * Getter for the boat offset.
     * 
     * @return The boats horizontal offset from the west bank of the river image.
     */
    public int getBoatOffset() {

        return boatOffset;
    }

    /**
     * Starts either the foward timer or backard timer depending on the boatOffset.
     */
    public void start() {

        if (boatOffset == RiverView.BOAT_START_OFFSET) {
            fowardTimer.start();

        } else if (boatOffset == RiverView.BOAT_END_OFFSET) {
            backwardTimer.start();

        } else {
            return;
        }
    }

    /**
     * Stops any active timers.
     */
    public void stop() {

        if (fowardTimer.isRunning()) {
            fowardTimer.stop();
        }

        if (backwardTimer.isRunning()) {
            backwardTimer.stop();
        }
    }
    
    /**
     * Moves the boat foward one step accros the river.
     */
    private void moveBoatFoward() {

        view.moveBoat(++boatOffset);

        if (boatOffset == RiverView.BOAT_END_OFFSET) {
            controller.onCrossRiverDone();
        }
    }

    /**
     * Moves the boat backward one step accros the river.
     */
    private void moveBoatBackward() {

        view.moveBoat(--boatOffset);
        
        if (boatOffset == RiverView.BOAT_START_OFFSET) {
            controller.onCrossRiverDone();
        }
    }
}

package farmer.view;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Panel in the view with control buttons for performing game actions.
 */
public class ControlPanel extends JPanel {

    private static final Color BLONDE;

    static {
        BLONDE = new Color(246, 236, 213);
    }

    /**
     * Button for the action to unload an item from the boat.
     */
    private ControlButton unloadBoatButton;

    /**
     * Button for the action to cross the river.
     */
    private ControlButton crossRiverButton;

    /**
     * Button for the action to restart the game.
     */
    private ControlButton restartButton;

    /**
     * Button for the action to display the help window.
     */
    private ControlButton helpButton;

    /**
     * Constructor. Initializes and adds the control buttons to the panel.
     */
    public ControlPanel() {

        super();
        setBackground(BLONDE);
        setBorder(new EmptyBorder(10, 100, 10, 100));
        GridLayout layout = new GridLayout(1, 4);
        layout.setHgap(10);
        setLayout(layout);

        unloadBoatButton = new ControlButton("Unload Boat");
        unloadBoatButton.setDefaultIcon(ImageLib.UNLOAD_ICON);
        unloadBoatButton.setHoverIcon(ImageLib.UNLOAD_ICON_HOVER);

        crossRiverButton = new ControlButton("Cross River");
        crossRiverButton.setDefaultIcon(ImageLib.CROSS_ICON);
        crossRiverButton.setHoverIcon(ImageLib.CROSS_ICON_HOVER);

        restartButton = new ControlButton("Restart Game");
        restartButton.setDefaultIcon(ImageLib.RESTART_ICON);
        restartButton.setHoverIcon(ImageLib.RESTART_ICON_HOVER);

        helpButton = new ControlButton("Instructions");
        helpButton.setDefaultIcon(ImageLib.HELP_ICON);
        helpButton.setHoverIcon(ImageLib.HELP_ICON_HOVER);

        this.add(unloadBoatButton);
        this.add(crossRiverButton);
        this.add(restartButton);
        this.add(helpButton);
    }

    /**
     * Adds a listener to the unload boat button.
     * 
     * @param listener ActionListener for processing events from the unload boat button.
     */
    public void addUnloadBoatListener(ActionListener listener) {

        unloadBoatButton.addActionListener(listener);
    }

    /**
     * Adds a listener to the cross river button.
     * 
     * @param listener ActionListener for processing events from the cross river button.
     */
    public void addCrossRiverListener(ActionListener listener) {

        crossRiverButton.addActionListener(listener);
    }

    /**
     * Adds a listener to the restart button.
     * 
     * @param listener ActionListener for processing events from the restart button.
     */
    public void addRestartListener(ActionListener listener) {

        restartButton.addActionListener(listener);
    }

    /**
     * Adds a listener to the help button.
     * 
     * @param listener ActionListener for processing events from the help button.
     */
    public void addHelpListener(ActionListener listener) {

        helpButton.addActionListener(listener);
    }
    
}

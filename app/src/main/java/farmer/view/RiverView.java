package farmer.view;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * The view component for the river and the boat. 
 */
public class RiverView extends JPanel {
    
    public static final int BOAT_START_OFFSET = 0;
    public static final int BOAT_END_OFFSET = 100;
    public static final int ANIMATION_DELAY = 20;

    /**
     * The image to use for painting the river.
     */
    private BufferedImage riverImage;

    /**
     * The image to use for painting the boat.
     */
    private BufferedImage boatImage;

    /**
     * X coordinate of origin for painting the boat.
     * This value is changed to animate the boat crossing the river.
     */
    private int boatXOrigin;

    /**
     * Y coordinate of the origin for painting the boat.
     */
    private int boatYOrigin;

    /**
     * Constructs the River with the given image and a Boat.DEFAULT object 
     * 
     * @param riverImage BufferedImage representing the river.
     */
    public RiverView(BufferedImage riverImage) {

        super();
        setOpaque(false);
        this.riverImage = riverImage;
        boatImage = ImageLib.BOAT_DEFAULT;

    }

    /**
     * Sets the image to use for painting the boat.
     * 
     * @param boat The image for painting the boat.
     */
    public void setBoat(BufferedImage boatImage) {

        this.boatImage = boatImage;
    }

    /**
     * Compute the origin for painting the boat on top of the river.
     * BOAT_START_OFFSET paints the boat on the west river bank.
     * BOAT_END_OFFSET paints the boat on the east river bank.
     * 
     * @param offset The offset from the west bank of the river.
     * @throws IllegalArgumentException If offset is less then BOAT_START_OFFSET or greater than BOAT_END_OFFSET.
     */
    public void setBoatOrigin(int offset) throws IllegalArgumentException {

        if (offset < BOAT_START_OFFSET || offset > BOAT_END_OFFSET) {
            throw new IllegalArgumentException("offset out of range");
        }

        int riverHeight = riverImage.getHeight();
        int riverWidth = riverImage.getWidth();
        int boatHeight = boatImage.getHeight();
        int boatWidth = boatImage.getWidth();

        int stepDistance = (riverWidth - boatWidth) / BOAT_END_OFFSET;
        boatXOrigin = stepDistance * offset;
        boatYOrigin = (riverHeight / 2) - (boatHeight / 2);
    }
        
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.drawImage(riverImage, 0, 0, this);
        graphics.translate(boatXOrigin, boatYOrigin);
        graphics.drawImage(boatImage, 0, 0, this);
        graphics.dispose();
    }

    @Override
    public Dimension getPreferredSize() {

        if (riverImage == null) {
            return super.getPreferredSize();
        }

        return new Dimension(riverImage.getWidth(), riverImage.getHeight());
    }
}

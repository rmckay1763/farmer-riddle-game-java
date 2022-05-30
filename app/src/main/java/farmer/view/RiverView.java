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
    public static final int BOAT_END_OFFSET = 50;
    public static final int ANIMATION_DELAY = 50;
    private BufferedImage riverImage;
    private BufferedImage boatImage;
    private int boatXOrigin;
    private int boatYOrigin;
    private int boatOffset;

    /**
     * Constructs the River with the given image and a Boat.DEFAULT object 
     * Initializes Timer objects for animating the boat.
     * 
     * @param riverImage BufferedImage representing the river.
     */
    public RiverView(BufferedImage riverImage) {
        super();
        setOpaque(false);
        this.riverImage = riverImage;
        boatImage = ImageLib.BOAT_DEFAULT;
        boatOffset = BOAT_START_OFFSET;
        setBoatOrigin(boatOffset);
    }

    /**
     * @param riverImage BufferedImage representing the river.
     */
    public void setRiverImage(BufferedImage riverImage) {
        this.riverImage = riverImage;
    }

    /**
     * @return BufferedImage representing the river.
     */
    public BufferedImage getRiverImage() {
        return riverImage;
    }

    /**
     * @param boat The image for painting the boat.
     */
    public void setBoat(BufferedImage boatImage) {
        this.boatImage = boatImage;
    }

    /**
     * Compute the origin for painting the boat on top of the river.
     * @param offset The offset from the side of the river.
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

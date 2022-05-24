package farmer.view;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.Timer;
import farmer.view.ImageLib.Boat;

/**
 * The view component for the river and the boat. 
 */
public class River extends JPanel {
    
    public static final int BOAT_START_OFFSET = 0;
    public static final int BOAT_END_OFFSET = 50;
    private static final int ANIMATION_DELAY = 50;
    private BufferedImage riverImage;
    private Boat boat;
    private int boatXOrigin;
    private int boatYOrigin;
    private int boatOffset;
    private Timer moveBoatFoward;
    private Timer moveBoatBackward;

    /**
     * Constructs the River with the given image and a Boat.DEFAULT object 
     * Initializes Timer objects for animating the boat.
     * 
     * @param riverImage BufferedImage representing the river.
     */
    public River(BufferedImage riverImage) {
        this.riverImage = riverImage;
        boat = Boat.DEFAULT;
        boatOffset = BOAT_START_OFFSET;
        setBoatOrigin(boatOffset);
        moveBoatFoward = new Timer(ANIMATION_DELAY, e -> {
            setBoatOrigin(++boatOffset);
            repaint();
            if (boatOffset == BOAT_END_OFFSET) {
                Timer t = (Timer) e.getSource();
                t.stop();
            }
        });
        moveBoatBackward = new Timer(ANIMATION_DELAY, e -> {
            setBoatOrigin(--boatOffset);
            repaint();
            if (boatOffset == BOAT_START_OFFSET) {
                Timer t = (Timer) e.getSource();
                t.stop();
            }
        });
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
     * @param boat Boat.DEFAULT, Boat.CHICKEN, Boat.FOX, or Boat.GRAIN
     */
    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    /**
     * @return Boat.DEFAULT, Boat.CHICKEN, Boat.FOX, or Boat.GRAIN
     */
    public Boat getBoat() {
        return boat;
    }

    /**
     * Moves the boat foward/backward across the river depending on the value of boatOffset.
     */
    public void startAnimation() {
        if (boatOffset == BOAT_START_OFFSET) {
            moveBoatFoward.start();
        } else if (boatOffset == BOAT_END_OFFSET) {
            moveBoatBackward.start();
        }
    }

    /**
     * Compute the origin for painting the boat on top of the river.
     * @param offset The offset from the side of the river.
     * @throws IllegalArgumentException If offset is less then BOAT_START_OFFSET or greater than BOAT_END_OFFSET.
     */
    private void setBoatOrigin(int offset) throws IllegalArgumentException {
        if (offset < BOAT_START_OFFSET || offset > BOAT_END_OFFSET) {
            throw new IllegalArgumentException("offset out of range");
        }
        int riverHeight = riverImage.getHeight();
        int riverWidth = riverImage.getWidth();
        int boatHeight = boat.getImage().getHeight();
        int boatWidth = boat.getImage().getWidth();
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
        graphics.drawImage(boat.getImage(), 0, 0, this);
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

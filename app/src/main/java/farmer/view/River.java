package farmer.view;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.Timer;

public class River extends JPanel {
    
    public static final int BOAT_START = 0;
    public static final int BOAT_END = 50;
    private static final int ANIMATION_DELAY = 50;
    private BufferedImage riverImage;
    private BufferedImage boatImage;
    private int boatXOrigin;
    private int boatYOrigin;
    private int boatOffset;
    private Timer moveBoatFoward;
    private Timer moveBoatBackward;

    public River(BufferedImage riverImage, BufferedImage boatImage) {
        this.riverImage = riverImage;
        this.boatImage = boatImage;
        boatOffset = BOAT_START;
        setBoatOrigin(boatOffset);
        moveBoatFoward = new Timer(ANIMATION_DELAY, e -> {
            setBoatOrigin(++boatOffset);
            repaint();
            if (boatOffset == BOAT_END) {
                Timer t = (Timer) e.getSource();
                t.stop();
            }
        });
        moveBoatBackward = new Timer(ANIMATION_DELAY, e -> {
            setBoatOrigin(--boatOffset);
            repaint();
            if (boatOffset == BOAT_START) {
                Timer t = (Timer) e.getSource();
                t.stop();
            }
        });
    }

    public int getBoatOffset() {
        return boatOffset;
    }

    public void setBoatImage(BufferedImage boatImage) {
        this.boatImage = boatImage;
    }

    

    public void startAnimation() {
        if (boatOffset == BOAT_START) {
            moveBoatFoward.start();
        } else {
            moveBoatBackward.start();
        }
        
    }

    private void setBoatOrigin(int offset) throws IllegalArgumentException {
        if (offset < 0 || offset > BOAT_END) {
            throw new IllegalArgumentException("offset out of range");
        }
        int riverHeight = riverImage.getHeight();
        int riverWidth = riverImage.getWidth();
        int boatHeight = boatImage.getHeight();
        int boatWidth = boatImage.getWidth();
        int stepDistance = (riverWidth - boatWidth) / BOAT_END;
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

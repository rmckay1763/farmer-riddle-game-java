package farmer.view;

import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class River extends JPanel {
    
    public static final int BOAT_STEPS = 50;
    private static final int ANIMATION_DELAY = 50;
    private static final String RIVER_IMAGE_FILE = "image/river.png";
    private static final String BOAT_IMAGE_FILE = "image/boat.png";
    private BufferedImage riverImage;
    private BufferedImage boatImage;
    private int boatXOrigin;
    private int boatYOrigin;
    private int boatOffset;

    public River(boolean animate) {
        boatOffset = 0;
        InputStream boatImageStream = 
            getClass().getClassLoader().getResourceAsStream(BOAT_IMAGE_FILE);
        InputStream riverImageStream = 
            getClass().getClassLoader().getResourceAsStream(RIVER_IMAGE_FILE);
        try {
            boatImage = ImageIO.read(boatImageStream);
            riverImage = ImageIO.read(riverImageStream);
            setBoatOrigin(boatOffset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer(ANIMATION_DELAY, e -> {
            setBoatOrigin(boatOffset++);
            repaint();
            if (boatOffset > BOAT_STEPS) {
                Timer t = (Timer) e.getSource();
                t.stop();
            }
        });
        if (animate) {
            timer.start();
        }
        
    }

    private void setBoatOrigin(int offset) {
        if (offset < 0 || offset > BOAT_STEPS) {
            throw new IllegalArgumentException("offset out of range");
        }
        int riverHeight = riverImage.getHeight();
        int riverWidth = riverImage.getWidth();
        int boatHeight = boatImage.getHeight();
        int boatWidth = boatImage.getWidth();
        int stepDistance = (riverWidth - boatWidth) / BOAT_STEPS;
        boatXOrigin = stepDistance * offset;
        boatYOrigin = (riverHeight / 2) - (boatHeight / 2);
    }

    public void moveBoat(int offset, int delay) {
        Timer timer = new Timer(delay, e -> {
            setBoatOrigin(offset);
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
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

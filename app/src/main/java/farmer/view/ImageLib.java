package farmer.view;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLib {

    private static final String RIVER_IMAGE_FILE = "image/river.png";
    private static final String BOAT_IMAGE_FILE = "image/boat.png";

    BufferedImage river;
    BufferedImage boatEmpty;

    private static ImageLib INSTANCE;

    public static ImageLib getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageLib();
        }
        return INSTANCE;
    }

    private ImageLib() {
        InputStream boatImageStream = 
            getClass().getClassLoader().getResourceAsStream(BOAT_IMAGE_FILE);
        InputStream riverImageStream = 
            getClass().getClassLoader().getResourceAsStream(RIVER_IMAGE_FILE);
        try {
            river = ImageIO.read(riverImageStream);
            boatEmpty = ImageIO.read(boatImageStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

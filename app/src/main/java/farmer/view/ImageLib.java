package farmer.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Single reference for all images used in the application. 
 * Uses final static members and enums to enforce singleton pattern.
 * Contains enums for the boat images and farmer item images.
 * Provides static methods for loading BufferedImages/ImageIcons.
 */
public class ImageLib {

    // control button icons
    public static final ImageIcon HELP_ICON;
    public static final ImageIcon CROSS_ICON;
    public static final ImageIcon RESTART_ICON;
    public static final ImageIcon UNLOAD_ICON;
    public static final ImageIcon HELP_ICON_HOVER;
    public static final ImageIcon CROSS_ICON_HOVER;
    public static final ImageIcon RESTART_ICON_HOVER;
    public static final ImageIcon UNLOAD_ICON_HOVER;

    // farmer item icons
    public static final ImageIcon CHICKEN_ICON;
    public static final ImageIcon FOX_ICON;
    public static final ImageIcon GRAIN_ICON;

    // boat images
    public static final BufferedImage BOAT_DEFAULT;
    public static final BufferedImage BOAT_CHICKEN;
    public static final BufferedImage BOAT_FOX;
    public static final BufferedImage BOAT_GRAIN;

    // river image
    public static final BufferedImage RIVER;

    static {
        HELP_ICON = loadImageIcon("image/icon/help.png");
        CROSS_ICON = loadImageIcon("image/icon/cross.png");
        RESTART_ICON = loadImageIcon("image/icon/restart.png");
        UNLOAD_ICON = loadImageIcon("image/icon/unload.png");
        HELP_ICON_HOVER = loadImageIcon("image/icon/help_hover.png");
        CROSS_ICON_HOVER = loadImageIcon("image/icon/cross_hover.png");
        RESTART_ICON_HOVER = loadImageIcon("image/icon/restart_hover.png");
        UNLOAD_ICON_HOVER = loadImageIcon("image/icon/unload_hover.png");
        CHICKEN_ICON = loadImageIcon("image/item/chicken.png");
        FOX_ICON = loadImageIcon("image/item/fox.png");
        GRAIN_ICON = loadImageIcon("image/item/grain.png");
        BOAT_DEFAULT = loadImage("image/boat/default.png");
        BOAT_CHICKEN = loadImage("image/boat/chicken.png");
        BOAT_FOX = loadImage("image/boat/fox.png");
        BOAT_GRAIN = loadImage("image/boat/grain.png");
        RIVER = loadImage("image/river.png");
    }
    
    /**
     * Loads an image resouce into a BufferedImage.
     * 
     * @param path Path to the image resource.
     * @return Loaded ImageResource.
     * @throws ImageLoadException If the resource fails to load.
     */
    public static BufferedImage loadImage(String path) throws ImageLoadException {
        InputStream imageStream = ImageLib.class.getClassLoader().getResourceAsStream(path);
        try {
            return ImageIO.read(imageStream);
        } catch (IOException | IllegalArgumentException e) {
            throw new ImageLoadException(String.format("Failed to load image: %s", path));
        }
    }

    /**
     * Loads an image resource into an ImageIcon.
     * 
     * @param path Path to the resource.
     * @return Loaded ImageIcon.
     * @throws ImageLoadException If the resource fails to load.
     */
    public static ImageIcon loadImageIcon(String path) throws ImageLoadException {
        URL url = ImageLib.class.getClassLoader().getResource(path);
        try {
            return new ImageIcon(url);
        } catch (NullPointerException e) {
            throw new ImageLoadException(String.format("Failed to load image: %s", path));
        }
        
    }
}

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

    private static final String RIVER_PATH = "image/river.png";
    private static final String ITEM_CHICKEN_PATH = "image/item/chicken.png";
    private static final String ITEM_FOX_PATH = "image/item/fox.png";
    private static final String ITEM_GRAIN_PATH = "image/item/grain.png";
    private static final String BOAT_DEFAULT_PATH = "image/boat/default.png";
    private static final String BOAT_CHICKEN_PATH = "image/boat/chicken.png";
    private static final String BOAT_FOX_PATH = "image/boat/fox.png";
    private static final String BOAT_GRAIN_PATH = "image/boat/grain.png";

    private static ImageIcon chickenIcon;
    private static ImageIcon foxIcon = loadImageIcon(ITEM_FOX_PATH);
    private static ImageIcon grainIcon = loadImageIcon(ITEM_GRAIN_PATH);
    private static BufferedImage boatDefault = loadImage(BOAT_DEFAULT_PATH);
    private static BufferedImage boatChicken = loadImage(BOAT_CHICKEN_PATH);
    private static BufferedImage boatFox = loadImage(BOAT_FOX_PATH);
    private static BufferedImage boatGrain = loadImage(BOAT_GRAIN_PATH);
    private static BufferedImage river = loadImage(RIVER_PATH);

    public static ImageIcon getChickeIcon() {
        if (chickenIcon == null) {
            chickenIcon = loadImageIcon(ITEM_CHICKEN_PATH);
        }
        return chickenIcon;
    }

    public static ImageIcon getFoxIcon() {
        if (foxIcon == null) {
            foxIcon = loadImageIcon(ITEM_FOX_PATH);
        }
        return foxIcon;
    }

    public static ImageIcon getGrainIcon() {
        if (grainIcon == null) {
            grainIcon = loadImageIcon(ITEM_GRAIN_PATH);
        }
        return grainIcon;
    }

    public static BufferedImage getBoatDefault() {
        if (boatDefault == null) {
            boatDefault = loadImage(BOAT_DEFAULT_PATH);
        }
        return boatDefault;
    }

    public static BufferedImage getBoatChicken() {
        if (boatChicken == null) {
            boatChicken = loadImage(BOAT_CHICKEN_PATH);
        }
        return boatChicken;
    }

    public static BufferedImage getBoatFox() {
        if (boatFox == null) {
            boatFox = loadImage(BOAT_FOX_PATH);
        }
        return boatFox;
    }

    public static BufferedImage getBoatGrain() {
        if (boatGrain == null) {
            boatGrain = loadImage(BOAT_GRAIN_PATH);
        }
        return boatGrain;
    }

    public static BufferedImage getRiver() {
        if (river == null) {
            river = loadImage(RIVER_PATH);
        }
        return river;
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

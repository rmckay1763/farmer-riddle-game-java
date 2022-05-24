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

    /**
     * Contains a type and ImageIcon for each farmer item.
     */
    public enum Item {

        CHICKEN(ITEM_CHICKEN_PATH),
        FOX(ITEM_FOX_PATH),
        GRAIN(ITEM_GRAIN_PATH);

        /**
         * The ImageIcon to represent the Item.
         */
        private final ImageIcon IMAGE_ICON;

        /**
         * @param path Path to the image resource.
         */
        Item(String path) {
            IMAGE_ICON = loadImageIcon(path);
        }

        /**
         * @return ImageIcon for the Item.
         */
        public ImageIcon getImageIcon() {
            return IMAGE_ICON;
        }
    }

    /**
     * Contains a type and BufferedImage for each boat.
     */
    public enum Boat {

        DEFAULT(BOAT_DEFAULT_PATH),
        CHICKEN(BOAT_CHICKEN_PATH),
        FOX(BOAT_FOX_PATH),
        GRAIN(BOAT_GRAIN_PATH);

        /**
         * The BufferedImage to represent the Boat.
         */
        private final BufferedImage IMAGE;

        /**
         * @param path Path to the image resource.
         */
        Boat(String path) {
            IMAGE = loadImage(path);
        }

        /**
         * @return BufferedImage for the Boat.
         */
        public BufferedImage getImage() {
            return IMAGE;
        }
    }

    private static final BufferedImage RIVER = loadImage(RIVER_PATH);

    /**
     * @return BufferedImage to represent the river in the game.
     */
    public static BufferedImage getRiver() {
        return RIVER;
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

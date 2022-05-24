package farmer.view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import farmer.view.ImageLib.Boat;
import farmer.view.ImageLib.Item;

/**
 * Test class for ImageLib.
 */
public class ImageLibTest {

    final String GOOD_PATH = "image/item/chicken.png";
    final String BAD_PATH = "foo/bar.png";

    /**
     * Test the Item enum.
     */
    @Test
    void itemEnum() {
        Item chicken1 = Item.CHICKEN;
        Item chicken2 = Item.CHICKEN;
        Item fox = Item.FOX;
        Item grain = Item.GRAIN;
        assertEquals(chicken1, chicken2);
        assertNotEquals(fox, grain);
    }

    /**
     * Test the Boat enum.
     */
    @Test
    void boatEnum() {
        Boat chicken1 = Boat.CHICKEN;
        Boat chicken2 = Boat.CHICKEN;
        Boat fox = Boat.FOX;
        Boat grain = Boat.GRAIN;
        assertEquals(chicken1, chicken2);
        assertNotEquals(fox, grain);
    }

    /**
     * Item.getImageIcon() should return a non null instance of ImageIcon.
     */
    @Test
    void itemGetImageIcon() {
        ImageIcon chickenIcon = Item.CHICKEN.getImageIcon();
        ImageIcon foxIcon = Item.FOX.getImageIcon();
        ImageIcon grainIcon = Item.GRAIN.getImageIcon();
        assertNotNull(chickenIcon);
        assertNotNull(foxIcon);
        assertNotNull(grainIcon);
    }

    /**
     * Boat.getImage() should return a non null instance of BufferedImage.
     */
    @Test
    void boatGetImage() {
        BufferedImage defaultImage = Boat.DEFAULT.getImage();
        BufferedImage chickenImage = Boat.CHICKEN.getImage();
        BufferedImage foxImage = Boat.FOX.getImage();
        BufferedImage grainImage = Boat.GRAIN.getImage();
        assertNotNull(defaultImage);
        assertNotNull(chickenImage);
        assertNotNull(foxImage);
        assertNotNull(grainImage);
    }

    /**
     * ImageLib.getRiver() should return a non null BufferedImage.
     */
    @Test
    void getRiver() {
        BufferedImage river = ImageLib.getRiver();
        assertNotNull(river);
    }

    /**
     * ImageLib.loadImage() should return a non null BufferedImage from a valid image resource path.
     */
    @Test
    void loadImage() {
        BufferedImage image = ImageLib.loadImage(GOOD_PATH);
        assertNotNull(image);
    }

    /**
     * ImageLib.loadImageIcon() should return a non null ImageIcon from a valid image resource path.
     */
    @Test
    void loadImageIcon() {
        ImageIcon icon = ImageLib.loadImageIcon(GOOD_PATH);
        assertNotNull(icon);
    }

    /**
     * ImageLib.loadImage() should throw ImageLoadException for bad path.
     */
    @Test
    void loadImageBadPath() {
        ImageLoadException thrown = assertThrows(
            ImageLoadException.class, 
            () -> ImageLib.loadImage(BAD_PATH)
        );
        assertTrue(thrown.getMessage().contains(BAD_PATH));
    }

    /**
     * ImageLib.loadImageIcon() should throw ImageLoadException for bad path.
     */
    @Test
    void loadImageIconBadPath() {
        ImageLoadException thrown = assertThrows(
            ImageLoadException.class,
            () -> ImageLib.loadImageIcon(BAD_PATH)
        );
        assertTrue(thrown.getMessage().contains(BAD_PATH));
    }
}

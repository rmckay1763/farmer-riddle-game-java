package farmer.view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Test class for ImageLib.
 */
public class ImageLibTest {

    final String GOOD_PATH = "image/item/chicken.png";
    final String BAD_PATH = "foo/bar.png";

    @Test
    void chickenIcon() {
        ImageIcon icon = ImageLib.getChickeIcon();
        assertNotNull(icon);
    }

    @Test
    void foxIcon() {
        ImageIcon icon = ImageLib.getFoxIcon();
        assertNotNull(icon);
    }

    @Test
    void grainIcon() {
        ImageIcon icon = ImageLib.getFoxIcon();
        assertNotNull(icon);
    }

    @Test
    void boatDefault() {
        BufferedImage image = ImageLib.getBoatDefault();
        assertNotNull(image);
    }

    @Test
    void boatChicken() {
        BufferedImage image = ImageLib.getBoatChicken();
        assertNotNull(image);
    }

    @Test
    void boatFox() {
        BufferedImage image = ImageLib.getBoatFox();
        assertNotNull(image);
    }

    @Test
    void boatGrain() {
        BufferedImage image = ImageLib.getBoatGrain();
        assertNotNull(image);
    }

    @Test
    void river() {
        BufferedImage image = ImageLib.getRiver();
        assertNotNull(image);
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

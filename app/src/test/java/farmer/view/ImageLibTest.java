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
    void helpIcon() {
        assertNotNull(ImageLib.HELP_ICON);
    }

    @Test
    void crossIcon() {
        assertNotNull(ImageLib.CROSS_ICON);
    }

    @Test
    void unloadIcon() {
        assertNotNull(ImageLib.UNLOAD_ICON);
    }

    @Test
    void restartIcon() {
        assertNotNull(ImageLib.RESTART_ICON);
    }

    @Test
    void helpIconHover() {
        assertNotNull(ImageLib.HELP_ICON_HOVER);
    }

    @Test
    void crossIconHover() {
        assertNotNull(ImageLib.CROSS_ICON_HOVER);
    }

    @Test
    void unloadIconHover() {
        assertNotNull(ImageLib.UNLOAD_ICON_HOVER);
    }

    @Test
    void restartIconHover() {
        assertNotNull(ImageLib.RESTART_ICON_HOVER);
    }

    @Test
    void chickenIcon() {
        assertNotNull(ImageLib.CHICKEN_ICON);
    }

    @Test
    void foxIcon() {
        assertNotNull(ImageLib.FOX_ICON);
    }

    @Test
    void grainIcon() {
        assertNotNull(ImageLib.GRAIN_ICON);
    }

    @Test
    void boatDefault() {
        assertNotNull(ImageLib.BOAT_DEFAULT);
    }

    @Test
    void boatChicken() {
        assertNotNull(ImageLib.BOAT_CHICKEN);
    }

    @Test
    void boatFox() {
        assertNotNull(ImageLib.BOAT_FOX);
    }

    @Test
    void boatGrain() {
        assertNotNull(ImageLib.BOAT_FOX);
    }

    @Test
    void river() {
        assertNotNull(ImageLib.RIVER);
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

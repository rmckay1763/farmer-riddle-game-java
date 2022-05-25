package farmer.view;

import org.junit.jupiter.api.Test;

import farmer.model.ImageLib;
import farmer.model.ImageLib.Boat;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RiverTest {
    
    RiverView river;

    /**
     * Instantiate a new River for each test.
     */
    @BeforeEach
    void init() {
        river = new RiverView(ImageLib.getRiver());
    }

    /**
     * River should not be null.
     */
    @Test()
    void river() {
        assertNotNull(river);
    }

    /**
     * Set boat should update the River instance's boat member.
     */
    @Test()
    void setBoat() {
        river.setBoat(Boat.CHICKEN);
        Boat boat = river.getBoat();
        assertNotNull(boat);
        assertEquals(Boat.CHICKEN, boat);
    }
}

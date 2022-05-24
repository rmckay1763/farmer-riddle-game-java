package farmer.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import farmer.view.ImageLib.Boat;

public class RiverTest {
    
    River river;

    /**
     * Instantiate a new River for each test.
     */
    @BeforeEach
    void init() {
        river = new River(ImageLib.getRiver());
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

package farmer.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

/**
 * Test class for the FarmerModel.
 */
public class FarmerModelTest {

    FarmerModel farmer;
    FarmerEnvironment env;

    /**
     * Initialize each test with a new model and environment.
     */
    @BeforeEach
    void initTest() {
        farmer = new FarmerModel();
        env = new FarmerEnvironment();
    }

    /**
     * The farmer should be on the west bank and not crossing the river.
     */
    @Test
    void initialState() {
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        assertFalse(farmer.isCrossingRiver());
    }

    /**
     * loadItemInBoat should remove the item from the river bank and set itemInBoat.
     */
    @Test
    void loadItemInBoat() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        assertEquals(Item.NONE, env.getItemInBoat());
        farmer.loadItemInBoat(env, Item.CHICKEN);
        assertEquals(Item.CHICKEN, env.getItemInBoat());
        assertFalse(westBank.contains(Item.CHICKEN));
    }

    /**
     * unloadItemFromBoat should add the item to the river bank and set itemInBoat to Item.NONE.
     */
    @Test
    void unloadItemFromBoat() {
        ArrayList<Item> westBank = env.getWestBankItems();
        westBank.clear();
        assertFalse(westBank.contains(Item.CHICKEN));
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        env.setItemInBoat(Item.CHICKEN);
        farmer.unloadItemFromBoat(env);
        assertTrue(westBank.contains(Item.CHICKEN));
    }

    /**
     * startCrossingRiver should set farmer.crossingRiver to true.
     */
    @Test
    void startCrossingRiver() {
        assertFalse(farmer.isCrossingRiver());
        farmer.startCrossingRiver();
        assertTrue(farmer.isCrossingRiver());
    }

    /**
     * doneCrossingRiver should set farmer.crossingRiver to false and switch the farmers location.
     */
    @Test
    void doneCrossingRiver() {
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        farmer.startCrossingRiver();
        farmer.doneCrossingRiver();
        assertEquals(Location.EAST_BANK, farmer.getFarmerLocation());
    }
    
}

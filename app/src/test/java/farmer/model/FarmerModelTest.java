package farmer.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class FarmerModelTest {

    FarmerModel farmer;
    FarmerEnvironment env;

    @BeforeEach
    void initTest() {
        farmer = new FarmerModel();
        env = new FarmerEnvironment();
    }

    @Test
    void initialState() {
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        assertFalse(farmer.isCrossingRiver());
    }

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

    @Test
    void startCrossingRiver() {
        assertFalse(farmer.isCrossingRiver());
        farmer.startCrossingRiver();
        assertTrue(farmer.isCrossingRiver());
    }

    @Test
    void doneCrossingRiver() {
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        farmer.startCrossingRiver();
        farmer.doneCrossingRiver();
        assertEquals(Location.EAST_BANK, farmer.getFarmerLocation());
    }
    
}

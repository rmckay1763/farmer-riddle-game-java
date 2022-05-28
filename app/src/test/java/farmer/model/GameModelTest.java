package farmer.model;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameModelTest {
    
    GameModel model;
    FarmerEnvironment env;
    FarmerModel farmer;

    @BeforeEach
    void initTest() {
        model = new GameModel();
        env = model.getEnvironment();
        farmer = model.getFarmer();
    }

    @Test
    void initialState() {
        ArrayList<Item> eastBank = env.getEastBankItems();
        ArrayList<Item> westBank = env.getWestBankItems();
        assertFalse(farmer.isCrossingRiver());
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        assertTrue(eastBank.isEmpty());
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.FOX));
        assertTrue(westBank.contains(Item.GRAIN));
        assertEquals(env.getItemInBoat(), Item.NONE);
    }

    @Test
    void startCrossingRiver() {
        assertFalse(farmer.isCrossingRiver());
        model.startCrossingRiver();
        assertTrue(farmer.isCrossingRiver());
    }

    @Test
    void startCrossingRiverWhileCrossingRiver() {
        model.startCrossingRiver();
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.startCrossingRiver()
        );
        assertEquals(GameModel.START_CROSSING_RIVER_ERROR, thrown.getMessage());
    }

    @Test
    void doneCrossingRiver() {
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        model.startCrossingRiver();
        model.doneCrossingRiver();
        assertEquals(Location.EAST_BANK, farmer.getFarmerLocation());
    }

    @Test
    void doneCrossingRiverWhileNotCrossingRiver() {
        assertFalse(farmer.isCrossingRiver());
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.doneCrossingRiver()
        );
        assertEquals(GameModel.DONE_CROSSING_RIVER_ERROR, thrown.getMessage());
    }

    @Test
    void loadItemInBoat() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        model.loadItemInBoat(Item.CHICKEN, Location.WEST_BANK);
        assertEquals(Item.CHICKEN, env.getItemInBoat());
        assertFalse(westBank.contains(Item.CHICKEN));
    }

    @Test
    void loadItemInBoatOnOppositeBank() {
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.loadItemInBoat(Item.CHICKEN, Location.EAST_BANK)
        );
        assertEquals(GameModel.SELECT_ITEM_ON_OPPOSITE_BANK_ERROR, thrown.getMessage());
    }

    @Test
    void loadItemInBoatWhileCrossingRiver() {
        model.startCrossingRiver();
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.loadItemInBoat(Item.CHICKEN, Location.EAST_BANK)
        );
        assertEquals(GameModel.SELECT_ITEM_WHILE_CROSSING_RIVER_ERROR, thrown.getMessage());
    }

    @Test
    void unloadItemFromBoat() {
        ArrayList<Item> westBank = env.getWestBankItems();
        westBank.clear();
        env.setItemInBoat(Item.CHICKEN);
        assertFalse(westBank.contains(Item.CHICKEN));
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        model.unloadItemFromBoat();
        assertEquals(Item.NONE, env.getItemInBoat());
        assertTrue(westBank.contains(Item.CHICKEN));
    }

    @Test
    void unloadItemFromBoatWhileCrossingRiver() {
        model.startCrossingRiver();
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.unloadItemFromBoat()
        );
        assertEquals(GameModel.UNLOAD_ITEM_WHILE_CROSSING_RIVER_ERROR, thrown.getMessage());
    }

    @Test
    void unloadItemFromBoatNoItemSelected() {
        assertEquals(Item.NONE, env.getItemInBoat());
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.unloadItemFromBoat()
        );
        assertEquals(GameModel.NO_ITEM_SELECTED_ERROR, thrown.getMessage());
    }

}

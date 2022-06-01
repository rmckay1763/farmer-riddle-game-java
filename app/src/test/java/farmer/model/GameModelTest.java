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

    /**
     * initialize each test with a new model and expose the farmer and envrironment.
     */
    @BeforeEach
    void initTest() {
        model = new GameModel();
        model.setInitialState();
        env = model.getEnvironment();
        farmer = model.getFarmer();
    }

    /**
     * west bank should contain chicken, fox, and grain. east bank should be empty. 
     * farmer should be on west bank and not crossing river. boat should be empty.
     */
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

    /**
     * startCrossingRiver should not change the farmer's location
     */
    @Test
    void startCrossingRiver() {
        String expected = String.format(GameModel.START_CROSSING_MESSAGE, Location.WEST_BANK);
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        assertFalse(farmer.isCrossingRiver());
        model.startCrossingRiver();
        assertTrue(farmer.isCrossingRiver());
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        assertEquals(expected, model.getGameStatus());
    }

    /**
     * startCrossingRiver while isCrossingRiver is true should throw IllegalActionException
     */
    @Test
    void startCrossingRiverWhileCrossingRiver() {
        model.startCrossingRiver();
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.startCrossingRiver()
        );
        assertEquals(GameModel.CROSSING_RIVER_ERROR, thrown.getMessage());
    }

    /**
     * doneCrossingRiver should change farmer's location
     */
    @Test
    void doneCrossingRiver() {
        String expected = String.format(GameModel.DONE_CROSSING_MESSAGE, Location.EAST_BANK);
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        model.loadItemInBoat(Item.CHICKEN, Location.WEST_BANK);
        model.startCrossingRiver();
        model.doneCrossingRiver();
        assertEquals(Location.EAST_BANK, farmer.getFarmerLocation());
        assertEquals(expected, model.getGameStatus());
    }

    /**
     * doneCrossingRiver while isCrossingRiver is fale should throw IllegalActionException
     */
    @Test
    void doneCrossingRiverWhileNotCrossingRiver() {
        assertFalse(farmer.isCrossingRiver());
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.doneCrossingRiver()
        );
        assertEquals(null, thrown.getMessage());
    }

    /**
     * loadItemInBoat should remove item from list and set itemLoadedInBoat
     */
    @Test
    void loadItemInBoat() {
        String expected = String.format(GameModel.ITEM_LOADED_MESSAGE, Item.CHICKEN);
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        model.loadItemInBoat(Item.CHICKEN, Location.WEST_BANK);
        assertEquals(Item.CHICKEN, env.getItemInBoat());
        assertFalse(westBank.contains(Item.CHICKEN));
        assertEquals(expected, model.getGameStatus());
    }

    /**
     * loadItemInBoat should throw IllegalActionException if item on opposite bank from farmer
     */
    @Test
    void loadItemInBoatOnOppositeBank() {
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.loadItemInBoat(Item.CHICKEN, Location.EAST_BANK)
        );
        assertEquals(GameModel.OPPOSITE_BANK_ERROR, thrown.getMessage());
    }

    /**
     * loadItemInBoat should throw IllegalActionException if isCrossingRiver is true
     */
    @Test
    void loadItemInBoatWhileCrossingRiver() {
        model.startCrossingRiver();
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.loadItemInBoat(Item.CHICKEN, Location.WEST_BANK)
        );
        assertEquals(GameModel.CROSSING_RIVER_ERROR, thrown.getMessage());
    }

    /**
     * loadItemInBoat should throw IllegalActionException if itemLoadedInBoat is not Item.NONE
     */
    @Test
    void loadItemInBoatWhenBoatFull() {
        model.loadItemInBoat(Item.CHICKEN, Location.WEST_BANK);
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.loadItemInBoat(Item.FOX, Location.WEST_BANK)
        );
        assertEquals(GameModel.BOAT_FULL_ERROR, thrown.getMessage());
    }

    /**
     * unloadItemFromBoat should add item to list and set itemLoadedInBoat to Item.NONE
     */
    @Test
    void unloadItemFromBoat() {
        String expected = String.format(GameModel.ITEM_UNLOADED_MESSAGE, Item.CHICKEN);
        ArrayList<Item> westBank = env.getWestBankItems();
        westBank.clear();
        env.setItemInBoat(Item.CHICKEN);
        assertFalse(westBank.contains(Item.CHICKEN));
        assertEquals(Location.WEST_BANK, farmer.getFarmerLocation());
        model.unloadItemFromBoat();
        assertEquals(Item.NONE, env.getItemInBoat());
        assertTrue(westBank.contains(Item.CHICKEN));
        assertEquals(expected, model.getGameStatus());
    }

    /**
     * unloadItemFromBoat should throw IllegalActionException if isCrossingRiver is true
     */
    @Test
    void unloadItemFromBoatWhileCrossingRiver() {
        model.startCrossingRiver();
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.unloadItemFromBoat()
        );
        assertEquals(GameModel.CROSSING_RIVER_ERROR, thrown.getMessage());
    }

    /**
     * unloadItemFromBoat should throw IllegalActionException if itemLoadedInBoat is Item.NONE
     */
    @Test
    void unloadItemFromBoatNoItemSelected() {
        assertEquals(Item.NONE, env.getItemInBoat());
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.unloadItemFromBoat()
        );
        assertEquals(GameModel.BOAT_EMPTY_ERROR, thrown.getMessage());
    }

    /**
     * foxAteChicken should return true if fox and chicken on opposite bank from farmer
     */
    @Test
    void foxAteChicken() {
        ArrayList<Item> westBank = env.getWestBankItems();
        model.startCrossingRiver();
        model.doneCrossingRiver();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.FOX));
        assertEquals(Location.EAST_BANK, farmer.getFarmerLocation());
        assertEquals(GameModel.FOX_ATE_CHICKEN_MESSAGE, model.getGameStatus());
    }

    /**
     * chickenAteGrain should return true if chicken and grain are on opposite bank from farmer
     */
    @Test
    void chickenAteGrain() {
        ArrayList<Item> westBank = env.getWestBankItems();
        model.loadItemInBoat(Item.FOX, Location.WEST_BANK);
        model.startCrossingRiver();
        model.doneCrossingRiver();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.GRAIN));
        assertEquals(Location.EAST_BANK, farmer.getFarmerLocation());
        assertEquals(GameModel.CHICKEN_ATE_GRAIN_MESSAGE, model.getGameStatus());
    }

    /**
     * All actions should throw IllegalActionException if gameOver is true
     */
    @Test
    void gameOver() {

        // leaving the chicken and the fox on the wset bank should end game
        model.startCrossingRiver();
        model.doneCrossingRiver();

        // loadItemInBoat
        IllegalActionException thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.loadItemInBoat(Item.CHICKEN, Location.EAST_BANK)
        );
        assertEquals(GameModel.GAME_OVER_ERROR, thrown.getMessage());

        // unloadItemFromBoat
        thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.unloadItemFromBoat()
        );
        assertEquals(GameModel.GAME_OVER_ERROR, thrown.getMessage());

        // startCrossingRiver
        thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.startCrossingRiver()
        );
        assertEquals(GameModel.GAME_OVER_ERROR, thrown.getMessage());

        // doneCrossingRiver
        thrown = assertThrows(
            IllegalActionException.class, 
            () -> model.doneCrossingRiver()
        );
        assertEquals(GameModel.GAME_OVER_ERROR, thrown.getMessage());
    }

    /**
     * simulates a complete game where the player wins
     */
    @Test
    void completeGame() {
        ArrayList<Item> eastBank = env.getEastBankItems();

        // take chicken to east bank
        model.loadItemInBoat(Item.CHICKEN, Location.WEST_BANK);
        model.startCrossingRiver();
        model.doneCrossingRiver();
        model.unloadItemFromBoat();

        // cross back to west bank
        model.startCrossingRiver();
        model.doneCrossingRiver();

        // take fox to east bank
        model.loadItemInBoat(Item.FOX, Location.WEST_BANK);
        model.startCrossingRiver();
        model.doneCrossingRiver();
        model.unloadItemFromBoat();

        // take chicken back to west bank
        model.loadItemInBoat(Item.CHICKEN, Location.EAST_BANK);
        model.startCrossingRiver();
        model.doneCrossingRiver();
        model.unloadItemFromBoat();

        // take grain to east bank
        model.loadItemInBoat(Item.GRAIN, Location.WEST_BANK);
        model.startCrossingRiver();
        model.doneCrossingRiver();
        model.unloadItemFromBoat();

        // cross back to west bank
        model.startCrossingRiver();
        model.doneCrossingRiver();

        // take chicken to east bank
        model.loadItemInBoat(Item.CHICKEN, Location.WEST_BANK);
        model.startCrossingRiver();
        model.doneCrossingRiver();
        model.unloadItemFromBoat();

        // all items should now be on east bank
        assertTrue(eastBank.contains(Item.CHICKEN));
        assertTrue(eastBank.contains(Item.FOX));
        assertTrue(eastBank.contains(Item.GRAIN));
        assertEquals(GameModel.GAME_WON_MESSAGE, model.getGameStatus());
    }

}

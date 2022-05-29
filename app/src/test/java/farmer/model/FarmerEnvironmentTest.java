package farmer.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class FarmerEnvironmentTest {

    FarmerEnvironment env;

    /**
     * Initialize each test with new environment. 
     * westBankItems contains CHICKEN, FOX, GRAIN and eastBankItems is empty.
     */
    @BeforeEach
    void initTest() {
        env = new FarmerEnvironment();
    }

    @Test
    void initialState() {
        ArrayList<Item> eastBank = env.getEastBankItems();
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(eastBank.isEmpty());
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.FOX));
        assertTrue(westBank.contains(Item.GRAIN));
        assertEquals(env.getItemInBoat(), Item.NONE);
    }

    @Test
    void addItemToEastBank() {
        ArrayList<Item> eastBank = env.getEastBankItems();
        assertFalse(eastBank.contains(Item.CHICKEN));
        env.addItemToEastBank(Item.CHICKEN);
        assertTrue(eastBank.contains(Item.CHICKEN));
    }

    @Test
    void addItemToWestBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        westBank.clear();
        assertFalse(westBank.contains(Item.CHICKEN));
        env.addItemToWestBank(Item.CHICKEN);
        assertTrue(westBank.contains(Item.CHICKEN));
    }

    @Test
    void removeItemFromWestBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        env.removeItemFromWestBank(Item.CHICKEN);
        assertFalse(westBank.contains(Item.CHICKEN));
    }
    
    /**
     * Removing all items from westBankItems should win the game.
     */
    @Test
    void allItemsAcross() {
        env.addItemToEastBank(Item.CHICKEN);
        env.addItemToEastBank(Item.FOX);
        env.addItemToEastBank(Item.GRAIN);
        assertTrue(env.allItemsAcross());
    }

    @Test
    void hasWonFalse() {
        ArrayList<Item> eastBank = env.getEastBankItems();
        assertFalse(eastBank.contains(Item.CHICKEN));
        assertFalse(env.allItemsAcross());
    }

    /**
     * westBankItems contains CHICKEN + FOX and farmer on east bank should loose game.
     */
    @Test
    void foxAteChickenFarmerOnEastBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.FOX));
        assertTrue(env.foxAteChicken(Location.EAST_BANK));
    }

    @Test
    void chickenAteGrainFarmerOnEastBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.GRAIN));
        assertTrue(env.chickenAteGrain(Location.EAST_BANK));
    }

    @Test
    void foxAteChickenFarmerOnWestBank() {
        env.addItemToEastBank(Item.CHICKEN);
        env.addItemToEastBank(Item.FOX);
        assertTrue(env.foxAteChicken(Location.WEST_BANK));
    }

    @Test
    void chickenAteGrainFarmerOnWestBank() {
        env.addItemToEastBank(Item.CHICKEN);
        env.addItemToEastBank(Item.GRAIN);
        assertTrue(env.chickenAteGrain(Location.WEST_BANK));
    }
}

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
        env.setInitialState();
    }

    /**
     * east bank should be empty, west bank should contain chicken, fox, and grain. 
     * itemInBoat should be Item.NONE.
     */
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

    /**
     * addItemToEastBank should add the item to the east bank list.
     */
    @Test
    void addItemToEastBank() {
        ArrayList<Item> eastBank = env.getEastBankItems();
        assertFalse(eastBank.contains(Item.CHICKEN));
        env.addItemToEastBank(Item.CHICKEN);
        assertTrue(eastBank.contains(Item.CHICKEN));
    }

    /**
     * addItemToWestBank should add the item to the west bank list.
     */
    @Test
    void addItemToWestBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        westBank.clear();
        assertFalse(westBank.contains(Item.CHICKEN));
        env.addItemToWestBank(Item.CHICKEN);
        assertTrue(westBank.contains(Item.CHICKEN));
    }

    /**
     * removeItemFromWestBank should remove the item from the west bank list.
     */
    @Test
    void removeItemFromWestBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        env.removeItemFromWestBank(Item.CHICKEN);
        assertFalse(westBank.contains(Item.CHICKEN));
    }
    
    /**
     * allItemsCross should return true if east bank contains chicken, fox, and grain.
     */
    @Test
    void allItemsAcross() {
        env.addItemToEastBank(Item.CHICKEN);
        env.addItemToEastBank(Item.FOX);
        env.addItemToEastBank(Item.GRAIN);
        assertTrue(env.allItemsAcross());
    }

    /**
     * allItemsAcross should return false if east bank does not contain chicken.
     */
    @Test
    void allItemsAcrossFalse() {
        ArrayList<Item> eastBank = env.getEastBankItems();
        assertFalse(eastBank.contains(Item.CHICKEN));
        assertFalse(env.allItemsAcross());
    }

    /**
     * foxAteChicken should return true if farmer on east bank and west bank contains fox and chicken.
     */
    @Test
    void foxAteChickenFarmerOnEastBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.FOX));
        assertTrue(env.foxAteChicken(Location.EAST_BANK));
    }

    /**
     * chickenAteGrain should return true if farmer on east bank and west bank contains chicken and grain.
     */
    @Test
    void chickenAteGrainFarmerOnEastBank() {
        ArrayList<Item> westBank = env.getWestBankItems();
        assertTrue(westBank.contains(Item.CHICKEN));
        assertTrue(westBank.contains(Item.GRAIN));
        assertTrue(env.chickenAteGrain(Location.EAST_BANK));
    }

    /**
     * foxAteChicken should return true if farmer east bank and west bank contains fox and chicken.
     */
    @Test
    void foxAteChickenFarmerOnWestBank() {
        env.addItemToEastBank(Item.CHICKEN);
        env.addItemToEastBank(Item.FOX);
        assertTrue(env.foxAteChicken(Location.WEST_BANK));
    }

    /**
     * chickenAteGrain should return true if farmer on west bank and east bank contains chicken and grain.
     */
    @Test
    void chickenAteGrainFarmerOnWestBank() {
        env.addItemToEastBank(Item.CHICKEN);
        env.addItemToEastBank(Item.GRAIN);
        assertTrue(env.chickenAteGrain(Location.WEST_BANK));
    }
}

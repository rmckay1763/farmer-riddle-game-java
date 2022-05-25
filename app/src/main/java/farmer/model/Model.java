package farmer.model;

import java.util.ArrayList;

import farmer.model.ImageLib.Item;

public class Model {

    private ArrayList<Item> westRiverBank;
    private ArrayList<Item> eastRiverBank;

    public Model() {
        westRiverBank = new ArrayList<>();
        eastRiverBank = new ArrayList<>();
        westRiverBank.add(Item.CHICKEN);
        westRiverBank.add(Item.FOX);
        westRiverBank.add(Item.GRAIN);
    }

    public ArrayList<Item> getWestRiverBank() {
        return westRiverBank;
    }

    public ArrayList<Item> getEastRiverBank() {
        return eastRiverBank;
    }

    public void foo() {}
}

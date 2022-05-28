package farmer.model;

public class FarmerModel {

    private Location farmerLocation;
    private boolean crossingRiver;

    public FarmerModel() {
        setInitialState();
    }

    public void setInitialState() {
        farmerLocation = Location.WEST_BANK;
        crossingRiver = false;
    }

    public boolean isCrossingRiver() {
        return crossingRiver;
    }

    public Location getFarmerLocation() {
        return farmerLocation;
    }
    
    public void loadItemInBoat(FarmerEnvironment environment, Item item) {
        switch (farmerLocation) {
            case EAST_BANK:
                environment.removeItemFromEastBank(item);
                break;
            case WEST_BANK:
                environment.removeItemFromWestBank(item);
                break;
        }
        environment.setItemInBoat(item);
    }

    public void unloadItemFromBoat(FarmerEnvironment environment) {
        Item itemInBoat = environment.getItemInBoat();
        environment.setItemInBoat(Item.NONE);
        switch (farmerLocation) {
            case EAST_BANK:
                environment.addItemToEastBank(itemInBoat);
                break;
            case WEST_BANK:
                environment.addItemToWestBank(itemInBoat);
                break;
        }
    }

    public void startCrossingRiver() {
        crossingRiver = true;
    }

    public void doneCrossingRiver() {
        crossingRiver = false;
        switch (farmerLocation) {
            case EAST_BANK:
                farmerLocation = Location.WEST_BANK;
                break;
            case WEST_BANK:
                farmerLocation = Location.EAST_BANK;
                break;
        }
    }
}

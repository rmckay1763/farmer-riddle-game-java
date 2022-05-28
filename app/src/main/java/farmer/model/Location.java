package farmer.model;

public enum Location {
    EAST_BANK("east bank"),
    WEST_BANK("west bank");

    private String string;

    private Location(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}

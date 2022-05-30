package farmer.model;

/**
 * <pre>
 * Location of the farmer or an item relative to the river.
 * 
 *  - EAST_BANK
 *  - WEST_BANK
 * </pre>
 */
public enum Location {

    EAST_BANK("East River Bank"),
    WEST_BANK("West River Bank");

    private String string;

    private Location(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}

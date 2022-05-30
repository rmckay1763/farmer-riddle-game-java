package farmer.model;

/**
 * <pre>
 * Item that the farmer can take across the river.
 * 
 *  - NONE
 *  - CHICKEN
 *  - FOX
 *  - GRAIN
 * </pre>
 */
public enum Item {
    NONE("None"),
    CHICKEN("Chicken"),
    FOX("Fox"),
    GRAIN("Grain");

    private String string;

    private Item(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}

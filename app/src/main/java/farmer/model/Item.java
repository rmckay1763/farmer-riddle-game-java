package farmer.model;

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

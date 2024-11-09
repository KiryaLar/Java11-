package ThirdLesson;

public enum FuelType {
    GAS,
    DIESEL,
    ELECTRICITY,
    NONE;

    @Override
    public String toString() {
        return this.name();
    }
}

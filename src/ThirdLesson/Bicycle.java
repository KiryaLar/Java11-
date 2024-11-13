package ThirdLesson;

public final class Bicycle extends Vehicle implements RoadDrivable {

    public Bicycle(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
        super(brand, fuelType, peopleCapacity, wheelsNumber);
    }

    @Override
    public void move() {
        drive();
    }
}

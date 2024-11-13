package ThirdLesson;

public non-sealed class Plane extends Vehicle implements Flyable, RoadDrivable {

    public Plane(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
        super(brand, fuelType, peopleCapacity, wheelsNumber);
    }

    @Override
    public void move() {
        fly();
        drive();
    }
}

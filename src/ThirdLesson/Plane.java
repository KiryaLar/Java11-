package ThirdLesson;

public non-sealed class Plane extends Vehicle implements Flyable, RoadDrivable {

    public Plane(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
        super(brand, fuelType, peopleCapacity, wheelsNumber);
    }

    @Override
    public void fly() {
        System.out.println("Я могу летать");
    }

    @Override
    public void drive() {
        System.out.println("Я могу ездить");
    }

    @Override
    public void move() {
        fly();
        drive();
    }
}

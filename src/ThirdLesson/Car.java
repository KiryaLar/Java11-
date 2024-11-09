package ThirdLesson;

public non-sealed class Car extends Vehicle implements RoadDrivable{

    public Car(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
        super(brand, fuelType, peopleCapacity, wheelsNumber);
    }

    @Override
    public void drive() {
        System.out.println("Я могу ехать");
    }

    @Override
    public void move() {
        drive();
    }
}

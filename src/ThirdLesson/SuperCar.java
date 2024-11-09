package ThirdLesson;

public final class SuperCar extends Vehicle implements Swimmable, Flyable, RoadDrivable {

    public SuperCar(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
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
    public void swim() {
        System.out.println("Я могу плавать");
    }

    @Override
    public void move() {
        System.out.println("Я могу всё");
        fly();
        swim();
        drive();
    }
}

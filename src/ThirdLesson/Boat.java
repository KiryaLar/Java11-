package ThirdLesson;

public non-sealed class Boat extends Vehicle implements Swimmable{

    public Boat(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
        super(brand, fuelType, peopleCapacity, wheelsNumber);
    }

    @Override
    public void swim() {
        System.out.println("Я могу плавать");
    }

    @Override
    public void move() {
        swim();
    }
}

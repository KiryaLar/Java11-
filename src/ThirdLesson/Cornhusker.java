package ThirdLesson;
public class Cornhusker extends Plane implements Swimmable{

    public Cornhusker(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
        super(brand, fuelType, peopleCapacity, wheelsNumber);
    }

    @Override
    public void swim() {
        System.out.println("Могу находиться на воде");
    }
}

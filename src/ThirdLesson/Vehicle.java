package ThirdLesson;

public abstract sealed class Vehicle permits Plane,Bicycle,Boat,Car,SuperCar{
    private String brand;
    private FuelType fuelType;
    protected boolean hasWheels;
    protected int peopleCapacity;
    protected int wheelsNumber;

    public Vehicle(String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber) {
        this.brand = brand;
        this.fuelType = fuelType;
        this.peopleCapacity = peopleCapacity;
        this.wheelsNumber = wheelsNumber;
        hasWheels = wheelsNumber > 0;
    }

    public abstract void move();

    public String getBrand() {
        return brand;
    }

    public FuelType getTypeFuel() throws NonFuelVehicleException {
        if (fuelType.equals(FuelType.NONE)) {
            throw new NonFuelVehicleException("This vehicle hasn't any fuel");
        } else {
            return fuelType;
        }
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public int getWheelsNumber() throws NonWheeledVehicleException {
        if (hasWheels) {
            return wheelsNumber;
        } else {
            throw new NonWheeledVehicleException("This vehicle hasn't any wheels");
        }
    }
}

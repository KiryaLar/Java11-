package ThirdLesson;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            //String brand, FuelType fuelType, int peopleCapacity, int wheelsNumber
            String brand = scanner.nextLine();
            FuelType fuelType = FuelType.GAS;
            int peopleCapacity = getPositiveNumber(scanner);
            int wheelsNumber = getPositiveNumber(scanner);
            Vehicle superCar = new SuperCar(brand, fuelType, peopleCapacity, wheelsNumber);
        } catch (Exception e) {
            System.out.println("Какая то ошибка");
        }
    }

    public static int getPositiveNumber(Scanner scanner) {
            int result;
            while (true){
                try {
                    result = scanner.nextInt();
                    if (result < 0) {
                        throw new InvalidCapacityException();
                    }
                    return result;
                } catch (InputMismatchException e) {
                    System.out.println("Неправиьный ввод. Введите целое число.");
                    scanner.nextLine();
                } catch (InvalidCapacityException e) {
                    System.out.println("Введите число больше 0");
                    scanner.nextLine();
                }
            }
    }


}
class InvalidCapacityException extends NoSuchElementException {}

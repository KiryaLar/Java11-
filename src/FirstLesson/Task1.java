package FirstLesson;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.println("Введите размер массива");
            int capacity = getValidatedInt(scanner);

            System.out.println("Введите нижнюю и верхнюю границы генерации случайных чисел");

            int numb1 = scanner.nextInt();
            int numb2 = scanner.nextInt();
            int minEdge = Math.min(numb1, numb2);
            int maxEdge = Math.max(numb1, numb2);

            int[] arrayInt = getRandomIntArray(capacity, minEdge, maxEdge);
            double[] arrayDouble = getRandomDoubleArray(capacity, minEdge, maxEdge);

            printMax(arrayInt);
            printMin(arrayInt);
            printAverage(arrayInt);
            sort(arrayInt);
            sortDescending(arrayInt);

            printMax(arrayDouble);
            printMin(arrayDouble);
            printAverage(arrayDouble);
            sort(arrayDouble);
            sortDescending(arrayDouble);
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка");
        }
    }

    public static int[] getRandomIntArray(int capacity, int min, int max) {
        int[] array = new int[capacity];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (min + (Math.random() * ((max - min) + 1)));
        }
        return array;
    }
    public static double[] getRandomDoubleArray(int capacity, int min, int max) {
        double[] array = new double[capacity];
        for (int i = 0; i < array.length; i++) {
            array[i] = min + (Math.random() * ((max - min) + 1));
        }
        return array;
    }

    public static void printMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println(max);
    }

    public static void printMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println(min);
    }

    public static void printAverage(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        System.out.println(sum / array.length);
    }

    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int copy = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > copy) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = copy;
        }
        return array;
    }

    public static int[] sortDescending(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int copy = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] < copy) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = copy;
        }
        return array;
    }

    public static void printMax(double[] array) {
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println(max);
    }

    public static void printMin(double[] array) {
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println(min);
    }

    public static void printAverage(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        System.out.println(sum / array.length);
    }

    public static double[] sort(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            double copy = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > copy) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = copy;
        }
        return array;
    }

    public static double[] sortDescending(double[] array) {
        for (int i = 1; i < array.length; i++) {
            double copy = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] < copy) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = copy;
        }
        return array;
    }

    public static int getValidatedInt(Scanner scanner) {
        int result;
        while (true){
            try {
                result = scanner.nextInt();
                return result;
            } catch (InputMismatchException e) {
                System.out.println("Неправиьный ввод. Введите целое число.");
                scanner.nextLine();
            }
        }
    }

    public static int getArraySize(Scanner scanner) {
        int result;
        while (true){
            try {
                result = getValidatedInt(scanner);
                if (result > 0) {
                    return result;
                } else throw new ArrayIndexOutOfBoundsException();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Размер массива должен быть больше нуля");
                scanner.nextLine();
            }
        }
    }


}

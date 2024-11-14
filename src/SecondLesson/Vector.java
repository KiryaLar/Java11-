package SecondLesson;

import java.util.Random;

public class Vector {
    int x;
    int y;
    int z;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public double scalarProduct(Vector vector) {
        return (this.x * vector.x + this.y * vector.y + this.z * vector.z);
    }

    public Vector vectorProduct(Vector vector) {
        int withoutX = this.y * vector.z - this.z * vector.y;
        int withoutY = this.z * vector.x - this.x * vector.z;
        int withoutZ = this.x * vector.y - this.y * vector.x;
        return new Vector(withoutX, withoutY, withoutZ);
    }

    public double calculateAngle(Vector vector) {
        double lengths = getLength() * vector.getLength();

        if (lengths == 0) {
            //Странная херня=)
            // По ТЗ надо было, чтобы без ошиок все закончилось
            try {
                throw new ArithmeticException("Нулевой вектор");
            } catch (ArithmeticException e) {
                e.getMessage();
            }
        }

        return Math.acos(scalarProduct(vector) / (lengths));
    }

    public Vector add(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }

    public Vector minus(Vector vector) {
        return new Vector(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    public static Vector[] getVectors(int n) {
        Vector[] vectors = new Vector[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int newX = random.nextInt() * 10;
            int newY = random.nextInt() * 10;
            int newZ = random.nextInt() * 10;
            vectors[i] = new Vector(newX, newY, newZ);
        }
        return vectors;
    }
}

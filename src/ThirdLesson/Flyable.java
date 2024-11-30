package ThirdLesson;

public interface Flyable {
    default void fly() {
        System.out.println("Я могу летать");
    }
}

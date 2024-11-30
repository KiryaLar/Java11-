package ThirdLesson;

public interface Swimmable {
    default void swim() {
        System.out.println("Я могу плавать");
    }
}

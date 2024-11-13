package ThirdLesson;

public interface RoadDrivable {
    default void drive() {
        System.out.println("Я могу ездить");
    }
}

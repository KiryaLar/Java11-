package SOLID_4;

public class Main {

    public static void main(String[] args) {

        try {
            ObservableStringBuilder obsSB = new ObservableStringBuilder();

            Observer observer1 = new MyStringBuilder("First");
            Observer observer2 = new MyStringBuilder("Second");

            obsSB.addObserver(observer1);
            obsSB.addObserver(observer2);

            obsSB.append("Hello, I'm from ObservableStringBuilder");
            obsSB.delete(0, 7);
            obsSB.delete(4, 9);
            obsSB.clear();

            obsSB.removeObserver(observer1);
            obsSB.append("I'm alone");
        } catch (UnknownException e) {
            e.getMessage();
        }

    }
}

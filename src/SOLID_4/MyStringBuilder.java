package SOLID_4;

public class MyStringBuilder implements Observer{
    private String name;

    public MyStringBuilder(String name) {
        this.name = name;
    }

    @Override
    public void update(String currentValue) {
        System.out.println("StringBuilder " + name + " изменен.\nТекущее значение: " + currentValue);
    }
}

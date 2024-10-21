package DecoratorDesignPattern;

public class SimpleCoffee implements Coffee {
    public double getCost() {
        return 5.0;
    }

    public String getDescription() {
        return "Simple Coffee";
    }
}

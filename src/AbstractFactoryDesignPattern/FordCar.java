package AbstractFactoryDesignPattern;

public class FordCar implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a Ford Car!");
    }
}

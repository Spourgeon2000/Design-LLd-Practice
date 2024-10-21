package AbstractFactoryDesignPattern;

public class FordTruck implements Truck {
    @Override
    public void drive() {
        System.out.println("Driving a Ford Truck!");
    }
}

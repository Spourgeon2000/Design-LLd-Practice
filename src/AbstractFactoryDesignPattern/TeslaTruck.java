package AbstractFactoryDesignPattern;

public class TeslaTruck implements Truck {
    @Override
    public void drive() {
        System.out.println("Driving a Tesla Truck!");
    }
}
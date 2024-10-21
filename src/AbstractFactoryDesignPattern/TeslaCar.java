package AbstractFactoryDesignPattern;

public class TeslaCar implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a Tesla Car!");
    }
}

package AbstractFactoryDesignPattern;

public class FordFactoryAbstract implements AbstractVehicleFactory {
    @Override
    public Car createCar() {
        return new FordCar();
    }

    @Override
    public Truck createTruck() {
        return new FordTruck();
    }
}


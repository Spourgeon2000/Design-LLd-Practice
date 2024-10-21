package AbstractFactoryDesignPattern;

public class TeslaFactoryAbstract implements AbstractVehicleFactory {
    @Override
    public Car createCar() {
        return new TeslaCar();
    }

    @Override
    public Truck createTruck() {
        return new TeslaTruck();
    }
}

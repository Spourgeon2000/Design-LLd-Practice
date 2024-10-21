package ObserverDesignPattern;

public class ForecastDisplay implements Observer {
    private float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.pressure = pressure;
        display();
    }

    private void display() {
        System.out.println("Forecast: Pressure is " + pressure + " Pa.");
    }
}

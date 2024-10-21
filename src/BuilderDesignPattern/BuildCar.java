package BuilderDesignPattern;

public class BuildCar {
    private String engine;
    private int wheels;
    private String color;

    // Private constructor to force the use of Builder
    private BuildCar(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheels=" + wheels +
                ", color='" + color + '\'' +
                '}';
    }

    // Static nested Builder class
    public static class CarBuilder {
        private String engine;
        private int wheels;
        private String color;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public BuildCar build() {
            return new BuildCar(this);
        }
    }
}

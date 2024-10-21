package FacadeDesignPattern;

public class Projector {
    public void on() {
        System.out.println("Projector is turned on.");
    }

    public void off() {
        System.out.println("Projector is turned off.");
    }

    public void setInput(String input) {
        System.out.println("Projector input set to " + input);
    }
}

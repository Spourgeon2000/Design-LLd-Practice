package FacadeDesignPattern;

public class DvdPlayer {
    public void on() {
        System.out.println("DVD player is turned on.");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void stop() {
        System.out.println("Stopping DVD.");
    }

    public void off() {
        System.out.println("DVD player is turned off.");
    }
}
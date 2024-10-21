package FacadeDesignPattern;

public class SoundSystem {
    public void on() {
        System.out.println("Sound system is turned on.");
    }

    public void off() {
        System.out.println("Sound system is turned off.");
    }

    public void setVolume(int level) {
        System.out.println("Sound system volume set to " + level);
    }
}
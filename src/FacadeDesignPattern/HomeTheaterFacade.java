package FacadeDesignPattern;

public class HomeTheaterFacade {
    private Projector projector;
    private SoundSystem soundSystem;
    private DvdPlayer dvdPlayer;
    private Lights lights;

    public HomeTheaterFacade(Projector projector, SoundSystem soundSystem, DvdPlayer dvdPlayer, Lights lights) {
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.dvdPlayer = dvdPlayer;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(20);
        projector.on();
        projector.setInput("DVD");
        soundSystem.on();
        soundSystem.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        dvdPlayer.stop();
        dvdPlayer.off();
        soundSystem.off();
        projector.off();
        lights.dim(100);
    }
}

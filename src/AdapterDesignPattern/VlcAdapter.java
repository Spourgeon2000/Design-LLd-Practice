package AdapterDesignPattern;

public class VlcAdapter implements MediaPlayer {
    private VlcPlayer vlcPlayer;

    public VlcAdapter() {
        vlcPlayer = new VlcPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVlc(fileName);
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}
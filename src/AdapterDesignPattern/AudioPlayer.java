package AdapterDesignPattern;

public class AudioPlayer implements MediaPlayer {
    private MediaPlayer vlcAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            vlcAdapter = new VlcAdapter();
            vlcAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private static Clip clip;
    private static FloatControl volumeControl;// Variable that controls the volume of the audio instance
    private static boolean isSoundPlaying = false;

    public static void playSound(String fileName, float volume) {

        if (isSoundPlaying && clip != null && clip.isRunning()) {
            clip.stop();
            clip.close(); // Release resources
        }

        try {
            File soundFile = new File("resources/" + fileName); // Load from resources folder
            if (!soundFile.exists()) {
                System.out.println("Sound file not found: " + soundFile.getAbsolutePath()); // Debugging
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Set volume to a lower level (e.g., -20dB)
            volumeControl.setValue(volume);  // Adjust this value for lower/higher volume. (0.0 is max volume)
            clip.start();
            isSoundPlaying = true;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }



    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
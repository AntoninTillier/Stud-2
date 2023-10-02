import java.io.*;
import javax.sound.sampled.*;

public class Sound extends Thread {
    File wav;
    AudioInputStream audioInputStream = null;
    SourceDataLine line;

    public Sound(File f) {
        wav = f;
    }

    public void run() {
        try {
            AudioFileFormat format = AudioSystem.getAudioFileFormat(wav);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            audioInputStream = AudioSystem.getAudioInputStream(wav);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioFormat audioFormat = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return;
        }
        try {
            line.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
        line.start();
        try {
            byte bytes[] = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
                line.write(bytes, 0, bytesRead);
            }
        } catch (IOException io) {
            io.printStackTrace();
            return;
        }
    }

    public void stopS() {
        line.close();
    }
}
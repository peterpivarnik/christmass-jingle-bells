package com.psw.jingle;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ChristmasJingleBells {

    private static final String JINGLE_BELLS_SOUND_LOCATION = "/jingle-bells.wav";
    private static final int BEEP_DURATION_SHORT = 10000;

    public static void main(String[] attrs) {
        ChristmasJingleBells christmasJingleBells = new ChristmasJingleBells();
        christmasJingleBells.ringBells();
    }

    private void ringBells() {
        try {
            sleep(Duration.ofMinutes(30));
            URL soundURL = this.getClass().getResource(JINGLE_BELLS_SOUND_LOCATION);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundURL);
            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(inputStream);
            clip.start();
            sleep(Duration.ofMillis(BEEP_DURATION_SHORT));
            clip.stop();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void sleep(Duration timeToSleep) {
        try {
            Thread.sleep(timeToSleep.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


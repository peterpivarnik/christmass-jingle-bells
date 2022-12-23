package com.psw.jingle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class ChristmasJingleBells {

  public static final long WAITING_TIME = Duration.ofMinutes(15).toMillis();
  public static final long SOUND_DURATION = 10000L;
  private static final String JINGLE_BELLS_FILE = "/jingle-bells.wav";

  public static void main(String[] attrs) {
    ChristmasJingleBells christmasJingleBells = new ChristmasJingleBells();
    christmasJingleBells.ringBells();
  }

  private void ringBells() {
    try {
      Thread.sleep(WAITING_TIME);
      URL soundUrl = getSoundUrl();
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundUrl);
      DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
      playSound(inputStream, info);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void playSound(AudioInputStream inputStream, DataLine.Info info)
      throws LineUnavailableException, IOException, InterruptedException {
    Clip clip = (Clip) AudioSystem.getLine(info);
    clip.open(inputStream);
    clip.start();
    Thread.sleep(SOUND_DURATION);
    clip.stop();
  }

  private URL getSoundUrl() throws FileNotFoundException {
    URL soundUrl = this.getClass().getResource(JINGLE_BELLS_FILE);
    if (soundUrl == null) {
      throw new FileNotFoundException("File " + JINGLE_BELLS_FILE + "not found");
    }
    return soundUrl;
  }
}


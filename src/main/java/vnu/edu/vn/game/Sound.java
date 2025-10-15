package vnu.edu.vn.game;

import java.io.File;
import javafx.scene.media.AudioClip;

public class Sound {
    private String name;
    private String path;
    private AudioClip audioClip;
    private File file;

    public Sound() {
    }

    public Sound(String name, String path, File file, AudioClip audioClip) {
        this.name = name;
        this.path = path;
        this.file = file;
        this.audioClip = audioClip;
    }

    public Sound(Sound other) {
        this.name = other.name;
        this.path = other.path;
        this.file = other.file;
        this.audioClip = other.audioClip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public AudioClip getAudioClip() {
        return audioClip;
    }

    public void setAudioClip(AudioClip audioClip) {
        this.audioClip = audioClip;
    }

    public void play() {
        System.out.println("Playing sound: " + name + " from path: " + path);
        AudioClip audioClip = new AudioClip(file.toURI().toString());
        audioClip.stop();
        audioClip.play();
        // Implement actual sound playing logic here
    }
}

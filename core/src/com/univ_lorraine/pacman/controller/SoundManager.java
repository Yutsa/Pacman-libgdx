package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * @author Édouard WILLISSECK
 */

public class SoundManager {
    private static SoundManager instance;

    private Music eatingPelletSound = Gdx.audio.newMusic(
            Gdx.files.internal("sounds/eating.mp3")
    );

    private Sound eatingGhostSound = Gdx.audio.newSound(
            Gdx.files.internal("sounds/pacman_eatghost.ogg")
    );

    private Sound deadPacmanSound = Gdx.audio.newSound(
            Gdx.files.internal("sounds/pacman_death.ogg")
    );

    private Sound beginningMusic = Gdx.audio.newSound(
            Gdx.files.internal("sounds/pacman_beginning.ogg")
    );

    private SoundManager() {
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public Music getEatingPelletSound() {
        return eatingPelletSound;
    }

    public Sound getEatingGhostSound() {
        return eatingGhostSound;
    }

    public Sound getDeadPacmanSound() {
        return deadPacmanSound;
    }

    public Sound getBeginningMusic() {
        return beginningMusic;
    }
}

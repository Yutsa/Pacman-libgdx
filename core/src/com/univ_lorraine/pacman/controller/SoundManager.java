package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * @author Édouard WILLISSECK
 */

public class SoundManager {
    private static SoundManager instance;
    private Music eatingPelletSound = Gdx.audio.newMusic(
            Gdx.files.internal("sounds/eating.mp3")
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
}

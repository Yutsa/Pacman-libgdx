package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.univ_lorraine.pacman.controller.MovementController;

/**
 * The class representing Pacman
 */

@SuppressWarnings("SameParameterValue")
public class Pacman extends MovableGameElement {
    private Music pacmanSound = Gdx.audio.newMusic(
            Gdx.files.internal("sounds/eating.mp3")
    );
    /**
     * Calls the GameElement constructor to create the Pacman instance.
     *
     * @param position Position of Pacman.
     * @param world    World of Pacman.
     */
    public Pacman(Vector2D position, World world, int speed, MovementController movementController) {
        super(position, world, speed);
    }

    public Music getPacmanSound() {
        return pacmanSound;
    }
}
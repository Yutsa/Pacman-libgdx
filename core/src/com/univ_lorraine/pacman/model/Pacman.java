package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.MovementController;

/**
 * The class representing Pacman
 */

@SuppressWarnings("SameParameterValue")
public class Pacman extends MovableGameElement {
    private float deadCounter = 0;
    /**
     * Calls the GameElement constructor to create the Pacman instance.
     *
     * @param position Position of Pacman.
     * @param world    World of Pacman.
     */
    public Pacman(Vector2D position, World world, int speed, MovementController movementController) {
        super(position, world, speed);
    }

    public float getDeadCounter() {
        return deadCounter;
    }

    public void setDeadCounter(float deadCounter) {
        this.deadCounter = deadCounter;
    }

    public void decreaseDeadCounter(float deltaTime) {
        deadCounter -= deltaTime;
        if (deadCounter < 0) {
            deadCounter = 0;
        }
    }
}
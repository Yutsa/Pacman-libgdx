package com.univ_lorraine.pacman.model;

/**
 * The class representing Pacman
 */

public class Pacman extends MovableGameElement {

    /**
     * Calls the GameElement constructor to create the Pacman instance.
     *
     * @param position Position of Pacman.
     * @param world    World of Pacman.
     */
    public Pacman(Vector2D position, World world, int speed) {
        super(position, world, speed);
    }
}

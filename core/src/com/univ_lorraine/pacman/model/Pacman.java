package com.univ_lorraine.pacman.model;

import java.awt.Point;

/**
 * The class representing Pacman
 */

public class Pacman extends GameElement {

    /**
     * Calls the GameElement constructor to create the Pacman instance.
     * @param position Position of Pacman.
     * @param wolrd World of Pacman.
     */
    public Pacman(Point position, World wolrd)
    {
        super(position, wolrd);
    }

    /**
     * Updates the mPosition of pacman.
     * @param move The quantity of movement.
     */
    public void update(double move) {

    }
}

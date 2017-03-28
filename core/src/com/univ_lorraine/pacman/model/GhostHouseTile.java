package com.univ_lorraine.pacman.model;

/**
 * @author Édouard WILLISSECK
 */

public class GhostHouseTile extends MazeElement {
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected GhostHouseTile(Vector2D position, World world) {
        super(position, world);
    }
}

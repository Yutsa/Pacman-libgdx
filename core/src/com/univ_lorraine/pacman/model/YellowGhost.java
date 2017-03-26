package com.univ_lorraine.pacman.model;

/**
 * @author Édouard WILLISSECK
 */

@SuppressWarnings("SameParameterValue")
public class YellowGhost extends Ghost {
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected YellowGhost(Vector2D position, World world, int speed) {
        super(position, world, speed);
    }
}

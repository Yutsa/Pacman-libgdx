package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;

/**
 * @author Édouard WILLISSECK
 */

@SuppressWarnings("SameParameterValue")
public class PinkGhost extends Ghost {
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *  @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected PinkGhost(Vector2D position, World world, int speed, GhostAI ai) {
        super(position, world, speed, ai);
    }
}

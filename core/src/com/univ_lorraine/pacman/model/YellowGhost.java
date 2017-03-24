package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;

/**
 * @author Édouard WILLISSECK
 */

public class YellowGhost extends Ghost {
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *  @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected YellowGhost(Vector2D position, World world, int speed, GhostAI ai) {
        super(position, world, speed, ai);
    }
}

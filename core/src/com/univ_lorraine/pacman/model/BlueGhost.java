package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;
import com.univ_lorraine.pacman.controller.MovementController;

/**
 * @author Édouard WILLISSECK
 */

public class BlueGhost extends Ghost {
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     * @param movementController
     */
    protected BlueGhost(Vector2D position, World world, int speed, GhostAI ai) {
        super(position, world, speed, ai);
    }
}

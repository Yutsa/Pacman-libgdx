package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.MovableGameElement;

/**
 * The basic class for Ghosts' AI.
 * @author Édouard WILLISSECK
 */

public abstract class GhostAI {
    protected MovementController mMovementController;
    protected Ghost mGhost;

    public GhostAI(Ghost ghost) {
        mGhost = ghost;
        mMovementController = ghost.getMovementController();
    }

    public void setMovementController(MovementController movementController) {
        if (movementController == null) {
            throw new IllegalArgumentException("Movement controller can't be null");
        }
        mMovementController = movementController;
    }
    
    public abstract void setDirection(MovableGameElement movableGameElement);
}

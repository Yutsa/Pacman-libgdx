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

    public GhostAI(MovementController movementController) {
        setMovementControler(movementController);
    }

    public void setMovementControler(MovementController movementController) {
        if (movementController == null) {
            throw new IllegalArgumentException("World renderer can't be null");
        }
        mMovementController = movementController;
    }

    public Ghost getGhost() {
        return mGhost;
    }

    public void setGhost(Ghost ghost) {
        if (ghost == null) {
            throw new IllegalArgumentException("Ghost can't be null");
        }
        mGhost = ghost;
    }

    public abstract void setDirection(MovableGameElement movableGameElement);

    // TODO: 25/03/17 Make a run away mode for the AI or create a run away AI.
}

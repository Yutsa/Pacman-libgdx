package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.MovableGameElement;

/**
 * The basic class for Ghosts' AI.
 * @author Édouard WILLISSECK
 */

public abstract class GhostAI {
    private WorldRenderer mWorldRenderer;

    public GhostAI(WorldRenderer worldRenderer) {
        setWorldRenderer(worldRenderer);
    }

    public void setWorldRenderer(WorldRenderer worldRenderer) {
        if (worldRenderer == null) {
            throw new IllegalArgumentException("World renderer can't be null");
        }
        mWorldRenderer = worldRenderer;
    }

    abstract void setDirection(MovableGameElement movableGameElement);
}
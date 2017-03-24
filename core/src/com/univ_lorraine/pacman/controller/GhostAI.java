package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.MovableGameElement;

/**
 * The basic class for Ghosts' AI.
 * @author Édouard WILLISSECK
 */

public abstract class GhostAI {
    protected WorldRenderer mWorldRenderer;
    protected Ghost mGhost;

    public GhostAI(WorldRenderer worldRenderer) {
        setWorldRenderer(worldRenderer);
    }

    public void setWorldRenderer(WorldRenderer worldRenderer) {
        if (worldRenderer == null) {
            throw new IllegalArgumentException("World renderer can't be null");
        }
        mWorldRenderer = worldRenderer;
    }

    public WorldRenderer getWorldRenderer() {
        return mWorldRenderer;
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
}

package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.MovableGameElement;

/**
 * @author Édouard WILLISSECK
 */

public class RandomAI extends GhostAI {
    public RandomAI(WorldRenderer worldRenderer) {
        super(worldRenderer);
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        if (mWorldRenderer.isAtIntersection(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection())) {
            movableGameElement.setWantedDirection(MovableGameElement.Direction.randomDirection());
        }
    }
}

package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.Vector2D;

/**
 * @author Édouard WILLISSECK
 */

public class RandomAI extends GhostAI {
    public RandomAI(WorldRenderer worldRenderer) {
        super(worldRenderer);
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        Vector2D position = movableGameElement.getPosition();
        if (mWorldRenderer.isAtIntersection(position,
                movableGameElement.getCurrentDirection())) {
            if (position.getX() % 100 == 0 && position.getY() % 100 == 0) {
                movableGameElement.setWantedDirection(MovableGameElement.Direction.randomDirection());
            }
        }
    }
}

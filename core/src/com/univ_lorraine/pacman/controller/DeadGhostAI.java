package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;
import com.univ_lorraine.pacman.model.Vector2D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Édouard WILLISSECK
 */

public class DeadGhostAI extends GhostAI {
    public DeadGhostAI(Ghost ghost) {
        super(ghost);
    }

    // TODO: 26/03/17 Can't enter the house for now.
    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        Gdx.app.log(getClass().getSimpleName(), "Going home");
        if (mGhost.getMovementController().isOnSameTile(movableGameElement.getPosition(),
                mGhost.getStartingPos())) {
            mGhost.setAlive(true);
            mGhost.switchToOutAI();
        }
        if (!mGhost.isAlive()) {
            HashMap<Direction, GameElement> availableDirections;
            Vector2D position = movableGameElement.getPosition();

            double min = Double.MAX_VALUE;
            Direction resDirection = null;

            availableDirections = mMovementController.getPossibleDirections(movableGameElement.getPosition(),
                    movableGameElement.getCurrentDirection());


            for (Map.Entry<Direction, GameElement> entry : availableDirections.entrySet()) {
                double distance = mMovementController.getDistance(entry.getValue().getPosition(),
                        mGhost.getStartingPos());
                if (distance < min) {
                    resDirection = entry.getKey();
                    min = distance;
                }
            }

            if (resDirection == null) {
                throw new RuntimeException("No valid direction");
            }

            if (position.getX() % 100 == 0 && position.getY() % 100 == 0) {
                movableGameElement.setWantedDirection(resDirection);
            }
        }
    }
}
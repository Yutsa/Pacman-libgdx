package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.Vector2D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Édouard WILLISSECK
 */

public class SearchPacmanAI extends GhostAI {
    public SearchPacmanAI(MovementController movementController) {
        super(movementController);
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        if (mMovementController.isAtIntersection(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection())) {
            HashMap<Direction, GameElement> availableDirections;
            Pacman pacman = mMovementController.getWorld().getPacman();
            Vector2D position = movableGameElement.getPosition();

            double min = Double.MAX_VALUE;
            Direction resDirection = null;

            availableDirections = mMovementController.getPossibleDirections(movableGameElement.getPosition(),
                    movableGameElement.getCurrentDirection());


            for (Map.Entry<Direction, GameElement> entry : availableDirections.entrySet()) {
                double distance = mMovementController.getDistance(entry.getValue().getPosition(),
                        pacman.getPosition());
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

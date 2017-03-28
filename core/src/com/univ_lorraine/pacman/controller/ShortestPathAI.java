package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.MazeElement;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.Vector2D;

import java.util.Map;

import static com.univ_lorraine.pacman.model.MovableGameElement.Direction;

/**
 * @author Édouard WILLISSECK
 */

public class ShortestPathAI extends GhostAI {
    private Vector2D goal;
    public ShortestPathAI(Ghost ghost) {
        super(ghost);
    }

    public Vector2D getGoal() {
        return goal;
    }

    public void setGoal(Vector2D goal) {
        this.goal = goal;
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        if (mMovementController.isAtIntersection(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection())) {
            mMovementController.findShortestPath(goal);
            Map.Entry<Direction, MazeElement> result = mMovementController.getBlockWithSmallestLabel(
                    movableGameElement.getPosition(),
                    movableGameElement.getCurrentDirection()
            );
            movableGameElement.setWantedDirection(result.getKey());
        }
    }
}

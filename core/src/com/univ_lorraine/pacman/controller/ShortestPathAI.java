package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.MazeElement;
import com.univ_lorraine.pacman.model.MovableGameElement;

import java.util.Map;

import static com.univ_lorraine.pacman.model.MovableGameElement.Direction;

/**
 * @author Édouard WILLISSECK
 */

public class ShortestPathAI extends GhostAI {
    private GameElement goal;
    public ShortestPathAI(Ghost ghost) {
        super(ghost);
    }

    public ShortestPathAI(Ghost ghost, GameElement goal) {
        super(ghost);
        setGoal(goal);
    }

    public GameElement getGoal() {
        return goal;
    }

    public void setGoal(GameElement goal) {
        this.goal = goal;
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        if (mMovementController.isAtIntersection(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection())) {
            mMovementController.findShortestPath(goal.getPosition());
            Map.Entry<Direction, MazeElement> result = mMovementController.getBlockWithSmallestLabel(
                    movableGameElement.getPosition(),
                    movableGameElement.getCurrentDirection()
            );
            movableGameElement.setWantedDirection(result.getKey());
        }
    }
}

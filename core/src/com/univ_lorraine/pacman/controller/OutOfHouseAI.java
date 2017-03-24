package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;

/**
 * @author Édouard WILLISSECK
 */

public class OutOfHouseAI extends GhostAI {
    public OutOfHouseAI(WorldRenderer worldRenderer) {
        super(worldRenderer);
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        if (mWorldRenderer.getElementAtPosition(movableGameElement) instanceof GhostHouseTile) {
            movableGameElement.setWantedDirection(Direction.UP);
        }
        else {
            switch (mGhost.getColor()) {
                case RED:
                    mGhost.setAi(new RandomAI(mWorldRenderer));
                    break;
                case YELLOW:
                    mGhost.setAi(new SearchPacmanAI(mWorldRenderer));
            }
        }
    }
}

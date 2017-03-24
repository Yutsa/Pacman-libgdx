package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;
import com.univ_lorraine.pacman.model.PinkGhost;
import com.univ_lorraine.pacman.model.RedGhost;
import com.univ_lorraine.pacman.model.YellowGhost;

/**
 * @author Édouard WILLISSECK
 */

public class OutOfHouseAI extends GhostAI {
    public OutOfHouseAI(MovementController movementController) {
        super(movementController);
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        if (mMovementController.getElementAtPosition(movableGameElement) instanceof GhostHouseTile) {
            movableGameElement.setWantedDirection(Direction.UP);
        }
        else {
            if (movableGameElement instanceof RedGhost) {
                ((RedGhost) movableGameElement).setAi(new RandomAI(mMovementController));
                ((RedGhost) movableGameElement).getAi().setGhost((Ghost) movableGameElement);

            }
            else if (movableGameElement instanceof YellowGhost) {
                ((YellowGhost) movableGameElement).setAi(new SearchPacmanAI(mMovementController));
                ((YellowGhost) movableGameElement).getAi().setGhost((Ghost) movableGameElement);
            }
            else if (movableGameElement instanceof PinkGhost) {
                ((PinkGhost) movableGameElement).setAi(new SwitchAI(mMovementController));
                ((PinkGhost) movableGameElement).getAi().setGhost((Ghost) movableGameElement);
            }
        }
    }
}

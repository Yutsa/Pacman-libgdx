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
    public OutOfHouseAI(WorldRenderer worldRenderer) {
        super(worldRenderer);
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        if (mWorldRenderer.getElementAtPosition(movableGameElement) instanceof GhostHouseTile) {
            movableGameElement.setWantedDirection(Direction.UP);
        }
        else {
            if (movableGameElement instanceof RedGhost) {
                ((RedGhost) movableGameElement).setAi(new RandomAI(mWorldRenderer));
                ((RedGhost) movableGameElement).getAi().setGhost((Ghost) movableGameElement);

            }
            else if (movableGameElement instanceof YellowGhost) {
                ((YellowGhost) movableGameElement).setAi(new SearchPacmanAI(mWorldRenderer));
                ((YellowGhost) movableGameElement).getAi().setGhost((Ghost) movableGameElement);
            }
            else if (movableGameElement instanceof PinkGhost) {
                ((PinkGhost) movableGameElement).setAi(new SwitchAI(mWorldRenderer));
                ((PinkGhost) movableGameElement).getAi().setGhost((Ghost) movableGameElement);
            }
        }
    }
}

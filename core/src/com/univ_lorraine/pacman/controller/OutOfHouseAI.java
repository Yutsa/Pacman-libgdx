package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;

/**
 * @author Édouard WILLISSECK
 */

// FIXME: 26/03/17 When coming from the left the red ghost is locked in the house
public class OutOfHouseAI extends GhostAI {
    public OutOfHouseAI(Ghost ghost) {
        super(ghost);
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        Gdx.app.log(getClass().getSimpleName(), "Current = " + movableGameElement.getCurrentDirection());
        Gdx.app.log(getClass().getSimpleName(), "Wanted = " + movableGameElement.getWantedDirection());
        if (mMovementController.getElementAtPosition(movableGameElement) instanceof GhostHouseTile) {
            movableGameElement.setWantedDirection(Direction.UP);
        }
        else {
            ((Ghost) movableGameElement).switchToDefaultAI();
        }
    }
}

package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.World;

import static com.univ_lorraine.pacman.model.MovableGameElement.Direction.DOWN;

/**
 * @author Édouard WILLISSECK
 */

public class GhostMoveController extends MovementController {
    public GhostMoveController(World world) {
        super(world);
        // TODO: 25/03/17 Don't use the static speed.
        epsilon = (world.getGhosts().get(0).getSpeed() / 6000f);
    }

    @Override
    public void moveElement(MovableGameElement movableGameElement, float deltaTime) {
        checkTunnel(movableGameElement);

        checkWantedDirection(movableGameElement, movableGameElement.getWantedDirection());

        GameElement nextBlock = getNextElement(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection());

        if (!(nextBlock instanceof Block) &&
                !((movableGameElement.getCurrentDirection() == DOWN)
                        && (nextBlock instanceof GhostHouseTile))) {
            movableGameElement.updatePosition(deltaTime);
        }

        fixPosition(movableGameElement);
    }
}

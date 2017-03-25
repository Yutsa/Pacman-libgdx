package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.BasicPellet;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.Maze;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.SuperPellet;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;

/**
 * @author Édouard WILLISSECK
 */

public class PacmanMoveController extends MovementController {
    public PacmanMoveController(World world) {
        super(world);
        epsilon = (Pacman.mSpeed / 6000f);
    }

    /**
     * Moves the pacman on the {@link Maze}
     *
     * @param deltaTime The time elapsed between two renders.
     */
    @Override
    public void moveElement(MovableGameElement movableGameElement, float deltaTime) {
        checkTunnel(movableGameElement);

        checkWantedDirection(movableGameElement, movableGameElement.getWantedDirection());

        GameElement nextBlock = getNextElement(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection());

        if (!(nextBlock instanceof Block) && !(nextBlock instanceof GhostHouseTile)) {
            movableGameElement.updatePosition(deltaTime);
        }

        fixPosition(movableGameElement);

        Vector2D currentPosition = movableGameElement.getPosition();
        GameElement currentGameElement = mWorld.getMaze().getBlock(
                (currentPosition.getX() + 50) / mCoef,
                (currentPosition.getY() + 50) / mCoef);

        if (currentGameElement instanceof BasicPellet
                || currentGameElement instanceof SuperPellet) {
            eatPellet(currentGameElement);
        }

    }
}

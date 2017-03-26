package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.univ_lorraine.pacman.model.BasicPellet;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.Maze;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.SuperPellet;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;

import static com.univ_lorraine.pacman.model.MovableGameElement.Direction.RIGHT;

/**
 * @author Édouard WILLISSECK
 */

public class PacmanMoveController extends MovementController {
    public PacmanMoveController(World world) {
        super(world);
        epsilon = (world.getPacman().getSpeed() / 6000f);
    }

    /**
     * Moves the pacman on the {@link Maze}
     *
     * @param deltaTime The time elapsed between two renders.
     */
    @Override
    public void moveElement(MovableGameElement movableGameElement, float deltaTime) {
        checkPacmanGhostCollision();
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

    /**
     * Checks if pacman and a Ghost are on the same tile and does what must be done.
     */
    public void checkPacmanGhostCollision() {
        Vector2D pacmanPosition = mWorld.getPacman().getPosition();
        for (Ghost ghost : mWorld.getGhosts()) {
            if (isOnSameTile(pacmanPosition, ghost.getPosition())) {
                resolveCollision(ghost);
            }
        }
    }

    public void resolveCollision(Ghost ghost) {
        if (Ghost.isFrightened()) {
            Gdx.app.log(getClass().getSimpleName(), "Pacman eats ghost");
        }
        else {
            World.decreaseLifeCounter();
            mWorld.getPacman().setPosition(new Vector2D(World.getPacmanStartingPosition()));
            mWorld.getPacman().setCurrentDirection(RIGHT);
        }
    }
}

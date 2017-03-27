package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.audio.Music;
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

        playEatingSound(nextBlock);
    }

    public void playEatingSound(GameElement nextBlock) {
        Music pacmanSound = SoundManager.getInstance().getEatingPelletSound();
        if (nextBlock instanceof BasicPellet
                || nextBlock instanceof SuperPellet) {

            if (!pacmanSound.isLooping()) {
                pacmanSound.setLooping(true);
                pacmanSound.play();
            }
        }
        else {
            pacmanSound.setLooping(false);
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
        if (ghost.isFrightened()) {
            SoundManager.getInstance().getEatingGhostSound().play();
            ghost.setFrightenedTimer(0);
            ghost.switchToDeadAI();
            ghost.setAlive(false);
            mWorld.winPoint(500);
        }
        else if (ghost.isAlive()){
            SoundManager.getInstance().getDeadPacmanSound().play();
            World.decreaseLifeCounter();
            mWorld.getPacman().resetPosition();
            mWorld.getPacman().setDeadCounter(2);
            mWorld.getPacman().setCurrentDirection(RIGHT);
            for (Ghost ghost2 : mWorld.getGhosts()) {
                ghost2.resetPosition();
                ghost2.setAlive(true);
                ghost2.switchToOutAI();
            }
        }
    }

    public void updateEpsilon(float deltaTime) {
        float fps = 1 / deltaTime;
        float newEpsilon = mWorld.getPacman().getSpeed() / (fps * World.getCoef());
        if (newEpsilon < 0.5) {
            epsilon = newEpsilon;
        }
        else {
            epsilon = 0.45;
        }
    }
}

package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.Gdx;
import com.univ_lorraine.pacman.controller.MovementController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Édouard WILLISSECK
 */

public class MovableGameElement extends GameElement {
    /**
     * An enum for the directions.
     */
    public enum Direction {
        LEFT, UP, RIGHT, DOWN;

        private static final List<Direction> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Direction randomDirection()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    /**
     * The direction the player wants pacman to go next.
     */
    private Direction mWantedDirection;

    /**
     * The direction in which the pacman is oriented/going.
     */
    private Direction mCurrentDirection;

    /**
     * The speed of the element.
     */
    public int mSpeed;

    protected MovementController mMovementController;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected MovableGameElement(Vector2D position, World world, int speed) {
        super(position, world);
        mCurrentDirection = Direction.RIGHT;
        mWantedDirection = Direction.RIGHT;
        setSpeed(speed);
    }

    /**
     * Gets the pacman's current direction.
     *
     * @return The current Direction of pacman.
     */
    public Direction getCurrentDirection() {
        return mCurrentDirection;
    }

    /**
     * Sets the pacman's current direction.
     *
     * @param currentDirection The Direction to set.
     */
    public void setCurrentDirection(Direction currentDirection) {
        mCurrentDirection = currentDirection;
    }

    /**
     * Gets the pacman's wanted direction.
     *
     * @return The wanted Direction of pacman.
     */
    public Direction getWantedDirection() {

        return mWantedDirection;
    }

    /**
     * Sets the pacman's wanted direction.
     *
     * @param wantedDirection The Direction to set.
     */
    public void setWantedDirection(Direction wantedDirection) {
        mWantedDirection = wantedDirection;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int speed) {
        if (speed <= 0) {
            throw new IllegalArgumentException("The speed must be positive.");
        }
        mSpeed = speed;
        if (mMovementController != null) {
            mMovementController.setEpsilon(speed / 6000f);
            Gdx.app.log(getClass().getSimpleName(), "Epsilon = " + mMovementController.getEpsilon());
        }
    }

    public MovementController getMovementController() {
        return mMovementController;
    }

    public void setMovementController(MovementController movementController) {
        mMovementController = movementController;
    }

    /**
     * Updates the mPosition of pacman.
     */
    public void updatePosition(float deltaTime) {
        switch (mCurrentDirection) {
            case LEFT:
                mPosition.x -= (mSpeed * deltaTime);
                break;
            case RIGHT:
                mPosition.x += (mSpeed * deltaTime);
                break;
            case UP:
                mPosition.y -= (mSpeed * deltaTime);
                break;
            case DOWN:
                mPosition.y += (mSpeed * deltaTime);
                break;
        }
    }

    public void move(float deltaTime) {
        if (mMovementController == null) {
            throw new RuntimeException("No movement controller has been set.");
        }
        mMovementController.moveElement(this, deltaTime);
    }
}

package com.univ_lorraine.pacman.model;

/**
 * @author Édouard WILLISSECK
 */

public class MovableGameElement extends GameElement {
    /**
     * The direction the player wants pacman to go next.
     */
    private Direction mWantedDirection;

    /**
     * An enum for the directions.
     */
    public enum Direction {LEFT, UP, RIGHT, DOWN}

    /**
     * The direction in which the pacman is oriented/going.
     */
    private Direction mCurrentDirection;

    /**
     * The speed of the element.
     */
    public int mSpeed;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *
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
}

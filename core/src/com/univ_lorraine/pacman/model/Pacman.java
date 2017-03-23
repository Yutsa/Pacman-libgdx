package com.univ_lorraine.pacman.model;

/**
 * The class representing Pacman
 */

public class Pacman extends GameElement {
    public enum Direction {LEFT, UP, RIGHT, DOWN}

    /**
     * The direction in which the pacman is oriented/going.
     */
    private Direction mCurrentDirection;
    /**
     * The direction the player wants pacman to go next.
     */
    private Direction mWantedDirection;
    /**
     * The speed of the pacman.
     */
    public static final int mSpeed = 500;

    /**
     * Calls the GameElement constructor to create the Pacman instance.
     *
     * @param position Position of Pacman.
     * @param wolrd    World of Pacman.
     */
    public Pacman(Vector2D position, World wolrd) {
        super(position, wolrd);
        mCurrentDirection = Direction.RIGHT;
        mWantedDirection = Direction.RIGHT;
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

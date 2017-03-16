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
    private Direction mWantedDirection;
    public static final int mSpeed = 500;
    /**
     * Calls the GameElement constructor to create the Pacman instance.
     * @param position Position of Pacman.
     * @param wolrd World of Pacman.
     */
    public Pacman(Vector2D position, World wolrd)
    {
        super(position, wolrd);
        mCurrentDirection = Direction.RIGHT;
        mWantedDirection = Direction.RIGHT;
    }

    public Direction getCurrentDirection() {
        return mCurrentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        mCurrentDirection = currentDirection;
    }

    public static int getSpeed() {
        return mSpeed;
    }

    public Direction getWantedDirection() {

        return mWantedDirection;
    }

    public void setWantedDirection(Direction wantedDirection) {
        mWantedDirection = wantedDirection;
    }

    /**
     * Updates the mPosition of pacman.
     */
    public void updatePosition(float deltaTime) {
        switch (mCurrentDirection)
        {
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

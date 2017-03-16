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
    public static final int mSpeed = 10;
    /**
     * Calls the GameElement constructor to create the Pacman instance.
     * @param position Position of Pacman.
     * @param wolrd World of Pacman.
     */
    public Pacman(Vector2D position, World wolrd)
    {
        super(position, wolrd);
        mCurrentDirection = Direction.RIGHT;
    }

    public Direction getCurrentDirection() {
        return mCurrentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        mCurrentDirection = currentDirection;
    }

    /**
     * Updates the mPosition of pacman.
     */
    public void updatePosition() {
        switch (mCurrentDirection)
        {
            case LEFT:
                mPosition.x -= mSpeed;
                break;
            case RIGHT:
                mPosition.x += mSpeed;
                break;
            case UP:
                mPosition.y -= mSpeed;
                break;
            case DOWN:
                mPosition.y += mSpeed;
                break;
        }
    }
}

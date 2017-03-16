package com.univ_lorraine.pacman.model;

/**
 * The class representing Pacman
 */

public class Pacman extends GameElement {
    public enum Direction {LEFT, UP, RIGHT, DOWN};

    /**
     * The direction in which the pacman is oriented/going.
     */
    private Direction mDirection;
    public static final int mSpeed = 10;
    /**
     * Calls the GameElement constructor to create the Pacman instance.
     * @param position Position of Pacman.
     * @param wolrd World of Pacman.
     */
    public Pacman(Vector2D position, World wolrd)
    {
        super(position, wolrd);
        mDirection = Direction.RIGHT;
    }

    public Direction getDirection() {
        return mDirection;
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }

    /**
     * Updates the mPosition of pacman.
     */
    public void updatePosition(Direction direction) {
        switch (mDirection)
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

package com.univ_lorraine.pacman.model;

/**
 * The class representing Pacman
 */

public class Pacman extends MoveableGameElement {
    /**
     * The direction the player wants pacman to go next.
     */
    private Direction mWantedDirection;

    /**
     * Calls the GameElement constructor to create the Pacman instance.
     *
     * @param position Position of Pacman.
     * @param world    World of Pacman.
     */
    public Pacman(Vector2D position, World world, int speed) {
        super(position, world);
        mWantedDirection = Direction.RIGHT;
        setSpeed(speed);
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
}

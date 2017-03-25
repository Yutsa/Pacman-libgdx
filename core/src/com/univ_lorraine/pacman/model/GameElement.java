package com.univ_lorraine.pacman.model;

/**
 * Base class for the game elements such as pacman, the blocks or the ghosts.
 */
public abstract class GameElement {
    /**
     * The mPosition of the GameElement on the map.
     */
    protected Vector2D mPosition;

    /**
     * The World in which the element is.
     */
    @SuppressWarnings("unused")
    protected World mWorld;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected GameElement(Vector2D position, World world) {
        setPosition(position);
        setWorld(world);
    }

    /**
     * Returns the mPosition of the element.
     *
     * @return Vector2 The mPosition of the element.
     */
    public Vector2D getPosition() {
        return mPosition;
    }

    /**
     * Sets the mPosition of the element.
     *
     * @param position The mPosition of the element.
     */
    public void setPosition(Vector2D position) {
        if (position == null)
            throw new IllegalArgumentException("Position cannot be null");
        this.mPosition = position;
    }

    /**
     * Sets the mWorld of the element.
     *
     * @param world The mWorld of the element.
     */
    public void setWorld(World world) {
        if (world == null)
            throw new IllegalArgumentException("World cannot be null");
        this.mWorld = world;
    }
}

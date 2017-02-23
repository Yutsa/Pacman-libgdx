package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Base class for the game elements such as pacman, the blocks or the ghosts.
 */
public abstract class GameElement {

    public enum textureID {
        BLOCK, PACMAN_RIGHT
    }

    /**
     * The mPosition of the GameElement on the map.
     */
    protected Vector2 mPosition;
    /**
     * The World in which the element is.
     */
    protected World mWorld;

    protected textureID mTextureID;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world The mWorld of the element.
     * @param textureID
     */
    protected GameElement(Vector2 position, World world, textureID textureID) {
        setPosition(position);
        setWorld(world);
        setTextureID(textureID);
    }

    /**
     * Returns the mPosition of the element.
     * @return Vector2 The mPosition of the element.
     */
    public Vector2 getPosition() {
        return mPosition;
    }


    /**
     * Sets the mPosition of the element.
     * @param position The mPosition of the element.
     */
    public void setPosition(Vector2 position) {
        if (position == null)
            throw new IllegalArgumentException("Position cannot be null");
        this.mPosition = position;
    }

    /**
     * Sets the mWorld of the element.
     * @param world The mWorld of the element.
     */
    public void setWorld(World world) {
        if (world == null)
            throw new IllegalArgumentException("World cannot be null");
        this.mWorld = world;
    }

    public void setTextureID(textureID textureID) {
        if (textureID == null) {
            throw new IllegalArgumentException("Must contain a textureID");
        }
        mTextureID = textureID;
    }

    public textureID getTextureID() {
        return mTextureID;
    }
}

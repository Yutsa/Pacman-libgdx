package com.univ_lorraine.pacman.model;

/**
 * @author Édouard WILLISSECK
 */

public class Ghost extends MovableGameElement {
    public enum Color {RED, BLUE, PINK, YELLOW}

    /**
     * The color of the ghost.
     */
    private Color mColor;
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected Ghost(Vector2D position, World world, int speed, Color color) {
        super(position, world, speed);
        mColor = color;
    }

    public Color getColor() {
        return mColor;
    }
}

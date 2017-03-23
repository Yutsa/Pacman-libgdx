package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;

/**
 * @author Édouard WILLISSECK
 */

public class Ghost extends MovableGameElement {
    public enum Color {RED, BLUE, PINK, YELLOW}

    /**
     * The color of the ghost.
     */
    private Color mColor;
    private GhostAI ai;
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected Ghost(Vector2D position, World world, int speed, Color color, GhostAI ai) {
        super(position, world, speed);
        mColor = color;
    }

    public Color getColor() {
        return mColor;
    }

    public GhostAI getAi() {
        return ai;
    }

    public void setAi(GhostAI ai) {
        if (ai == null) {
            throw new IllegalArgumentException("The AI can't be null");
        }
        this.ai = ai;
    }
}

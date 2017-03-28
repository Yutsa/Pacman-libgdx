package com.univ_lorraine.pacman.model;

/**
 * @author Édouard WILLISSECK
 */

public class MazeElement extends GameElement {
    private int shortestPathLabel;
    /**
     * Creates a GameElement with a mPosition and a mWorld.
     *
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected MazeElement(Vector2D position, World world) {
        super(position, world);
    }

    public int getShortestPathLabel() {
        return shortestPathLabel;
    }

    public void setShortestPathLabel(int shortestPathLabel) {
        this.shortestPathLabel = shortestPathLabel;
    }
}

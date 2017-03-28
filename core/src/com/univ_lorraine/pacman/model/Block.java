package com.univ_lorraine.pacman.model;

/**
 * The class representing a block in the pacman game.
 */

public class Block extends MazeElement {

    /**
     * Creates a block at a given mPosition in a given mWorld.
     *
     * @param position The mPosition of the block.
     * @param world    The mWorld of the block.
     */
    public Block(Vector2D position, World world) {
        super(position, world);
    }


}

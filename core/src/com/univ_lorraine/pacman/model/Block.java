package com.univ_lorraine.pacman.model;

import java.awt.Point;

/**
 * The class representing a block in the pacman game.
 */

public class Block extends GameElement {

    /**
     * Creates a block at a given mPosition in a given mWorld.
     * @param position The mPosition of the block.
     * @param world The mWorld of the block.
     */
    public Block(Point position, World world) {
        super(position, world);
    }


}

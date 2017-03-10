package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.math.Vector2;

/**
 * The class representing a block in the pacman game.
 */

public class Block extends GameElement {

    /**
     * Creates a block at a given mPosition in a given mWorld.
     * @param position The mPosition of the block.
     * @param world The mWorld of the block.
     */
    public Block(Vector2 position, World world) {
        super(position, world);
    }


}

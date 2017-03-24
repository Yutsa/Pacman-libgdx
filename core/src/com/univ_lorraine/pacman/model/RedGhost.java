package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;

/**
 * @author Édouard WILLISSECK
 */

public class RedGhost extends Ghost {
    public RedGhost(Vector2D position, World world, int speed, Color color, GhostAI ai) {
        super(position, world, speed, color, ai);
    }

    @Override
    public void useAI() {

    }
}

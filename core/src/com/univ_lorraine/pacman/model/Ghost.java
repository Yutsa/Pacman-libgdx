package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;

/**
 * @author Édouard WILLISSECK
 */

public abstract class Ghost extends MovableGameElement {
    /**
     * The color of the ghost.
     */
    private GhostAI ai = null;
    private static float mFrightenedTimer = 0;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected Ghost(Vector2D position, World world, int speed, GhostAI ai) {
        super(position, world, speed);
        setAi(ai);
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

    public void useAI() {
        ai.setDirection(this);
    }

    public static void setFrightenedTimer(float frightenedTimer) {
        mFrightenedTimer = frightenedTimer;
    }

    public static void decreaseFrightenedTimer(float time) {
        mFrightenedTimer -= time;
        if (mFrightenedTimer < 0) {
            mFrightenedTimer = 0;
        }
    }

    public static float getFrightenedTimer() {
        return mFrightenedTimer;
    }
}

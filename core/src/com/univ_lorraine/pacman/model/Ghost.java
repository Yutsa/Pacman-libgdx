package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;
import com.univ_lorraine.pacman.controller.MovementController;
import com.univ_lorraine.pacman.controller.RandomAI;

/**
 * @author Édouard WILLISSECK
 */

public abstract class Ghost extends MovableGameElement {
    /**
     * The color of the ghost.
     */
    private GhostAI ai = null;
    private GhostAI frightenedAI = null;
    private static float mFrightenedTimer = 0;
    private int frightenedSpeed = 300;
    private int normalSpeed;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected Ghost(Vector2D position, World world, int speed, GhostAI ai) {
        super(position, world, speed);
        setAi(ai);
        frightenedAI = new RandomAI();
        normalSpeed = speed;
    }

    @Override
    public void setMovementController(MovementController movementController) {
        super.setMovementController(movementController);
        frightenedAI.setGhost(this);
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
        if (mFrightenedTimer > 0) {
            if (mSpeed != frightenedSpeed) {
                setSpeed(frightenedSpeed);
            }
            frightenedAI.setDirection(this);
        }
        else {
            if (mSpeed != normalSpeed) {
                setSpeed(normalSpeed);
            }
            ai.setDirection(this);
        }
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

package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.GhostAI;
import com.univ_lorraine.pacman.controller.OutOfHouseAI;
import com.univ_lorraine.pacman.controller.RandomAI;

/**
 * @author Édouard WILLISSECK
 */

public abstract class Ghost extends MovableGameElement {
    /**
     * The color of the ghost.
     */
    private GhostAI usedAI = null;
    private GhostAI defaultAI = null;
    private GhostAI frightenedAI = null;
    private GhostAI outOfHouseAI = null;
    private static float mFrightenedTimer = 0;
    private int frightenedSpeed = 300;
    private int normalSpeed;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected Ghost(Vector2D position, World world, int speed) {
        super(position, world, speed);
        normalSpeed = speed;
    }

    public void initAI(GhostAI defaultAI) {
        this.defaultAI = defaultAI;
        frightenedAI = new RandomAI(this);
        outOfHouseAI = new OutOfHouseAI(this);
        usedAI = outOfHouseAI;
    }

    public void switchToFrightenedAI() {
        usedAI = frightenedAI;
    }

    public void switchToOutAI() {
        usedAI = outOfHouseAI;
    }

    public void switchToDefaultAI() {
        usedAI = defaultAI;
    }

    public GhostAI getUsedAI() {
        return usedAI;
    }

    public void setUsedAI(GhostAI usedAI) {
        if (usedAI == null) {
            throw new IllegalArgumentException("The AI can't be null");
        }
        this.usedAI = usedAI;
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
            usedAI.setDirection(this);
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

    public static boolean isFrightened() {
        return mFrightenedTimer > 0;
    }
}

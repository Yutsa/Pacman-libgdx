package com.univ_lorraine.pacman.model;

import com.univ_lorraine.pacman.controller.DeadGhostAI;
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
    private GhostAI deadAI = null;
    private boolean alive = true;
    private float mFrightenedTimer = 0;
    private int frightenedSpeed = 100;
    private int normalSpeed;
    private Vector2D startingPos;

    /**
     * Creates a GameElement with a mPosition and a mWorld.
     * @param position The mPosition of the element.
     * @param world    The mWorld of the element.
     */
    protected Ghost(Vector2D position, World world, int speed) {
        super(position, world, speed);
        normalSpeed = speed;
        startingPos = new Vector2D(position);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Vector2D getStartingPos() {
        return startingPos;
    }

    public void initAI(GhostAI defaultAI) {
        this.defaultAI = defaultAI;
        frightenedAI = new RandomAI(this);
        outOfHouseAI = new OutOfHouseAI(this);
        deadAI = new DeadGhostAI(this);
        usedAI = outOfHouseAI;
    }

    public void switchToDeadAI() {
        usedAI = deadAI;
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

    public GhostAI getDefaultAI() {
        return defaultAI;
    }

    public void useAI() {
        usedAI.setDirection(this);
    }

    public void setFrightenedTimer(float frightenedTimer) {
        mFrightenedTimer = frightenedTimer;
        if (frightenedTimer > 0) {
            switchToFrightenedAI();
            setSpeed(frightenedSpeed);
        }
        else if (frightenedTimer == 0) {
            setSpeed(normalSpeed);
        }
    }

    public void decreaseFrightenedTimer(float time) {
        mFrightenedTimer -= time;
        if (mFrightenedTimer < 0) {
            mFrightenedTimer = 0;
            switchToDefaultAI();
            setSpeed(normalSpeed);
        }
    }

    public float getFrightenedTimer() {
        return mFrightenedTimer;
    }

    public boolean isFrightened() {
        return mFrightenedTimer > 0;
    }
}

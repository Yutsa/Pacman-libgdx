package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Pacman;

/**
 * @author Édouard WILLISSECK
 */

class PacmanTextureWrapper extends TextureWrapper {
    /**
     * The texture for the pacman up and open.
     */
    private Texture pacmanUpOpen;
    /**
     * The texture for the pacman right and open.
     */
    private Texture pacmanRightOpen;
    /**
     * The texture for the pacman down and open.
     */
    private Texture pacmanDownOpen;
    /**
     * The texture for the pacman left and open.
     */
    private Texture pacmanLeftOpen;
    /**
     * The texture for the pacman up and closed.
     */
    private Texture pacmanUpClosed;
    /**
     * The texture for the pacman right and closed.
     */
    private Texture pacmanRightClosed;
    /**
     * The texture for the pacman down and closed.
     */
    private Texture pacmanDownClosed;
    /**
     * The texture for the pacman left and closed.
     */
    private Texture pacmanLeftClosed;
    /**
     * The counter for the open/close animation.
     */
    private int animationCounter = 0;
    /**
     * The time limit to change the sprite, and do the animation.
     */
    private int animationLimit;

    /**
     * Creates the wrapper.
     * @param wrappedObject The object to be wrapped.
     */
    public PacmanTextureWrapper(GameElement wrappedObject) {
        super(wrappedObject);

        pacmanUpClosed = new Texture("pacmanUp.png");
        pacmanLeftClosed = new Texture("pacmanLeft.png");
        pacmanDownClosed = new Texture("pacmanDown.png");
        pacmanRightClosed = new Texture("pacmanRight.png");
        pacmanUpOpen = new Texture("pacmanUp-2.png");
        pacmanLeftOpen = new Texture("pacmanLeft-2.png");
        pacmanDownOpen = new Texture("pacmanDown-2.png");
        pacmanRightOpen = new Texture("pacmanRight-2.png");
    }

    @Override
    public void setWrappedObject(GameElement wrappedObject) {
        super.setWrappedObject(wrappedObject);

        if (!(wrappedObject instanceof Pacman)) {
            throw new IllegalArgumentException("PacmanTextureWrapper's wrapped object should" +
                    " be a pacman.");
        }

        animationLimit = (1000 / Pacman.mSpeed) * 5;
        Gdx.app.log(PacmanTextureWrapper.class.getSimpleName(), "Animation limit = "
                + animationLimit);
    }

    @Override
    public Texture getTexture() {
        if (animationCounter < 2 * animationLimit) {
            ++animationCounter;
        } else {
            animationCounter = 0;
        }
        Pacman pacman = (Pacman) getWrappedObject();
        switch (pacman.getCurrentDirection()) {
            case UP:
                if (animationCounter < animationLimit) {
                    return pacmanDownClosed;
                }
                else {
                    return pacmanDownOpen;
                }
            case RIGHT:
                if (animationCounter < animationLimit) {
                    return pacmanRightClosed;
                } else {
                    return  pacmanRightOpen;
                }
            case DOWN:
                if (animationCounter < animationLimit) {
                    return pacmanUpClosed;
                } else {
                    return pacmanUpOpen;
                }
            case LEFT:
                if (animationCounter < animationLimit) {
                    return pacmanLeftClosed;
                } else {
                    return  pacmanLeftOpen;
                }
            default:
                throw new RuntimeException("Direction inconnue.");
        }

    }
}

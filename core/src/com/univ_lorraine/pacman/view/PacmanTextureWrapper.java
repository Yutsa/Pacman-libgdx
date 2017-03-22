package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Pacman;

/**
 * @author Édouard WILLISSECK
 */

class PacmanTextureWrapper extends TextureWrapper {
    private Texture pacmanUpOpen;
    private Texture pacmanRightOpen;
    private Texture pacmanDownOpen;
    private Texture pacmanLeftOpen;
    private Texture pacmanUpClosed;
    private Texture pacmanRightClosed;
    private Texture pacmanDownClosed;
    private Texture pacmanLeftClosed;
    private int animationCounter = 0;

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
    }

    @Override
    public Texture getTexture() {
        if (animationCounter < 20) {
            ++animationCounter;
        } else {
            animationCounter = 0;
        }
        Pacman pacman = (Pacman) getWrappedObject();
        switch (pacman.getCurrentDirection()) {
            case UP:
                if (animationCounter < 10) {
                    return pacmanDownClosed;
                }
                else {
                    return pacmanDownOpen;
                }
            case RIGHT:
                if (animationCounter < 10) {
                    return pacmanRightClosed;
                } else {
                    return  pacmanRightOpen;
                }
            case DOWN:
                if (animationCounter < 10) {
                    return pacmanUpClosed;
                } else {
                    return pacmanUpOpen;
                }
            case LEFT:
                if (animationCounter < 10) {
                    return pacmanLeftClosed;
                } else {
                    return  pacmanLeftOpen;
                }
            default:
                throw new RuntimeException("Direction inconnue.");
        }

    }
}

package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Pacman;

/**
 * @author Édouard WILLISSECK
 */

public class PacmanTextureWrapper extends TextureWrapper {

    private final Texture[] UpTextures = new Texture[4];

    private final Texture[] DownTextures = new Texture[4];

    private final Texture[] LeftTextures = new Texture[4];

    private final Texture[] RightTextures = new Texture[4];

    private float time = 0;
    private int state = 0;

    /**
     * Creates the wrapper.
     */
    public PacmanTextureWrapper() {
        super();
        Texture pacmanUpClosed = new Texture("pacmanUp.png");
        Texture pacmanLeftClosed = new Texture("pacmanLeft.png");
        Texture pacmanDownClosed = new Texture("pacmanDown.png");
        Texture pacmanRightClosed = new Texture("pacmanRight.png");
        Texture pacmanUpOpen = new Texture("pacmanUp-2.png");
        Texture pacmanLeftOpen = new Texture("pacmanLeft-2.png");
        Texture pacmanDownOpen = new Texture("pacmanDown-2.png");
        Texture pacmanRightOpen = new Texture("pacmanRight-2.png");
        Texture pacmanClosed = new Texture("pacman-3.png");

        UpTextures[0] = pacmanClosed;
        UpTextures[1] = pacmanUpClosed;
        UpTextures[2] = pacmanUpOpen;
        UpTextures[3] = pacmanUpClosed;

        DownTextures[0] = pacmanClosed;
        DownTextures[1] = pacmanDownClosed;
        DownTextures[2] = pacmanDownOpen;
        DownTextures[3] = pacmanDownClosed;

        LeftTextures[0] = pacmanClosed;
        LeftTextures[1] = pacmanLeftClosed;
        LeftTextures[2] = pacmanLeftOpen;
        LeftTextures[3] = pacmanLeftClosed;

        RightTextures[0] = pacmanClosed;
        RightTextures[1] = pacmanRightClosed;
        RightTextures[2] = pacmanRightOpen;
        RightTextures[3] = pacmanRightClosed;

    }

    public void update(float deltaTime) {
        time += deltaTime;
        state = (int) (time * (getPacman().getSpeed() / 50)) % 4;
    }

    public Pacman getPacman() {
        if (getWrappedObject() instanceof Pacman) {
            return (Pacman) getWrappedObject();
        }
        throw new RuntimeException("The wrapped object wasn't a pacman : " +getWrappedObject());
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
        Pacman pacman = (Pacman) getWrappedObject();
        switch (pacman.getCurrentDirection()) {
            case UP:
                return UpTextures[state];
            case RIGHT:
                return RightTextures[state];
            case DOWN:
                return DownTextures[state];
            case LEFT:
                return LeftTextures[state];
            default:
                throw new RuntimeException("Direction inconnue.");
        }

    }
}

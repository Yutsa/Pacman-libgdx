package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;

/**
 * @author Édouard WILLISSECK
 */

public class GhostTextureWrapper extends TextureWrapper {
    public Texture normalTexture;
    public Texture frightenedTexture = new Texture("ghostEscaping.png");
    public Texture deadTexture = new Texture("ghostDead.png");
    public float frightenedTimer = 0;

    /**
     * Creates the wrapper
     *
     */
    public GhostTextureWrapper(Texture defaultTexture) {
        super();
        setNormalTexture(defaultTexture);
    }

    public void setNormalTexture(Texture normalTexture) {
        if (normalTexture == null) {
            throw new IllegalArgumentException("NULL texture");
        }
        this.normalTexture = normalTexture;
    }

    public void setFrightenedTimer(float frightenedTimer) {
        this.frightenedTimer = frightenedTimer;
    }

    public void update(float deltaTime) {
        if (Ghost.getFrightenedTimer() > 0) {
            Ghost.decreaseFrightenedTimer(deltaTime);
            Gdx.app.log(getClass().getSimpleName(), "Timer = " + Ghost.getFrightenedTimer());
        }
    }

    @Override
    public void setWrappedObject(GameElement wrappedObject) {
        super.setWrappedObject(wrappedObject);

        if (!(wrappedObject instanceof Ghost)) {
            throw new IllegalArgumentException("GhostTextureWrapper's wrapped object should" +
                    " be a ghost.");
        }
    }

    @Override
    public Texture getTexture() {
        if (Ghost.getFrightenedTimer() > 0) {
            return frightenedTexture;
        }
        return normalTexture;
    }
}

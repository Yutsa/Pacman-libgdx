package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * @author Édouard WILLISSECK
 */

public class DebugMazeElementWrapper extends TextureWrapper {
    BitmapFont font = new BitmapFont(true);
    public DebugMazeElementWrapper() {
        super();

    }

    @Override
    public Texture getTexture() {
        return null;
    }
}

package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Édouard WILLISSECK
 *         The abstract class that wrapps an object to get the right texture.
 */

public interface ITexturable {


    /**
     * Returns the right texture for the wrapped GameElement.
     *
     * @return The right texture for the wrapped GameElement.
     */
     Texture getTexture();
}

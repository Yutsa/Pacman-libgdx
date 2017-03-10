package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;


/**
 * The factory singleton that will return the textures needed.
 */
public class TextureFactory {

    public enum TextureInfo {
        DEFAULT_TEXTURE, PACMAN_RIGHT, PACMAN_LEFT, PACMAN_UP, PACMAN_DOWN
    }
    /**
     * An instance of the TextureFactory
     */
    private static TextureFactory instance;

    /**
     * The simple texture of the pacman
     */
    private static Texture pacmanTexture;

    /**
     * The texture for the blocs
     */
    private static Texture blocTexture;

    /**
     * Constructor that loads the textures.
     */
    private TextureFactory(){
        pacmanTexture = new Texture("pacmanRight.png");
        blocTexture = new Texture("bloc.png");
    }

    /**
     * Checks if an instance of the TextureFactory already exists, if it does
     * it returns it, otherwise it creates the TextureFactory.
     * @return TextureFactory The instance of the TextureFactory.
     */
    public static TextureFactory getInstance() {
        if (instance == null)
        {
            instance = new TextureFactory();
        }
        return instance;
    }

    /**
     * Returns the simple pacman texture.
     * @return Texture The pacman texture.
     */
    public Texture getTexturePacman() {
        return pacmanTexture;
    }

    /**
     * Returns the block texture.
     * @return Texture The block's texture.
     */
    public Texture getTextureBloc() {
        return blocTexture;
    }

    public Texture getTexture(GameElement element) {
        switch (element.getTextureID()) {
            case PACMAN_RIGHT:
                return pacmanTexture;
            case BLOCK:
                return blocTexture;
            default:
                throw new IllegalArgumentException("The texture id of the element " +
                        "is not recognized");
        }
    }
}

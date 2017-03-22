package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.EmptyTile;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Pacman;

import java.util.HashMap;
import java.util.Map;


/**
 * The factory singleton that will return the textures needed.
 */
public class TextureFactory {
    /**
     * A map that associates a GameElement to its texture.
     */
    private Map<Class<?>, TextureWrapper> mTextureMap;
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

    private static Texture emptyTexture;

    /**
     * Constructor that loads the textures.
     */
    private TextureFactory(){
        pacmanTexture = new Texture("pacmanRight.png");
        blocTexture = new Texture("bloc.png");
        emptyTexture = new Texture("dark.png");
        mTextureMap = new HashMap<Class<?>, TextureWrapper>();
        mTextureMap.put(Pacman.class, new PacmanTextureWrapper(null));
        mTextureMap.put(Block.class, new DefaultTextureWrapper(null, blocTexture));
        mTextureMap.put(EmptyTile.class, new DefaultTextureWrapper(null, emptyTexture));
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

    public Texture getTexture(GameElement element) {
        TextureWrapper textureWrapper = mTextureMap.get(element.getClass());
        if (textureWrapper.getWrappedObject() == null) {
            textureWrapper.setWrappedObject(element);
        }
        return textureWrapper.getTexture();
    }
}

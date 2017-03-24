package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.BasicPellet;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.EmptyTile;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.GhostHouseTile;
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
     * The texture for the blocs
     */
    private static Texture blocTexture;
    /**
     * The texture for the empty blocks
     */
    private static Texture emptyTexture;
    /**
     * The texture for the basic pellets
     */
    private static Texture basicPelletTexture;

    /**
     * Constructor that loads the textures.
     */
    private TextureFactory() {
        blocTexture = new Texture("bloc.png");
        emptyTexture = new Texture("dark.png");
        basicPelletTexture = new Texture("pellet.png");
        mTextureMap = new HashMap<Class<?>, TextureWrapper>();
        mTextureMap.put(Pacman.class, new PacmanTextureWrapper(null));
        mTextureMap.put(Ghost.class, new GhostTextureWrapper(null));
        mTextureMap.put(Block.class, new DefaultTextureWrapper(null, blocTexture));
        mTextureMap.put(EmptyTile.class, new DefaultTextureWrapper(null, emptyTexture));
        mTextureMap.put(BasicPellet.class, new DefaultTextureWrapper(null, basicPelletTexture));
        mTextureMap.put(GhostHouseTile.class, new DefaultTextureWrapper(null, emptyTexture));
    }

    /**
     * Checks if an instance of the TextureFactory already exists, if it does
     * it returns it, otherwise it creates the TextureFactory.
     *
     * @return TextureFactory The instance of the TextureFactory.
     */
    public static TextureFactory getInstance() {
        if (instance == null) {
            instance = new TextureFactory();
        }
        return instance;
    }

    /**
     * Gets the Texture for the given GameElement.
     * @param element The GameElement to get the Texture.
     * @return The Texture for the given GameElement.
     */
    public Texture getTexture(GameElement element) {
        TextureWrapper textureWrapper = mTextureMap.get(element.getClass());
        textureWrapper.setWrappedObject(element);
        return textureWrapper.getTexture();
    }
}

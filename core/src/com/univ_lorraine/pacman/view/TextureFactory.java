package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.BasicPellet;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.BlueGhost;
import com.univ_lorraine.pacman.model.EmptyTile;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.PinkGhost;
import com.univ_lorraine.pacman.model.RedGhost;
import com.univ_lorraine.pacman.model.SuperPellet;
import com.univ_lorraine.pacman.model.YellowGhost;

import java.util.HashMap;
import java.util.Map;


/**
 * The factory singleton that will return the textures needed.
 */
public class TextureFactory {
    /**
     * A map that associates a GameElement to its texture.
     */
    private final Map<Class<?>, ITexturable> mTextureMap;
    /**
     * An instance of the TextureFactory
     */
    private static TextureFactory instance;

    private Texture livesTexture = new Texture("pacmanRight.png");

    /**
     * Constructor that loads the textures.
     */
    private TextureFactory() {
        Texture blocTexture = new Texture("bloc.png");
        Texture emptyTexture = new Texture("dark.png");
        Texture basicPelletTexture = new Texture("pellet.png");
        Texture redGhost = new Texture("ghost1.png");
        Texture pinkGhost = new Texture("ghost2.png");
        Texture blueGhost = new Texture("ghost3.png");
        Texture yellowGhost = new Texture("ghost4.png");
        Texture superPellet = new Texture("superpellet.png");
        mTextureMap = new HashMap<Class<?>, ITexturable>();
        mTextureMap.put(Pacman.class, new PacmanTextureWrapper());
        mTextureMap.put(RedGhost.class, new GhostTextureWrapper(redGhost));
        mTextureMap.put(PinkGhost.class, new GhostTextureWrapper(pinkGhost));
        mTextureMap.put(BlueGhost.class, new GhostTextureWrapper(blueGhost));
        mTextureMap.put(YellowGhost.class, new GhostTextureWrapper(yellowGhost));

        mTextureMap.put(Block.class, new DefaultTextureWrapper(blocTexture));
        mTextureMap.put(EmptyTile.class, new DefaultTextureWrapper(emptyTexture));
        mTextureMap.put(BasicPellet.class, new DefaultTextureWrapper(basicPelletTexture));
        mTextureMap.put(GhostHouseTile.class, new DefaultTextureWrapper(emptyTexture));

        mTextureMap.put(SuperPellet.class, new SuperPelletTextureWrapper());
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

    public void update(float deltaTime) {
        ((PacmanTextureWrapper) mTextureMap.get(Pacman.class)).update(deltaTime);
        ((GhostTextureWrapper) mTextureMap.get(PinkGhost.class)).update(deltaTime);
        ((GhostTextureWrapper) mTextureMap.get(RedGhost.class)).update(deltaTime);
        ((GhostTextureWrapper) mTextureMap.get(YellowGhost.class)).update(deltaTime);
        ((GhostTextureWrapper) mTextureMap.get(BlueGhost.class)).update(deltaTime);
        ((SuperPelletTextureWrapper) mTextureMap.get(SuperPellet.class)).update(deltaTime);
    }

    /**
     * Gets the Texture for the given GameElement.
     *
     * @param element The GameElement to get the Texture.
     * @return The Texture for the given GameElement.
     */
    public Texture getTexture(GameElement element) {
        ITexturable iTexturable = mTextureMap.get(element.getClass());

        if (iTexturable instanceof TextureWrapper) {
            TextureWrapper textureWrapper = (TextureWrapper) iTexturable;
            if (textureWrapper.getWrappedObject() == null) {
                textureWrapper.setWrappedObject(element);
            }
        }

        return iTexturable.getTexture();
    }

    public Texture getLivesTexture() {
        return livesTexture;
    }

    public void resetTextureFactory() {
        for (ITexturable texturable : mTextureMap.values()) {
            if (texturable instanceof TextureWrapper) {
                ((TextureWrapper) texturable).resetWrapper();
            }
        }
    }
}

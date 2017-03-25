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
    private Map<Class<?>, ITexturable> mTextureMap;
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

    private static Texture redGhost;
    private static Texture blueGhost;
    private static Texture yellowGhost;
    private static Texture pinkGhost;

    private float time = 0;
    private int state = 0;

    /**
     * Constructor that loads the textures.
     */
    private TextureFactory() {
        blocTexture = new Texture("bloc.png");
        emptyTexture = new Texture("dark.png");
        basicPelletTexture = new Texture("pellet.png");
        redGhost = new Texture("ghost1.png");
        pinkGhost = new Texture("ghost2.png");
        blueGhost = new Texture("ghost3.png");
        yellowGhost = new Texture("ghost4.png");
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

    // TODO: 25/03/17 Should change the pacman texture according to the time using update.
    public void update(float deltaTime) {
        time += deltaTime;
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
}

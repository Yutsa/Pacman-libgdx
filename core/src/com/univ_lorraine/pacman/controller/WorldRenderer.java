package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.World;
import com.univ_lorraine.pacman.view.TextureFactory;

/**
 * @author Édouard WILLISSECK
 */

public class WorldRenderer {
    private SpriteBatch batch;
    private TextureFactory textureFactory;
    private World mWorld;

    public WorldRenderer(World world)
    {
        textureFactory = TextureFactory.getInstance();
        batch = new SpriteBatch();
        mWorld = world;
    }

    public void render() {
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Iterates through the maze and renders the block */
        for (GameElement e : mWorld) {
            Vector2 position = e.getPosition();
            batch.draw(textureFactory.getTexture(e),
                    position.x * 48,
                    position.y * 48, 12, 12);
        }
        batch.end();
    }
}

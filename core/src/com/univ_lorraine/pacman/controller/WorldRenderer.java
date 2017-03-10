package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;
import com.univ_lorraine.pacman.view.TextureFactory;

/**
 * @author Édouard WILLISSECK
 */

public class WorldRenderer {
    private SpriteBatch batch;
    private TextureFactory textureFactory;
    private World mWorld;

    public double getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    float size;

    public WorldRenderer(World world) {
        textureFactory = TextureFactory.getInstance();
        batch = new SpriteBatch();
        mWorld = world;
    }

    public void render(OrthographicCamera camera) {
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        /* Iterates through the maze and renders the block */
        for (GameElement e : mWorld) {
            Vector2D position = e.getPosition();
            batch.draw(textureFactory.getTexture(e),
                    (position.x - mWorld.getWidth() / 2f) * size,
                    (position.y - mWorld.getHeight() / 2f) * size, size, size);
        }
        batch.end();
    }
}

package com.univ_lorraine.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.univ_lorraine.pacman.controller.WorldRenderer;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.World;
import com.univ_lorraine.pacman.view.TextureFactory;

/**
 * @author Édouard WILLISSECK
 */

public class GameScreen implements Screen {
    private World mWorld;
    private WorldRenderer mWorldRenderer;
    private SpriteBatch batch;
    private TextureFactory textureFactory;

    public GameScreen()
    {
        mWorld = new World();
        mWorldRenderer = new WorldRenderer();
        textureFactory = TextureFactory.getInstance();
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Iterates through the maze and renders the block */
        for (GameElement e : mWorld) {
            Vector2 position = e.getPosition();
            batch.draw(textureFactory.getTexture(e),
                    position.x * 48,
                    position.y * 48);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

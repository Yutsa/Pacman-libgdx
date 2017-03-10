package com.univ_lorraine.pacman.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.univ_lorraine.pacman.controller.WorldRenderer;
import com.univ_lorraine.pacman.model.World;

/**
 * @author Édouard WILLISSECK
 */

public class GameScreen implements Screen {
    private World mWorld;
    private WorldRenderer mWorldRenderer;
    private OrthographicCamera mCamera;


    public GameScreen()
    {
        mWorld = new World();
        mWorldRenderer = new WorldRenderer(mWorld);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mWorldRenderer.render();
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

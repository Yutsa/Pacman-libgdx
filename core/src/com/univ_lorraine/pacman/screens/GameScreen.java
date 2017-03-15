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
        mCamera = new OrthographicCamera();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mWorldRenderer.render(mCamera);
    }

    @Override
    public void resize(int width, int height) {
        mCamera.setToOrtho(true, width, height);
        mWorldRenderer.setSize(Math.min((float) width / mWorld.getWidth(),
                (float)height / mWorld.getHeight()));
        mCamera.position.set(0, 0, 0);
        mCamera.update();
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

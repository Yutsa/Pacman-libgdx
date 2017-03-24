package com.univ_lorraine.pacman.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.univ_lorraine.pacman.controller.MovementController;
import com.univ_lorraine.pacman.controller.WorldRenderer;
import com.univ_lorraine.pacman.model.World;

/**
 * @author Édouard WILLISSECK
 */

public class GameScreen implements Screen {
    private World mWorld;
    private WorldRenderer mWorldRenderer;
    private MovementController mMovementController;
    private OrthographicCamera mCamera;
    private BitmapFont font;
    private SpriteBatch batch;
    private Game mGame;


    public GameScreen(Game game)
    {
        mWorld = new World();
        mMovementController = new MovementController(mWorld);
        mWorldRenderer = new WorldRenderer(mWorld, game, mMovementController);
        mWorld.setMovementController(mMovementController);
        mWorld.createGhosts();
        mCamera = new OrthographicCamera();
        mGame = game;
    }

    public WorldRenderer getWorldRenderer() {
        return mWorldRenderer;
    }

    @Override
    public void show() {
        font = new BitmapFont(false);
        font.setColor(Color.GOLD);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        mWorldRenderer.render(mCamera, delta);
        batch.begin();
        font.draw(batch, "Score: " + mWorld.getScore(), 10, 20);
        batch.end();
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

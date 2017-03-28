package com.univ_lorraine.pacman.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

/**
 * @author Édouard WILLISSECK
 */

public class LoadingScreen implements Screen, InputProcessor {
    private final int mViewportWidth;
    private final int mViewportHeight;
    private BitmapFont font;
    private SpriteBatch batch;
    private Game mGame;
    private OrthographicCamera mCamera;

    public LoadingScreen(Game game) {
        mGame = game;
        Gdx.input.setInputProcessor(this);
        mViewportWidth = 300;
        mViewportHeight = 200;
        createCamera();
    }

    public void createCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        mCamera = new OrthographicCamera(mViewportWidth, mViewportHeight * (h / w));
        mCamera.setToOrtho(false, mViewportWidth, mViewportHeight);
        mCamera.update();
    }

    @Override
    public void show() {
        font = new BitmapFont(false);
        font.setColor(Color.GOLD);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderWelcomeInstructions(batch);
        batch.end();
    }

    public void renderWelcomeInstructions(SpriteBatch batch) {
        String text = "PACMAN";
        text = text + "\nAppuyez sur n'importe quelle touche pour commencer.";
        font.draw(batch, text, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0,
                Align.center, false);
    }

    @Override
    public void resize(int width, int height) {
        mCamera.viewportHeight = mViewportHeight * (width / height);
        mCamera.position.set(mCamera.viewportWidth / 2, mCamera.viewportHeight / 2, 0);
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

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log(getClass().getSimpleName(), "Pressed a key");
        GameScreen screen = new GameScreen(mGame);
        mGame.setScreen(screen);
        screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

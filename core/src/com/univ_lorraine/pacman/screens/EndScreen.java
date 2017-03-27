package com.univ_lorraine.pacman.screens;

import com.badlogic.gdx.Gdx;
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

public class EndScreen implements Screen {
    private BitmapFont font;
    private SpriteBatch batch;
    private boolean won;
    private OrthographicCamera mCamera;
    private int score;

    public EndScreen(boolean won, int score) {
        this.won = won;
        setScore(score);
        init();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void init() {
        mCamera = new OrthographicCamera(5, 5);
        mCamera.position.set(0, 0, 0);
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
        String text;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (won) {
            text = "Vous avez gagné !";
        }
        else {
            text = "Vous avez perdu !";
        }
        text = text + "\nScore = " + score;
        font.draw(batch, text, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0,
                Align.center, false);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        mCamera.viewportWidth = (5 / height) *
                width;
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

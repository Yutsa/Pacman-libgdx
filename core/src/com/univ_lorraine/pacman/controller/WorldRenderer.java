package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.univ_lorraine.pacman.model.EmptyTile;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;
import com.univ_lorraine.pacman.view.TextureFactory;

/**
 * @author Édouard WILLISSECK
 */

public class WorldRenderer implements InputProcessor {
    float size;
    private SpriteBatch batch;
    private TextureFactory textureFactory;
    private World mWorld;

    public WorldRenderer(World world) {
        textureFactory = TextureFactory.getInstance();
        batch = new SpriteBatch();
        mWorld = world;
        Gdx.input.setInputProcessor(this);
    }

    public double getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
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
                    ((position.x / 100) - mWorld.getWidth() / 2f) * size,
                    ((position.y / 100) - mWorld.getHeight() / 2f) * size, size, size);
        }
        batch.end();
        if (mWorld.getPacman().getDirection() != null)
            movePacman();
        //Gdx.app.log(WorldRenderer.class.getName(), mWorld.getPacman().getPosition().toString());
    }

    public void movePacman() {
        Pacman pacman = mWorld.getPacman();
        Vector2D newPosition;
        switch (pacman.getDirection()) {
            case LEFT:
                newPosition = new Vector2D(pacman.getPosition().x - Pacman.mSpeed,
                        pacman.getPosition().y);
                if (mWorld.getMaze().getBlock((newPosition.x / 100),
                        (newPosition.y / 100)) instanceof EmptyTile)
                    pacman.setPosition(newPosition);
                break;
            case RIGHT:
                newPosition = new Vector2D(pacman.getPosition().x + Pacman.mSpeed,
                        pacman.getPosition().y);
                if (mWorld.getMaze().getBlock((newPosition.x / 100),
                        (newPosition.y / 100)) instanceof EmptyTile)
                    pacman.setPosition(newPosition);
                break;
            case UP:
                newPosition = new Vector2D(pacman.getPosition().x,
                        pacman.getPosition().y - Pacman.mSpeed);
                if (mWorld.getMaze().getBlock((newPosition.x / 100),
                        (newPosition.y / 100)) instanceof EmptyTile)
                    pacman.setPosition(newPosition);
                break;
            case DOWN:
                newPosition = new Vector2D(pacman.getPosition().x,
                        pacman.getPosition().y + Pacman.mSpeed);
                if (mWorld.getMaze().getBlock((newPosition.x / 100),
                        (newPosition.y / 100)) instanceof EmptyTile)
                    pacman.setPosition(newPosition);
                break;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Pacman pacman = mWorld.getPacman();
        switch (keycode) {
            case Input.Keys.LEFT:
                pacman.setDirection(Pacman.Direction.LEFT);
                Gdx.app.log(WorldRenderer.class.getName(), "LEFT");
                break;
            case Input.Keys.RIGHT:
                pacman.setDirection(Pacman.Direction.RIGHT);
                Gdx.app.log(WorldRenderer.class.getName(), "RIGHT");
                break;
            case Input.Keys.UP:
                pacman.setDirection(Pacman.Direction.UP);
                Gdx.app.log(WorldRenderer.class.getName(), "UP");
                break;
            case Input.Keys.DOWN:
                pacman.setDirection(Pacman.Direction.DOWN);
                Gdx.app.log(WorldRenderer.class.getName(), "DOWN");
                break;
        }

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

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
                    ((position.x / 100f) - mWorld.getWidth() / 2f) * size,
                    ((position.y / 100f) - mWorld.getHeight() / 2f) * size, size, size);
        }
        batch.end();
        movePacman();
    }

    public void movePacman() {
        Pacman pacman = mWorld.getPacman();
        switch (pacman.getWantedDirection()) {
            case LEFT:
                if (mWorld.getMaze().getBlock((int) Math.ceil((pacman.getPosition().x / 100f) - 1),
                        (pacman.getPosition().y / 100)) instanceof EmptyTile)
                {
                    pacman.setCurrentDirection(pacman.getWantedDirection());
                }
                break;
            case RIGHT:
                if (mWorld.getMaze().getBlock((pacman.getPosition().x / 100) + 1,
                        (pacman.getPosition().y / 100)) instanceof EmptyTile)
                {
                    pacman.setCurrentDirection(pacman.getWantedDirection());
                }
                break;
            case UP:
                if (mWorld.getMaze().getBlock((pacman.getPosition().x / 100),
                        (int) Math.ceil((pacman.getPosition().y / 100f)) - 1)
                        instanceof EmptyTile)
                {
                    pacman.setCurrentDirection(pacman.getWantedDirection());
                }
                break;
            case DOWN:
                if (mWorld.getMaze().getBlock((pacman.getPosition().x / 100),
                        (pacman.getPosition().y / 100) +1 ) instanceof EmptyTile)
                {
                    pacman.setCurrentDirection(pacman.getWantedDirection());
                }
                break;
        }

        switch (pacman.getCurrentDirection()) {
            case LEFT:
                if (mWorld.getMaze().getBlock((int) Math.ceil((pacman.getPosition().x / 100f) - 1),
                        (pacman.getPosition().y / 100)) instanceof EmptyTile)
                {
                    pacman.updatePosition();
                    pacman.getPosition().y = Math.round(pacman.getPosition().y / 100) * 100;
                }
                break;
            case RIGHT:
                if (mWorld.getMaze().getBlock((pacman.getPosition().x / 100) + 1,
                        (pacman.getPosition().y / 100)) instanceof EmptyTile)
                {
                    pacman.updatePosition();
                    pacman.getPosition().y = Math.round(pacman.getPosition().y / 100) * 100;
                }
                break;
            case UP:
                if (mWorld.getMaze().getBlock((pacman.getPosition().x / 100),
                        (int) Math.ceil((pacman.getPosition().y / 100f)) - 1)
                        instanceof EmptyTile)
                {
                    pacman.updatePosition();
                    pacman.getPosition().x = Math.round(pacman.getPosition().x / 100) * 100;
                }
                break;
            case DOWN:
                if (mWorld.getMaze().getBlock((pacman.getPosition().x / 100),
                        (pacman.getPosition().y / 100) +1 ) instanceof EmptyTile)
                {
                    pacman.updatePosition();
                    pacman.getPosition().x = Math.round(pacman.getPosition().x / 100) * 100;
                }
                break;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Pacman pacman = mWorld.getPacman();
        switch (keycode) {
            case Input.Keys.LEFT:
                pacman.setWantedDirection(Pacman.Direction.LEFT);
                Gdx.app.log(WorldRenderer.class.getName(), "LEFT");
                break;
            case Input.Keys.RIGHT:
                pacman.setWantedDirection(Pacman.Direction.RIGHT);
                Gdx.app.log(WorldRenderer.class.getName(), "RIGHT");
                break;
            case Input.Keys.UP:
                pacman.setWantedDirection(Pacman.Direction.UP);
                Gdx.app.log(WorldRenderer.class.getName(), "UP");
                break;
            case Input.Keys.DOWN:
                pacman.setWantedDirection(Pacman.Direction.DOWN);
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

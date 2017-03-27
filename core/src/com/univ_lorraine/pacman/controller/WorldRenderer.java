package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;
import com.univ_lorraine.pacman.screens.EndScreen;
import com.univ_lorraine.pacman.view.TextureFactory;

/**
 * @author Édouard WILLISSECK
 */

public class WorldRenderer implements InputProcessor {
    float size;
    private final SpriteBatch batch;
    private final TextureFactory textureFactory;
    private final World mWorld;
    private final Game mGame;
    private FPSLogger mFPSLogger = new FPSLogger();

    /**
     * Creates the WorldRenderer.
     *  @param world The world being controlled by the {@link WorldRenderer}
     *
     */
    public WorldRenderer(World world, Game game) {
        textureFactory = TextureFactory.getInstance();
        batch = new SpriteBatch();
        mWorld = world;
        Gdx.input.setInputProcessor(this);
        mWorld.getMaze().loadDemoLevel(World.getCoef());
        mGame = game;

    }

    /**
     * Sets the size of the sprites.
     *
     * @param size The size of the sprites.
     */
    public void setSize(float size) {
        this.size = size;
    }

    /**
     * Renders the maze and the {@link GameElement}s
     *
     * @param camera    The camera for the game.
     * @param deltaTime The time passed between two renders.
     */
    public void render(OrthographicCamera camera, float deltaTime) {
//        setLag(50);
        updateEpsilons(deltaTime);
        checkEndGame();
        batch.setProjectionMatrix(camera.combined);
        drawWorld();
        textureFactory.update(deltaTime);
        moveGameElements(deltaTime);
//        mFPSLogger.log();
    }

    public void setLag(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateEpsilons(float deltaTime) {
        mWorld.getPacman().getMovementController().updateEpsilon(deltaTime);
        for (Ghost ghost : mWorld.getGhosts()) {
            ghost.getMovementController().updateEpsilon(deltaTime);
        }
    }

    public void checkEndGame() {
        if (mWorld.getMaze().getPelletNumber() == 0) {
            Gdx.app.log(WorldRenderer.class.getSimpleName(), "Vous avez gagné !");
            mGame.setScreen(new EndScreen(true, mWorld.getScore()));
        }
        else if (World.getLifeCounter() <= 0) {
            Gdx.app.log(getClass().getSimpleName(), "Vous avez perdu !");
            mGame.setScreen(new EndScreen(false, mWorld.getScore()));
        }
    }

    public void drawWorld() {
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (GameElement e : mWorld) {
            Vector2D position = e.getPosition();
            Texture texture = textureFactory.getTexture(e);
            batch.draw(texture,
                    ((position.x / ((float) World.getCoef())) - mWorld.getWidth() / 2f) * size,
                    ((position.y / ((float) World.getCoef())) - mWorld.getHeight() / 2f) * size, size, size,
                    0, 0,
                    texture.getWidth(), texture.getHeight(), false, true);
        }
        batch.end();
    }

    /**
     * Move the pacman and the ghosts of the world.
     * @param deltaTime The time elapsed between this render and the last one.
     */
    public void moveGameElements(float deltaTime) {
        mWorld.getPacman().move(deltaTime);
        for (Ghost ghost : mWorld.getGhosts()) {
            ghost.useAI();
            ghost.move(deltaTime);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Pacman pacman = mWorld.getPacman();
        switch (keycode) {
            case Input.Keys.LEFT:
                pacman.setWantedDirection(Direction.LEFT);
                break;
            case Input.Keys.RIGHT:
                pacman.setWantedDirection(Direction.RIGHT);
                break;
            case Input.Keys.UP:
                pacman.setWantedDirection(Direction.UP);
                break;
            case Input.Keys.DOWN:
                pacman.setWantedDirection(Direction.DOWN);
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

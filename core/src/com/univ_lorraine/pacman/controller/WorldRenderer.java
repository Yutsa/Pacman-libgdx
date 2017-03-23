package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.univ_lorraine.pacman.model.BasicPellet;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.EmptyTile;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Maze;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;
import com.univ_lorraine.pacman.screens.WinScreen;
import com.univ_lorraine.pacman.view.TextureFactory;

/**
 * @author Édouard WILLISSECK
 */

public class WorldRenderer implements InputProcessor {
    float size;
    private SpriteBatch batch;
    private TextureFactory textureFactory;
    private World mWorld;
    private double epsilon;
    private Game mGame;
    /**
     * The coefficient by which the logical world is bigger than the onscreen world.
     */
    private int mCoef;

    /**
     * Creates the WorldRenderer.
     *
     * @param world The world being controlled by the {@link WorldRenderer}
     */
    public WorldRenderer(World world, Game game) {
        textureFactory = TextureFactory.getInstance();
        batch = new SpriteBatch();
        mWorld = world;
        Gdx.input.setInputProcessor(this);
        mCoef = mWorld.getCoef();
        mWorld.getMaze().loadDemoLevel(mCoef);
        epsilon = (mWorld.getPacman().getSpeed() / 6000f);
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
        if (mWorld.getMaze().getPelletNumber() == 0) {
            Gdx.app.log(WorldRenderer.class.getSimpleName(), "Vous avez gagné !");
            mGame.setScreen(new WinScreen());
        }
        Pacman pacman = mWorld.getPacman();
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        /* Iterates through the maze and renders the block */
        for (GameElement e : mWorld) {
            Vector2D position = e.getPosition();
            batch.draw(textureFactory.getTexture(e),
                    ((position.x / ((float) mCoef)) - mWorld.getWidth() / 2f) * size,
                    ((position.y / ((float) mCoef)) - mWorld.getHeight() / 2f) * size, size, size);
        }
        batch.end();
        moveElement(mWorld.getPacman(), deltaTime);
    }

    /**
     * Eats a pellet and change it to an empty block.
     */
    public void eatPellet(GameElement gameElement) {
        Vector2D position = new Vector2D(gameElement.getPosition().getX() / mCoef,
                gameElement.getPosition().getY() / mCoef);

        Vector2D gameElementPosition = gameElement.getPosition();
        int x = gameElementPosition.x / mCoef;
        int y = gameElementPosition.y / mCoef;
        mWorld.getMaze().setBlock(new EmptyTile(gameElementPosition, mWorld),
                position.getX(), position.getY());
        long timeElapsed = TimeUtils.timeSinceMillis(mWorld.getStartTime());
        mWorld.winPoint(10 - (int) (timeElapsed / 1000));
        mWorld.getMaze().decreasePelleNumber();
    }

    /**
     * Gets the next block given a position and a direction.
     *
     * @param position  The initial position of the element who wants to know the next block.
     * @param direction The direction of the element who wants to know the next block.
     * @return The next GameElement on the map.
     */
    private GameElement getNextElement(Vector2D position, Pacman.Direction direction) {
        int x, y;
        switch (direction) {
            case LEFT:
                return mWorld.getMaze().getBlock(
                        (int) Math.ceil((position.getX() / ((float) mCoef)) - 1),
                        position.getY() / mCoef);
            case RIGHT:
                return mWorld.getMaze().getBlock(
                        (position.getX() / mCoef) + 1,
                        position.getY() / mCoef);
            case UP:
                return mWorld.getMaze().getBlock(
                        position.getX() / mCoef,
                        (int) Math.ceil((position.getY() / ((float) mCoef))) - 1);
            case DOWN:
                return mWorld.getMaze().getBlock(
                        position.getX() / mCoef,
                        (position.getY() / mCoef) + 1);
        }

        throw new IllegalArgumentException("Unrecognized Direction.");
    }

    /**
     * Fix the position of the element. If he went a little too far it repositions it.
     *
     * @param element The elemen to fix the position.
     */
    private void fixPosition(MovableGameElement element) {
        switch (element.getCurrentDirection()) {
            case LEFT:
                if (element.getPosition().getX() / ((float) mCoef)
                        - (element.getPosition().getX() / mCoef) < epsilon) {
                    element.getPosition().setX(element.getPosition().getX() / mCoef * mCoef);
                }
                break;
            case RIGHT:
                if ((element.getPosition().getX() / mCoef) + 1
                        - element.getPosition().getX() / ((float) mCoef) < epsilon) {
                    element.getPosition().setX(((element.getPosition().x / mCoef) + 1) * mCoef);
                }
                break;
            case UP:
                if (element.getPosition().y / ((float) mCoef)
                        - (element.getPosition().y / mCoef) < epsilon) {
                    element.getPosition().setY((element.getPosition().y / mCoef) * mCoef);
                }
                break;
            case DOWN:
                if ((element.getPosition().y / mCoef) + 1
                        - element.getPosition().y / ((float) mCoef) < epsilon) {
                    element.getPosition().setY(((element.getPosition().y / mCoef) + 1) * mCoef);
                }
                break;
        }
    }

    /**
     * Checks if the pacman can go to the wanted direction or not.
     *
     * @param pacman          The {@link Pacman} that is moving.
     * @param wantedDirection The direction the {@link Pacman} wants to go.
     */
    private void checkWantedDirection(MovableGameElement pacman, Pacman.Direction wantedDirection) {
        GameElement nextBlock = getNextElement(pacman.getPosition(), pacman.getWantedDirection());

        switch (wantedDirection) {
            case LEFT:
            case RIGHT:
                if (!(nextBlock instanceof Block)) {
                    if (pacman.getPosition().y == nextBlock.getPosition().y) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case UP:
            case DOWN:
                if (!(nextBlock instanceof Block)) {
                    if (pacman.getPosition().x == nextBlock.getPosition().x) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
        }
    }

    /**
     * Moves the pacman on the {@link Maze}
     *
     * @param deltaTime The time elapsed between two renders.
     */
    public void moveElement(MovableGameElement movableGameElement, float deltaTime) {
        checkTunnel(movableGameElement);
        Vector2D currentPosition;
        GameElement currentGameElement;

        GameElement nextBlock = getNextElement(movableGameElement.getPosition(), movableGameElement.getCurrentDirection());

        checkWantedDirection(movableGameElement, movableGameElement.getWantedDirection());

        if (!(nextBlock instanceof Block)) {
            movableGameElement.updatePosition(deltaTime);
        }

        fixPosition(movableGameElement);
        currentPosition = movableGameElement.getPosition();
        currentGameElement = mWorld.getMaze().getBlock(
                (currentPosition.getX() + 50) / mCoef,
                (currentPosition.getY() + 50) / mCoef);

        if (currentGameElement instanceof BasicPellet) {
            eatPellet(currentGameElement);
        }

    }

    /**
     * Checks if we are going through the tunnel and teleports the {@link MovableGameElement}
     *
     * @param movableGameElement The {@link MovableGameElement} that is going through th tunnel.
     */
    private void checkTunnel(MovableGameElement movableGameElement) {
    /* Handles TP */
        if ((movableGameElement.getPosition().x / mCoef) == mWorld.getWidth() - 1) {
            movableGameElement.setPosition(new Vector2D(0, movableGameElement.getPosition().y));
        }

        if ((movableGameElement.getPosition().x / mCoef) == 0
                && movableGameElement.getCurrentDirection() == Pacman.Direction.LEFT) {
            movableGameElement.setPosition(new Vector2D(27 * mCoef, movableGameElement.getPosition().y));
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Pacman pacman = mWorld.getPacman();
        switch (keycode) {
            case Input.Keys.LEFT:
                pacman.setWantedDirection(Pacman.Direction.LEFT);
                break;
            case Input.Keys.RIGHT:
                pacman.setWantedDirection(Pacman.Direction.RIGHT);
                break;
            case Input.Keys.UP:
                pacman.setWantedDirection(Pacman.Direction.UP);
                break;
            case Input.Keys.DOWN:
                pacman.setWantedDirection(Pacman.Direction.DOWN);
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

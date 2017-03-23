package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.univ_lorraine.pacman.model.BasicPellet;
import com.univ_lorraine.pacman.model.Block;
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
    private double epsilon = (Pacman.mSpeed / 10000f) * 2;
    /**
     * The coefficient by which the logical world is bigger than the onscreen world.
     */
    private int mCoef;

    /**
     * Creates the WorldRenderer.
     *
     * @param world The world being controlled by the {@link WorldRenderer}
     */
    public WorldRenderer(World world) {
        textureFactory = TextureFactory.getInstance();
        batch = new SpriteBatch();
        mWorld = world;
        Gdx.input.setInputProcessor(this);
        mCoef = mWorld.getCoef();
        mWorld.getMaze().loadDemoLevel(mCoef);
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
        movePacman(deltaTime);
//        Gdx.app.log(WorldRenderer.class.getName(), mWorld.getPacman().getPosition().toString());
    }

    /**
     * Eats a pellet and change it to an empty block.
     */
    public void eatPellet(GameElement gameElement) {
        Vector2D gameElementPosition = gameElement.getPosition();
        int x = gameElementPosition.x / mCoef;
        int y = gameElementPosition.y / mCoef;
        mWorld.getMaze().setBlock(new EmptyTile(gameElementPosition, mWorld), x, y);
    }

    // TODO: 23/03/17 Make a "getNextBlock()" method.
    private GameElement getNextBlock(Vector2D position, Pacman.Direction direction) {
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
                        (int) Math.ceil((position.getY()/ ((float) mCoef))) - 1);
            case DOWN:
                return mWorld.getMaze().getBlock(
                        position.getX() / mCoef,
                        (position.getY() / mCoef) + 1);
        }

        throw new IllegalArgumentException("Unrecognized Direction.");
    }
    /**
     * Checks if the pacman can go to the wanted direction or not.
     *
     * @param pacman          The {@link Pacman} that is moving.
     * @param wantedDirection The direction the {@link Pacman} wants to go.
     */
    private void checkWantedDirection(Pacman pacman, Pacman.Direction wantedDirection) {
        GameElement nextBlock;

        /* Handles TP */
        if ((pacman.getPosition().x / mCoef) == mWorld.getWidth() - 1) {
            pacman.setPosition(new Vector2D(0, pacman.getPosition().y));
        }

        if ((pacman.getPosition().x / mCoef) == 0
                && pacman.getCurrentDirection() == Pacman.Direction.LEFT) {
            pacman.setPosition(new Vector2D(27 * mCoef, pacman.getPosition().y));
        }

        switch (wantedDirection) {
            case LEFT:
                if (!((nextBlock = mWorld.getMaze().getBlock((int) Math.ceil((pacman.getPosition().x / ((float) mCoef)) - 1),
                        (pacman.getPosition().y / mCoef))) instanceof Block)) {
                    if (pacman.getPosition().y == nextBlock.getPosition().y) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case RIGHT:
                if (!((nextBlock = mWorld.getMaze().getBlock((pacman.getPosition().x / mCoef) + 1,
                        (pacman.getPosition().y / mCoef))) instanceof Block)) {
                    if (pacman.getPosition().y == nextBlock.getPosition().y) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case UP:
                if (!((nextBlock = mWorld.getMaze().getBlock((pacman.getPosition().x / mCoef),
                        (int) Math.ceil((pacman.getPosition().y / ((float) mCoef))) - 1))
                        instanceof Block)) {
                    if (pacman.getPosition().x == nextBlock.getPosition().x) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case DOWN:
                if (!((nextBlock = mWorld.getMaze().getBlock((pacman.getPosition().x / mCoef),
                        (pacman.getPosition().y / mCoef) + 1)) instanceof Block)) {
                    if (pacman.getPosition().x == nextBlock.getPosition().x) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
        }
    }

    /**
     * Moves the pacman on the {@link com.univ_lorraine.pacman.model.Maze}
     *
     * @param deltaTime The time elapsed between two renders.
     */
    public void movePacman(float deltaTime) {
        Pacman pacman = mWorld.getPacman();
        GameElement nextBlock = getNextBlock(pacman.getPosition(), pacman.getCurrentDirection());

        checkWantedDirection(pacman, pacman.getWantedDirection());

        switch (pacman.getCurrentDirection()) {
            case LEFT:


                if (!(nextBlock instanceof Block)) {

                    pacman.updatePosition(deltaTime);

                    if (pacman.getPosition().x / ((float) mCoef) - (pacman.getPosition().x / mCoef) < epsilon)
                        pacman.getPosition().x = (pacman.getPosition().x / mCoef) * mCoef;
                }
                break;
            case RIGHT:
                nextBlock = mWorld.getMaze().getBlock((pacman.getPosition().x / mCoef) + 1,
                        (pacman.getPosition().y / mCoef));

                if (!(nextBlock instanceof Block)) {

                    pacman.updatePosition(deltaTime);

                    if ((pacman.getPosition().x / mCoef) + 1
                            - pacman.getPosition().x / ((float) mCoef) < epsilon) {
                        pacman.getPosition().x = ((pacman.getPosition().x / mCoef) + 1) * mCoef;
                    }
                }
                break;
            case UP:
                nextBlock = mWorld.getMaze().getBlock((pacman.getPosition().x / mCoef),
                        (int) Math.ceil((pacman.getPosition().y / ((float) mCoef))) - 1);

                if (!(nextBlock instanceof Block)) {
                    pacman.getPosition().x = (int) Math.floor(pacman.getPosition().x / mCoef) * mCoef;

                    pacman.updatePosition(deltaTime);

                    if (pacman.getPosition().y / ((float) mCoef) - (pacman.getPosition().y / mCoef) < epsilon)
                        pacman.getPosition().y = (pacman.getPosition().y / mCoef) * mCoef;

                }
                break;
            case DOWN:
                nextBlock = mWorld.getMaze().getBlock((pacman.getPosition().x / mCoef),
                        (pacman.getPosition().y / mCoef) + 1);

                if (!(nextBlock instanceof Block)) {
                    pacman.getPosition().x = (int) Math.floor(pacman.getPosition().x / mCoef) * mCoef;

                    pacman.updatePosition(deltaTime);

                    if ((pacman.getPosition().y / mCoef) + 1
                            - pacman.getPosition().y / ((float) mCoef) < epsilon) {
                        pacman.getPosition().y = ((pacman.getPosition().y / mCoef) + 1) * mCoef;
                    }
                }
                break;
        }

        if (nextBlock instanceof BasicPellet) {
            eatPellet(nextBlock);
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

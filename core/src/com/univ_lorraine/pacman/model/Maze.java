package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * The class representing the maze of the map.
 */

public class Maze implements Iterable<GameElement> {
    /**
     * The width of the maze.
     */
    private int width;
    /**
     * The height of the maze.
     */
    private int height;
    /**
     * The world the maze is in.
     */
    private World world;
    /**
     * The blocks GameElement composing the maze.
     */
    private GameElement[][] blocks;
    /**
     * The number of pellets on the map.
     */
    private int pelletNumber = 0;

    /**
     * Creates the maze and assigns its world.
     *
     * @param world The world to assign to the maze.
     */
    public Maze(World world) {
        setWorld(world);
    }

    /**
     * Gets the width of the maze
     *
     * @return The width of the maze.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the maze.
     *
     * @param width The width of the maze.
     */
    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("The width of the maze cannot be negative.");
        }
        this.width = width;
    }

    /**
     * Gets the height of the maze
     *
     * @return The height of the maze.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the maze
     *
     * @param height The height of the maze.
     */
    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("The height of the maze cannot be negative.");
        }

        this.height = height;
    }

    /**
     * Sets the world of the maze
     *
     * @param world The world of the maze.
     */
    public void setWorld(World world) {
        if (world == null) {
            throw new IllegalArgumentException("The mWorld cannot be empty.");
        }
        this.world = world;
    }

    /**
     * Gets the block at the coordinates given.
     *
     * @param x The x-axis coordinate of the block to get.
     * @param y The y-axis coordinate of the block to get.
     * @return Gets the block at the coordinates given.
     */
    public GameElement getBlock(int x, int y) {
        if (x >= width) {
            x = width - 1;
            Gdx.app.log(Maze.class.getSimpleName(), "Changed x to " + x);
        }
        else if (x < 0) {
            x = 0;
        }
        if (x >= height) {
            y = height - 1;
            Gdx.app.log(Maze.class.getSimpleName(), "Changed y to " + y);
        }
        else if (x < 0) {
            y = 0;
        }
        return blocks[x][y];
    }

    /**
     * Sets the block at postion (x, y) to the gameElement passed.
     *
     * @param gameElement The new GameElement at this position.
     * @param x           the x-axis coordinate of the element to change.
     * @param y           the y-axis coordinate of the element to change.
     */
    public void setBlock(GameElement gameElement, int x, int y) {
        blocks[x][y] = gameElement;
    }

    public int getPelletNumber() {
        return pelletNumber;
    }

    public void decreasePelleNumber() {
        if (pelletNumber > 0)
            pelletNumber--;
    }

    /**
     * Returns an iterator to iterate through the maze
     *
     * @return An iterator to iterate through the maze
     */
    @Override
    public Iterator<GameElement> iterator() {
        Iterator<GameElement> it = new Iterator<GameElement>() {
            private int x = 0;
            private int y = 0;

            @Override
            public boolean hasNext() {
                return x < width && y < height;
            }

            @Override
            public GameElement next() {
                GameElement gameElement;
                gameElement = blocks[x][y];
                x++;
                if (x >= width) {
                    x = 0;
                    y++;
                }
                return gameElement;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }

    /**
     * Loads a level to play.
     *
     * @param coeff The coeff between the logical positions and the real on-screen positions.
     */
    public void loadDemoLevel(int coeff) {
        setWidth(28);
        setHeight(31);
        blocks = new GameElement[width][height];
        FileHandle handle = Gdx.files.internal("levels/testEndLvl.txt");
        InputStream stream = handle.read();
        try {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char c = (char) stream.read();
                    if (c == '\n')
                        c = (char) stream.read();

                    if (c == '0')
                        blocks[x][y] = new Block(new Vector2D(x * coeff, y * coeff), world);
                    else if (c == '1')
                        blocks[x][y] = new EmptyTile(new Vector2D(x * coeff, y * coeff), world);
                    else if (c == '2') {
                        blocks[x][y] = new BasicPellet(new Vector2D(x * coeff, y * coeff), world);
                        pelletNumber++;
                    }
                    else if (c == '3') {
                        blocks[x][y] = new BasicPellet(new Vector2D(x * coeff, y * coeff), world);
                        pelletNumber++;
                    }
                    else
                        blocks[x][y] = new EmptyTile(new Vector2D(x * coeff, y * coeff), world);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

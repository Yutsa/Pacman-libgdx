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
    private int width;
    private int height;
    private World world;
    private GameElement[][] blocks;

    public Maze(World world) {
        setWorld(world);
//        loadDemoLevel();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("The width of the maze cannot be negative.");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("The height of the maze cannot be negative.");
        }

        this.height = height;
    }

    public void setWorld(World world) {
        if (world == null) {
            throw new IllegalArgumentException("The mWorld cannot be empty.");
        }
        this.world = world;
    }

    public GameElement getBlock(int x, int y) {
        return blocks[x][y];
    }

    /**
     * Sets the block at postion (x, y) to the gameElement passed.
     * @param gameElement The new GameElement at this position.
     * @param x the x-axis coordinate of the element to change.
     * @param y the y-axis coordinate of the element to change.
     */
    public void setBlock(GameElement gameElement, int x, int y) {
        blocks[x][y] = gameElement;
    }

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

    public void loadDemoLevel(int coeff) {
        setWidth(28);
        setHeight(31);
        blocks = new GameElement[width][height];
        FileHandle handle = Gdx.files.internal("levels/base_lvl.txt");
        InputStream stream = handle.read();
        try {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char c = (char) stream.read();
                    if (c == '\n')
                        c = (char) stream.read();
                    if (c == '1')
                        blocks[x][y] = new Block(new Vector2D(x * coeff, y * coeff), world);
                    else
                        blocks[x][y] = new BasicPellet(new Vector2D(x * coeff, y * coeff), world);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

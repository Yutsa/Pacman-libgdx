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

    public int getCoef() {
        return mCoef;
    }

    private int mCoef = 100;

    public Maze(World world) {
        setWorld(world);
        loadDemoLevel();
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

    private void loadDemoLevel() {
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
                        blocks[x][y] = new Block(new Vector2D(x * mCoef, y * mCoef), world);
                    else
                        blocks[x][y] = new EmptyTile(new Vector2D(x * mCoef, y * mCoef), world);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

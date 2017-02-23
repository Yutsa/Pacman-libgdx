package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

/**
 * The class representing the maze of the map.
 */

public class Maze implements Iterable<GameElement> {
    private int width;
    private int height;
    private World world;
    private Block[][] blocks;

    public Maze(World world, int width, int height) {
        setWorld(world);
        setWidth(width);
        setHeight(height);
        blocks = new Block[width][height];
        loadDemoLevel();
    }

    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("The width of the maze cannot be negative.");
        }
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        if (height <= 0){
            throw new IllegalArgumentException("The height of the maze cannot be negative.");
        }

        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWorld(World world) {
        if (world == null) {
            throw new IllegalArgumentException("The mWorld cannot be empty.");
        }
        this.world = world;
    }

    public Block get(int x, int y) {
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
                GameElement gameElement = blocks[x][y];
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                blocks[i][j] = new Block(new Vector2(i, j), world);
            }
        }
    }
}

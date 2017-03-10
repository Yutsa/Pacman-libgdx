package com.univ_lorraine.pacman.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<GameElement> {
    private Pacman mPacman;
    private Maze mMaze;

    private ArrayList<GameElement> mGameElements;

    public World() {
        mPacman = new Pacman(new Point(0, 0), this);
        mMaze = new Maze(this, 10, 10);
        mGameElements = new ArrayList<GameElement>();
        mGameElements.add(mPacman);
    }

    public int getWidth() {
        return mMaze.getWidth();
    }

    public int getHeight() {
        return mMaze.getHeight();
    }

    public Pacman getPacman() {
        return mPacman;
    }

    public Maze getMaze() {
        return mMaze;
    }

    @Override
    public Iterator<GameElement> iterator() {
        return new WorldIterator();
    }

    class WorldIterator implements Iterator<GameElement> {

        Iterator<GameElement> mazeIterator = mMaze.iterator();
        Iterator<GameElement> mGameElementIterator = mGameElements.iterator();
        @Override
        public boolean hasNext() {
            return mazeIterator.hasNext() || mGameElementIterator.hasNext();
        }

        @Override
        public GameElement next() {
            if (mazeIterator.hasNext()) {
                return mazeIterator.next();
            }
            return mGameElementIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }
    }
}

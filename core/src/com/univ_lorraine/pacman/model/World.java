package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.utils.TimeUtils;
import com.univ_lorraine.pacman.controller.GhostAI;
import com.univ_lorraine.pacman.controller.RandomAI;
import com.univ_lorraine.pacman.controller.SearchPacmanAI;
import com.univ_lorraine.pacman.controller.WorldRenderer;

import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<GameElement> {

    /**
     * The Pacman of this world.
     */
    private Pacman mPacman;
    private Ghost redGhost;
    private Ghost yellowGhost;
    /**
     * The Maze of this world.
     */
    private Maze mMaze;
    /**
     * The coefficient by which the logical world is bigger than the onscreen world.
     */
    private int mCoef = 100;
    /**
     * The score of the player.
     */
    private int score = 0;
    /**
     * The time at which the game started.
     */
    long startTime;
    WorldRenderer mWorldRenderer;

    /**
     * The GameElements in this world.
     */
    private ArrayList<GameElement> mGameElements;

    /**
     * Creates the World, the Pacman and the Maze.
     */
    public World() {
        mPacman = new Pacman(new Vector2D(14 * mCoef, 17 * mCoef), this, 500);
        mMaze = new Maze(this);
        startTime = TimeUtils.millis();
    }

    public void createGhosts() {
        GhostAI randomAI = new RandomAI(mWorldRenderer);
        GhostAI searchAI = new SearchPacmanAI(mWorldRenderer);

        redGhost = new Ghost(new Vector2D(14 * mCoef, 13 * mCoef), this, 500,
                Ghost.Color.RED, randomAI);
        yellowGhost = new Ghost(new Vector2D(13 * mCoef, 13 * mCoef), this, 500,
                Ghost.Color.YELLOW, searchAI);
        mGameElements = new ArrayList<GameElement>();
        mGameElements.add(mPacman);
        mGameElements.add(redGhost);
        mGameElements.add(yellowGhost);
    }

    public long getStartTime() {
        return startTime;
    }

    public void resetStartTime() {
        startTime = TimeUtils.millis();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void winPoint(int wonPoint) {
        if (wonPoint <= 0) {
            wonPoint = 5;
        }
        score += wonPoint;
    }

    /**
     * Gets the coef.
     *
     * @return The coef.
     */
    public int getCoef() {
        return mCoef;
    }

    /**
     * Gets the world's width.
     *
     * @return The world's width.
     */
    public int getWidth() {
        return mMaze.getWidth();
    }

    /**
     * Gets the world's height.
     *
     * @return The world's height.
     */
    public int getHeight() {
        return mMaze.getHeight();
    }

    /**
     * Gets the world's Pacman.
     *
     * @return The world's Pacman.
     */
    public Pacman getPacman() {
        return mPacman;
    }

    public Ghost getRedGhost() {
        return redGhost;
    }

    public Ghost getYellowGhost() {
        return yellowGhost;
    }

    /**
     * Gets the world's Maze.
     *
     * @return The world's Maze.
     */
    public Maze getMaze() {
        return mMaze;
    }

    public WorldRenderer getWorldRenderer() {
        return mWorldRenderer;
    }

    public void setWorldRenderer(WorldRenderer worldRenderer) {
        mWorldRenderer = worldRenderer;
    }

    /**
     * Returns an iterator to iterate through the World.
     *
     * @return An iterator to iterate through the World.
     */
    @Override
    public Iterator<GameElement> iterator() {
        return new WorldIterator();
    }

    /**
     * The iterator for the World.
     */
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

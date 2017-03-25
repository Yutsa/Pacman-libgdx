package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.univ_lorraine.pacman.controller.GhostMoveController;
import com.univ_lorraine.pacman.controller.OutOfHouseAI;
import com.univ_lorraine.pacman.controller.PacmanMoveController;

import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<GameElement> {

    /**
     * The Pacman of this world.
     */
    private final Pacman mPacman;
    private final ArrayList<Ghost> mGhosts = new ArrayList<Ghost>();
    /**
     * The Maze of this world.
     */
    private final Maze mMaze;
    /**
     * The coefficient by which the logical world is bigger than the onscreen world.
     */
    private final int mCoef = 100;
    /**
     * The score of the player.
     */
    private int score = 0;
    /**
     * The time at which the game started.
     */
    final long startTime;
    /**
     * The GameElements in this world.
     */
    private ArrayList<GameElement> mGameElements;

    /**
     * Creates the World, the Pacman and the Maze.
     */
    public World() {
        mPacman = new Pacman(new Vector2D(14 * mCoef, 17 * mCoef), this,
                500, null);
        mPacman.setMovementController(new PacmanMoveController(this));
        Gdx.app.log(getClass().getSimpleName(), "Speed = " + mPacman.getSpeed());
        mMaze = new Maze(this);
        startTime = TimeUtils.millis();
    }

    public void createGhosts() {
        RedGhost redGhost = new RedGhost(new Vector2D(14 * mCoef, 13 * mCoef), this, 500,
                new OutOfHouseAI());
        YellowGhost yellowGhost = new YellowGhost(new Vector2D(13 * mCoef, 13 * mCoef), this, 500,
                new OutOfHouseAI());
        PinkGhost pinkGhost = new PinkGhost(new Vector2D(12 * mCoef, 13 * mCoef), this, 500,
                new OutOfHouseAI());

        mGameElements = new ArrayList<GameElement>();
        mGameElements.add(mPacman);
        mGameElements.add(redGhost);
        mGhosts.add(redGhost);
        mGameElements.add(yellowGhost);
        mGhosts.add(yellowGhost);
        mGameElements.add(pinkGhost);
        mGhosts.add(pinkGhost);

        GhostMoveController ghostMoveController = new GhostMoveController(this);
        for (Ghost ghost : mGhosts) {
            ghost.setMovementController(ghostMoveController);
        }

        redGhost.getAi().setGhost(redGhost);
        yellowGhost.getAi().setGhost(yellowGhost);
        pinkGhost.getAi().setGhost(pinkGhost);
    }

    public ArrayList<Ghost> getGhosts() {
        return mGhosts;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getScore() {
        return score;
    }

    public void winPoint(int wonPoint) {
        if (wonPoint <= 0) {
            wonPoint = 5;
        }
        score += wonPoint;
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

    /**
     * Gets the world's Maze.
     *
     * @return The world's Maze.
     */
    public Maze getMaze() {
        return mMaze;
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

        final Iterator<GameElement> mazeIterator = mMaze.iterator();
        final Iterator<GameElement> mGameElementIterator = mGameElements.iterator();

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

    public int getCoef() {
        return mCoef;
    }
}

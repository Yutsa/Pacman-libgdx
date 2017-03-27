package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.utils.TimeUtils;
import com.univ_lorraine.pacman.controller.GhostMoveController;
import com.univ_lorraine.pacman.controller.PacmanMoveController;
import com.univ_lorraine.pacman.controller.RandomAI;
import com.univ_lorraine.pacman.controller.SearchPacmanAI;
import com.univ_lorraine.pacman.controller.SwitchAI;

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
    private final static int mCoef = 100;
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
    private static int lifeCounter = 3;
    public static Vector2D pacmanStartingPosition = new Vector2D(14 * mCoef, 17 * mCoef);
    public static Vector2D redGhostStartingPos = new Vector2D(14 * mCoef, 13 * mCoef);
    public static Vector2D yellowGhostStartingPos = new Vector2D(13 * mCoef, 13 * mCoef);
    public static Vector2D pinkGhostStartingPos = new Vector2D(13 * mCoef, 14 * mCoef);

    /**
     * Creates the World, the Pacman and the Maze.
     */
    public World() {
        mPacman = new Pacman(new Vector2D(pacmanStartingPosition), this,
                500, null);
        mPacman.setMovementController(new PacmanMoveController(this));
        mMaze = new Maze(this);
        startTime = TimeUtils.millis();
    }

    public static Vector2D getPacmanStartingPosition() {
        return pacmanStartingPosition;
    }

    public static int getLifeCounter() {
        return lifeCounter;
    }

    public static void setLifeCounter(int lifeCounter) {
        World.lifeCounter = lifeCounter;
    }

    public static void decreaseLifeCounter() {
        lifeCounter--;
        if (lifeCounter <= 0) {
            lifeCounter = 0;
        }
    }

    public void createGhosts() {
        RedGhost redGhost = new RedGhost(new Vector2D(redGhostStartingPos), this, 500);
        YellowGhost yellowGhost = new YellowGhost(new Vector2D(yellowGhostStartingPos), this, 500);
        PinkGhost pinkGhost = new PinkGhost(new Vector2D(pinkGhostStartingPos), this, 500);

        mGameElements = new ArrayList<GameElement>();
        mGameElements.add(mPacman);
        mGameElements.add(redGhost);
        mGameElements.add(yellowGhost);
        mGameElements.add(pinkGhost);

        mGhosts.add(pinkGhost);
        mGhosts.add(yellowGhost);
        mGhosts.add(redGhost);

        for (Ghost ghost : mGhosts) {
            ghost.setMovementController(new GhostMoveController(this, ghost));
        }

        pinkGhost.initAI(new SwitchAI(pinkGhost));
        yellowGhost.initAI(new SearchPacmanAI(yellowGhost));
        redGhost.initAI(new RandomAI(redGhost));
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

    public static int getCoef() {
        return mCoef;
    }
}

package com.univ_lorraine.pacman.model;

import com.badlogic.gdx.utils.TimeUtils;
import com.univ_lorraine.pacman.controller.MovementController;
import com.univ_lorraine.pacman.controller.OutOfHouseAI;

import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<GameElement> {

    /**
     * The Pacman of this world.
     */
    private final Pacman mPacman;
    private RedGhost redGhost;
    private YellowGhost yellowGhost;
    private PinkGhost pinkGhost;
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
    MovementController mMovementController;

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
        redGhost = new RedGhost(new Vector2D(14 * mCoef, 13 * mCoef), this, 500,
                new OutOfHouseAI(mMovementController));
        yellowGhost = new YellowGhost(new Vector2D(13 * mCoef, 13 * mCoef), this, 500,
                new OutOfHouseAI(mMovementController));
        pinkGhost = new PinkGhost(new Vector2D(12 * mCoef, 13 * mCoef), this, 500,
                new OutOfHouseAI(mMovementController));

        redGhost.getAi().setGhost(redGhost);
        yellowGhost.getAi().setGhost(yellowGhost);
        pinkGhost.getAi().setGhost(pinkGhost);

        mGameElements = new ArrayList<GameElement>();
        mGameElements.add(mPacman);
        mGameElements.add(redGhost);
        mGhosts.add(redGhost);
        mGameElements.add(yellowGhost);
        mGhosts.add(yellowGhost);
        mGameElements.add(pinkGhost);
        mGhosts.add(pinkGhost);
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

    public void setMovementController(MovementController movementController) {
        mMovementController = movementController;
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
}

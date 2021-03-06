package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.utils.TimeUtils;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.EmptyTile;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.Maze;
import com.univ_lorraine.pacman.model.MazeElement;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.SuperPellet;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Édouard WILLISSECK
 */

@SuppressWarnings("SameParameterValue")
public abstract class MovementController {
    protected World mWorld;
    protected int mCoef = World.getCoef();
    protected double epsilon;

    public MovementController(World world) {
        setWorld(world);
    }

    public void setWorld(World world) {
        if (world == null) {
            throw new IllegalArgumentException();
        }
        mWorld = world;
    }

    public World getWorld() {
        return mWorld;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public double getEpsilon() {
        return epsilon;
    }

    /**
     * Gets the next block given a position and a direction.
     *
     * @param position  The initial position of the element who wants to know the next block.
     * @param direction The direction of the element who wants to know the next block.
     * @return The next GameElement on the map.
     */
    public GameElement getNextElement(Vector2D position, Direction direction) {
        switch (direction) {
            case LEFT:
                return getNextLeftElement(position);
            case RIGHT:
                return getNextRightElement(position);
            case UP:
                return getNextUpElement(position);
            case DOWN:
                return getNextDownElement(position);
        }

        throw new IllegalArgumentException("Unrecognized Direction.");
    }

    public MazeElement getNextUpElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                position.getX() / mCoef,
                (int) Math.ceil((position.getY() / ((float) mCoef))) - 1);
    }

    public MazeElement getNextDownElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                position.getX() / mCoef,
                (position.getY() / mCoef) + 1);
    }

    public MazeElement getNextRightElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                (position.getX() / mCoef) + 1,
                position.getY() / mCoef);
    }

    public MazeElement getNextLeftElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                (int) Math.ceil((position.getX() / ((float) mCoef)) - 1),
                position.getY() / mCoef);
    }

    public HashMap<Direction, MazeElement>
    getPossibleDirections(Vector2D position, Direction direction) {
        HashMap<Direction, MazeElement> possibleDirections
                = new HashMap<Direction, MazeElement>();

        MazeElement element = getNextLeftElement(position);
        if (!(element instanceof Block) && direction != Direction.RIGHT
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.put(Direction.LEFT, element);
        }

        element = getNextRightElement(position);
        if (!(element instanceof Block) && direction != Direction.LEFT
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.put(Direction.RIGHT, element);
        }

        element = getNextUpElement(position);
        if (!(element instanceof Block)  && direction != Direction.DOWN
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.put(Direction.UP, element);
        }

        element = getNextDownElement(position);
        if (!(element instanceof Block) && direction != Direction.UP
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.put(Direction.DOWN, element);
        }

        return possibleDirections;
    }

    public LinkedList<MazeElement>
    getAvailableBlocks(Vector2D position) {
        LinkedList<MazeElement> possibleDirections
                = new LinkedList<MazeElement>();

        MazeElement element = getNextLeftElement(position);
        if (!(element instanceof Block)
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.add(element);
        }

        element = getNextRightElement(position);
        if (!(element instanceof Block)
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.add(element);
        }

        element = getNextUpElement(position);
        if (!(element instanceof Block)
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.add(element);
        }

        element = getNextDownElement(position);
        if (!(element instanceof Block)
                && !(element instanceof GhostHouseTile)) {
            possibleDirections.add(element);
        }

        return possibleDirections;
    }

    public Map.Entry<Direction, MazeElement>
    getBlockWithSmallestLabel(Vector2D position, Direction direction) {
        HashMap<Direction, MazeElement> availableBlocks
                = getPossibleDirections(position, direction);

        Map.Entry<Direction, MazeElement> result = null;
        int min = Integer.MAX_VALUE;

        for (Map.Entry<Direction, MazeElement> entry : availableBlocks.entrySet()) {
            int shortestPathLabel = entry.getValue().getShortestPathLabel();
            if (shortestPathLabel < min) {
                result = entry;
                min = entry.getValue().getShortestPathLabel();
            }
        }

        return result;
    }

    public double getDistance(Vector2D position1, Vector2D position2) {
        double diffX = position1.getX() - position2.getX();
        double diffY = position1.getY() - position2.getY();
        return Math.sqrt((diffX * diffX) + (diffY * diffY));
    }

    boolean isAtIntersection(Vector2D position, Direction direction) {
        if (isExactlyOnTile(position)) {
            int emptyBlockCounter = 0;
            if (!(getNextUpElement(position) instanceof Block)) {
                emptyBlockCounter++;
            }
            if (!(getNextDownElement(position) instanceof Block)) {
                emptyBlockCounter++;
            }
            if (!(getNextRightElement(position) instanceof Block)) {
                emptyBlockCounter++;
            }
            if (!(getNextLeftElement(position) instanceof Block)) {
                emptyBlockCounter++;
            }

            return (emptyBlockCounter >= 3 || (getNextElement(position, direction) instanceof Block));
        }

        else {
            return false;
        }
    }

    public boolean isExactlyOnTile(Vector2D position) {
        return position.getX() % 100 == 0 && position.getY() % 100 == 0;
    }

    /**
     * Fix the position of the element. If he went a little too far it repositions it.
     *
     * @param element The element to fix the position.
     */
    public void fixPosition(MovableGameElement element) {
        switch (element.getCurrentDirection()) {
            case LEFT:
                if (element.getPosition().getX() / ((float) mCoef)
                        - (element.getPosition().getX() / mCoef) < epsilon) {
                    element.getPosition().setX(element.getPosition().getX() / mCoef * mCoef);
                }
                break;
            case RIGHT:
                if ((element.getPosition().getX() / mCoef) + 1
                        - element.getPosition().getX() / ((float) mCoef) < epsilon) {
                    Vector2D old = new Vector2D(element.getPosition());
                    element.getPosition().setX(((element.getPosition().x / mCoef) + 1) * mCoef);
                }
                break;
            case UP:
                if (element.getPosition().y / ((float) mCoef)
                        - (element.getPosition().y / mCoef) < epsilon) {
                    element.getPosition().setY((element.getPosition().y / mCoef) * mCoef);
                }
                break;
            case DOWN:
                if ((element.getPosition().y / mCoef) + 1
                        - element.getPosition().y / ((float) mCoef) < epsilon) {
                    element.getPosition().setY(((element.getPosition().y / mCoef) + 1) * mCoef);
                }
                break;
        }
    }

    /**
     * Gets the {@link GameElement} at the element's position.
     * @param element The element used to get the position.
     * @return The {@link GameElement} at element's position.
     */
    public GameElement getElementAtPosition(GameElement element) {
        int x = element.getPosition().getX() / mCoef;
        int y = element.getPosition().getY() / mCoef;
        return mWorld.getMaze().getBlock(x, y);
    }

    /**
     * Gets the {@link GameElement} at the element's position.
     * @param position The position.
     * @return The {@link GameElement} at element's position.
     */
    public MazeElement getElementAtPosition(Vector2D position) {
        int x = position.getX() / mCoef;
        int y = position.getY() / mCoef;
        return mWorld.getMaze().getBlock(x, y);
    }

    /**
     * Gets the rounded position.
     * @param position The raw position.
     * @return The rounded position
     */
    public Vector2D getExactPosition(Vector2D position) {
        return new Vector2D(position.getX() / mCoef, position.getY() / mCoef);
    }

    public boolean isOnSameTile(Vector2D position1, Vector2D position2) {
        return getExactPosition(position1).equals(getExactPosition(position2));
    }

    /**
     * Checks if we are going through the tunnel and teleports the {@link MovableGameElement}
     *
     * @param movableGameElement The {@link MovableGameElement} that is going through th tunnel.
     */
    public void checkTunnel(MovableGameElement movableGameElement) {
    /* Handles TP */
        if ((movableGameElement.getPosition().x / mCoef) == mWorld.getMaze().getWidth() - 1) {
            movableGameElement.setPosition(new Vector2D(0, movableGameElement.getPosition().y));
        }

        if ((movableGameElement.getPosition().x / mCoef) == 0
                && movableGameElement.getCurrentDirection() == Direction.LEFT) {
            movableGameElement.setPosition(new Vector2D(27 * mCoef, movableGameElement.getPosition().y));
        }
    }

    /**
     * Eats a pellet and change it to an empty block.
     */
    public void eatPellet(GameElement gameElement) {
        Vector2D position = new Vector2D(gameElement.getPosition().getX() / mCoef,
                gameElement.getPosition().getY() / mCoef);

        Vector2D gameElementPosition = gameElement.getPosition();
        mWorld.getMaze().setBlock(new EmptyTile(gameElementPosition, mWorld),
                position.getX(), position.getY());

        long timeElapsed = TimeUtils.timeSinceMillis(mWorld.getStartTime());
        int baseScore;
        int timeMalus = (int) (timeElapsed / 1000);
        int frightenedDuration = 5;
        if (gameElement instanceof SuperPellet) {
            baseScore = 100;
            for (Ghost ghost : mWorld.getGhosts()) {
                if (ghost.isAlive()) {
                    ghost.setFrightenedTimer(frightenedDuration);
                }
            }
        }
        else {
            baseScore = 10;
        }
        mWorld.winPoint(baseScore - timeMalus);
        mWorld.getMaze().decreasePelletNumber();
    }

    /**
     * Checks if the pacman can go to the wanted direction or not.
     *
     * @param pacman          The {@link Pacman} that is moving.
     * @param wantedDirection The direction the {@link Pacman} wants to go.
     */
    public void checkWantedDirection(MovableGameElement pacman, Direction wantedDirection) {
        GameElement nextBlock = getNextElement(pacman.getPosition(), pacman.getWantedDirection());

        switch (wantedDirection) {
            case LEFT:
            case RIGHT:
                if (pacman.getPosition().getY() % 100 == 0) {
                    if (!(nextBlock instanceof Block)) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case UP:
                if (pacman.getPosition().getX() % 100 == 0) {
                    if (!(nextBlock instanceof Block)) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case DOWN:
                if (pacman.getPosition().getX() % 100 == 0) {
                    if (!(nextBlock instanceof Block) && !(nextBlock instanceof GhostHouseTile)) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
        }
    }

    /**
     * Moves the pacman on the {@link Maze}
     *
     * @param deltaTime The time elapsed between two renders.
     */
    public abstract void moveElement(MovableGameElement movableGameElement, float deltaTime);

    public abstract void updateEpsilon(float deltaTime);

    /**
     * Labels every MazeElement to find the shortest path.
     */
    public void findShortestPath(Vector2D positionObjective) {
        for (GameElement gameElement : mWorld.getMaze()) {
            if (gameElement instanceof MazeElement) {
                MazeElement mazeElement = (MazeElement) gameElement;
                mazeElement.setShortestPathLabel(Integer.MAX_VALUE);
            }
        }
        Queue<MazeElement> blockQueue = new LinkedList<MazeElement>();
        MazeElement beginningBlock = getElementAtPosition(positionObjective);
        MazeElement currentBlock;
        LinkedList<MazeElement> neighboursBlocks;
        int label = 0;

        blockQueue.add(beginningBlock);
        beginningBlock.setShortestPathLabel(label);

        while (!blockQueue.isEmpty()) {
            currentBlock = blockQueue.remove();
            label = currentBlock.getShortestPathLabel() + 1;
            neighboursBlocks = getAvailableBlocks(currentBlock.getPosition());
            for (MazeElement neighbour : neighboursBlocks) {
                if (neighbour.getShortestPathLabel() == Integer.MAX_VALUE) {
                    blockQueue.add(neighbour);
                    neighbour.setShortestPathLabel(label);
                }
            }

        }
    }
}

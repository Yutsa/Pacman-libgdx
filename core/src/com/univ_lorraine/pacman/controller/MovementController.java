package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.utils.TimeUtils;
import com.univ_lorraine.pacman.model.BasicPellet;
import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.EmptyTile;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.Maze;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;
import com.univ_lorraine.pacman.model.Pacman;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;

import java.util.HashMap;

/**
 * @author Édouard WILLISSECK
 */

public class MovementController {
    private World mWorld;
    private int mCoef;
    private double epsilon;

    public MovementController(World world) {
        setWorld(world);
        setCoef(100);
        epsilon = (mWorld.getPacman().getSpeed() / 6000f);
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

    public void setCoef(int coef) {
        mCoef = coef;
    }

    public int getCoef() {
        return mCoef;
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

    public GameElement getNextUpElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                position.getX() / mCoef,
                (int) Math.ceil((position.getY() / ((float) mCoef))) - 1);
    }

    public GameElement getNextDownElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                position.getX() / mCoef,
                (position.getY() / mCoef) + 1);
    }

    public GameElement getNextRightElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                (position.getX() / mCoef) + 1,
                position.getY() / mCoef);
    }

    public GameElement getNextLeftElement(Vector2D position) {
        return mWorld.getMaze().getBlock(
                (int) Math.ceil((position.getX() / ((float) mCoef)) - 1),
                position.getY() / mCoef);
    }

    public HashMap<Direction, GameElement>
    getPossibleDirections(Vector2D position, Direction direction) {
        HashMap<Direction, GameElement> possibleDirections
                = new HashMap<Direction, GameElement>();

        GameElement element = getNextLeftElement(position);
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
     * @param element The elemen to fix the position.
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
                if (!(nextBlock instanceof Block)) {
                    if (pacman.getPosition().y == nextBlock.getPosition().y) {
                        pacman.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case UP:
            case DOWN:
                if (!(nextBlock instanceof Block)) {
                    if (pacman.getPosition().x == nextBlock.getPosition().x) {
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
    public void moveElement(MovableGameElement movableGameElement, float deltaTime) {
        checkTunnel(movableGameElement);
        Vector2D currentPosition;
        GameElement currentGameElement;

        checkWantedDirection(movableGameElement, movableGameElement.getWantedDirection());

        GameElement nextBlock = getNextElement(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection());

        if (!(nextBlock instanceof Block) &&
                !((movableGameElement.getCurrentDirection() == Direction.DOWN)
                        && (nextBlock instanceof GhostHouseTile))) {
            movableGameElement.updatePosition(deltaTime);
        }

        fixPosition(movableGameElement);
        currentPosition = movableGameElement.getPosition();
        currentGameElement = mWorld.getMaze().getBlock(
                (currentPosition.getX() + 50) / mCoef,
                (currentPosition.getY() + 50) / mCoef);

        if (currentGameElement instanceof BasicPellet && movableGameElement instanceof Pacman) {
            eatPellet(currentGameElement);
        }

    }

    public GameElement getElementAtPosition(GameElement element) {
        int x = element.getPosition().getX() / mCoef;
        int y = element.getPosition().getY() / mCoef;
        return mWorld.getMaze().getBlock(x, y);
    }
    /**
     * Checks if we are going through the tunnel and teleports the {@link MovableGameElement}
     *
     * @param movableGameElement The {@link MovableGameElement} that is going through th tunnel.
     */
    public void checkTunnel(MovableGameElement movableGameElement) {
    /* Handles TP */
        if ((movableGameElement.getPosition().x / mCoef) == mWorld.getWidth() - 1) {
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
        mWorld.winPoint(10 - (int) (timeElapsed / 1000));
        mWorld.getMaze().decreasePelletNumber();
    }
}

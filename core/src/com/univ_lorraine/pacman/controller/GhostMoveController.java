package com.univ_lorraine.pacman.controller;

import com.univ_lorraine.pacman.model.Block;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.GhostHouseTile;
import com.univ_lorraine.pacman.model.MovableGameElement;
import com.univ_lorraine.pacman.model.MovableGameElement.Direction;
import com.univ_lorraine.pacman.model.Vector2D;
import com.univ_lorraine.pacman.model.World;

import java.util.HashMap;

import static com.univ_lorraine.pacman.model.MovableGameElement.Direction.DOWN;

/**
 * @author Édouard WILLISSECK
 */

public class GhostMoveController extends MovementController {
    private Ghost mGhost;
    public GhostMoveController(World world, Ghost ghost) {
        super(world);
        epsilon = (world.getGhosts().get(0).getSpeed() / 6000f);
        setGhost(ghost);
    }

    public Ghost getGhost() {
        return mGhost;
    }

    public void setGhost(Ghost ghost) {
        mGhost = ghost;
    }

    public HashMap<Direction, GameElement>
    getPossibleDirections(Ghost ghost, Direction direction) {
        Vector2D position = ghost.getPosition();
        HashMap<Direction, GameElement> possibleDirections
                = new HashMap<Direction, GameElement>();

        GameElement element = getNextLeftElement(position);
        if (!(element instanceof Block) && direction != Direction.RIGHT
                && (!ghost.isAlive() || !(element instanceof GhostHouseTile))) {
            possibleDirections.put(Direction.LEFT, element);
        }

        element = getNextRightElement(position);
        if (!(element instanceof Block) && direction != Direction.LEFT
                && (!ghost.isAlive() || !(element instanceof GhostHouseTile))) {
            possibleDirections.put(Direction.RIGHT, element);
        }

        element = getNextUpElement(position);
        if (!(element instanceof Block)  && direction != Direction.DOWN
                && (!ghost.isAlive() || !(element instanceof GhostHouseTile))) {
            possibleDirections.put(Direction.UP, element);
        }

        element = getNextDownElement(position);
        if (!(element instanceof Block) && direction != Direction.UP
                && (!ghost.isAlive() || !(element instanceof GhostHouseTile))) {
            possibleDirections.put(Direction.DOWN, element);
        }

        return possibleDirections;
    }

    // TODO: 26/03/17 Red ghost is sometimes blocked with the RIGHT direction when wanting to go up.
    @Override
    public void checkWantedDirection(MovableGameElement movableGameElement, Direction wantedDirection) {
        GameElement nextBlock = getNextElement(movableGameElement.getPosition(),
                movableGameElement.getWantedDirection());

        Ghost ghost = (Ghost) movableGameElement;

        switch (wantedDirection) {
            case LEFT:
            case RIGHT:
                if (movableGameElement.getPosition().getY() % 100 == 0) {
                    if (!(nextBlock instanceof Block)
                            && (!ghost.isAlive() || !(nextBlock instanceof GhostHouseTile))) {
                        movableGameElement.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case UP:
                if (movableGameElement.getPosition().getX() % 100 == 0) {
                    if (!(nextBlock instanceof Block)) {
                        movableGameElement.setCurrentDirection(wantedDirection);
                    }
                }
                break;
            case DOWN:
                if (movableGameElement.getPosition().getX() % 100 == 0) {
                    if (!(nextBlock instanceof Block)
                            && (!ghost.isAlive() || !(nextBlock instanceof GhostHouseTile))) {
                        movableGameElement.setCurrentDirection(wantedDirection);
                    }
                }
                break;
        }
    }

    @Override
    public void moveElement(MovableGameElement movableGameElement, float deltaTime) {
        checkTunnel(movableGameElement);

        checkWantedDirection(movableGameElement, movableGameElement.getWantedDirection());

        GameElement nextBlock = getNextElement(movableGameElement.getPosition(),
                movableGameElement.getCurrentDirection());

        if (!(nextBlock instanceof Block) &&
                !((movableGameElement.getCurrentDirection() == DOWN)
                        && (nextBlock instanceof GhostHouseTile) &&
                        ((Ghost) movableGameElement).isAlive())) {
            movableGameElement.updatePosition(deltaTime);
        }

        fixPosition(movableGameElement);
    }

    @Override
    public void updateEpsilon(float deltaTime) {
        float fps = 1 / deltaTime;
        float newEpsilon = mGhost.getSpeed() / (fps * World.getCoef());
        if (newEpsilon < 0.5) {
            epsilon = newEpsilon;
        }
        else {
            epsilon = 0.45;
        }
    }
}

package com.univ_lorraine.pacman.controller;

import com.badlogic.gdx.Gdx;
import com.univ_lorraine.pacman.model.Ghost;
import com.univ_lorraine.pacman.model.MovableGameElement;

/**
 * @author Édouard WILLISSECK
 */

public class SwitchAI extends GhostAI {
    private GhostAI usedAI;
    private GhostAI randomAI;
    private GhostAI searchAI;

    public SwitchAI(WorldRenderer worldRenderer) {
        super(worldRenderer);
    }

    @Override
    public void setGhost(Ghost ghost) {
        super.setGhost(ghost);
        randomAI = new RandomAI(mWorldRenderer);
        randomAI.setGhost(ghost);
        searchAI = new SearchPacmanAI(mWorldRenderer);
        searchAI.setGhost(ghost);
        usedAI = searchAI;
    }

    @Override
    public void setDirection(MovableGameElement movableGameElement) {
        Gdx.app.log(SwitchAI.class.getSimpleName(), "AI: " + usedAI.getClass().getSimpleName());
        usedAI.setDirection(movableGameElement);
        if (usedAI instanceof SearchPacmanAI) {
            usedAI = randomAI;
        }
        else {
            usedAI = searchAI;
        }
    }
}

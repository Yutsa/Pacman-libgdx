package com.univ_lorraine.pacman;

import com.badlogic.gdx.Game;
import com.univ_lorraine.pacman.screens.GameScreen;

public class PacmanGame extends Game {

    @Override
    public void create () {
        setScreen(new GameScreen());
    }
}
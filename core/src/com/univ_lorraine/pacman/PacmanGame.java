package com.univ_lorraine.pacman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.World;
import com.univ_lorraine.pacman.view.TextureFactory;

public class PacmanGame extends ApplicationAdapter {
	private SpriteBatch batch;

	private Texture pacmanTexture;
	private Texture blocTexture;
    private TextureFactory textureFactory;

    private World mWorld;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		textureFactory = TextureFactory.getInstance();

        /* Loads the different textures used in this project */
        pacmanTexture = textureFactory.getTexturePacman();
		blocTexture = textureFactory.getTextureBloc();

        mWorld = new World();
	}
	

	@Override
	public void render () {
		batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Iterates through the maze and renders the block */
        for (GameElement e : mWorld) {
            Vector2 position = e.getPosition();
            batch.draw(textureFactory.getTexture(e),
                    position.x * 48,
					position.y * 48);
        }
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        pacmanTexture.dispose();
        blocTexture.dispose();
	}
}

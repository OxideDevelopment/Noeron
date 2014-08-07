package com.oxidedevelopment.noeron.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.oxidedevelopment.noeron.Noeron;
import com.oxidedevelopment.noeron.world.World;

public class Game implements Screen {

	World world;
	SpriteBatch batch;
	OrthographicCamera camera;
	BitmapFont font;
	
	public Game(World world) {
		this.world = world;
		batch = Noeron.getBatch();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		world.draw();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		batch.end();
		camera.update();

		
		if(Gdx.input.isKeyPressed(Keys.G)) {
			world = World.generateWorld(new Random().nextLong());
			System.gc();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		
		//camera.zoom = 2f;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);
		camera.position.x = Gdx.graphics.getWidth() / 2;
		camera.position.y = Gdx.graphics.getHeight() / 2;
		
		//Get the font.
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("mainmenu/new/kenvector_future.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 15;
		font = generator.generateFont(parameter);
		generator.dispose();
		
		
		System.gc(); //We no longer need the Main Menu stuff, we should clean it up.
	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
	}

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

}

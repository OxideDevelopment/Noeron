package com.oxidedevelopment.noeron.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.oxidedevelopment.noeron.Noeron;
import com.oxidedevelopment.noeron.world.World;

public class MainMenu implements Screen {

	Stage stage;
	Music bgMusic;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		// Music
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("mainmenu/new/bgmusic.mp3"));
		bgMusic.play();
		bgMusic.setLooping(true);

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		// Play button
		Image up = new Image(new Texture("mainmenu/new/playbutton1.png"));
		Image down = new Image(new Texture("mainmenu/new/playbutton2.png"));
		Button playbutton = new Button(up.getDrawable(), down.getDrawable());
		playbutton.setPosition(Gdx.graphics.getWidth() / 2 - playbutton.getWidth() / 2, 300);
		playbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Noeron.changeScreen(new Game(World.generateWorld(new Random().nextLong())));
			}
		});

		// Header
		Image header = new Image(new Texture("mainmenu/noeron.png"));
		header.setPosition(Gdx.graphics.getWidth() / 2 - header.getWidth() / 2, 550);

		// Background
		Image bg = new Image(new Texture("mainmenu/new/bg.png"));

		stage.addActor(bg);
		stage.addActor(header);
		stage.addActor(playbutton);

		Gdx.app.debug("Main Menu", "Finished setting up Scene2D.");
	}

	@Override
	public void dispose() {
		stage.dispose();
		bgMusic.dispose();
	}

	// Unused methods
	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}

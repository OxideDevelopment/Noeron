package com.oxidedevelopment.noeron;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxidedevelopment.noeron.screens.MainMenu;

public class Noeron extends Game {

	static Noeron instance;
	SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		System.out.println("\n"); // Gradle build info is right on top of the debug info..
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		instance = this;
		setScreen(new MainMenu());
	}
	

	public static void changeScreen(Screen screen) {
		if(instance != null)
			instance.setScreen(screen);
		else
			Gdx.app.error("Noeron", "Change screen was called when the instance was not set.");
	}
	
	public static SpriteBatch getBatch() {
		if(instance != null)
			return instance.batch;
		else
			Gdx.app.error("Noeron", "getBatch() was called when the instance was not set.");
		
		return null;
	}
}

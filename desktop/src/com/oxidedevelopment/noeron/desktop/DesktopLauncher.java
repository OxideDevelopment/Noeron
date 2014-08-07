package com.oxidedevelopment.noeron.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oxidedevelopment.noeron.Noeron;
import com.oxidedevelopment.noeron.world.World;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL30 = true;
		
		config.width = ((int) 1280 / World.BLOCK_SIZE) * World.BLOCK_SIZE;
		config.height = ((int) 720 / World.BLOCK_SIZE) * World.BLOCK_SIZE;
		
		new LwjglApplication(new Noeron(), config);
	}
}
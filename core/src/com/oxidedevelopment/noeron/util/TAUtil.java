package com.oxidedevelopment.noeron.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.oxidedevelopment.noeron.Noeron;
import com.oxidedevelopment.noeron.world.Location;

public class TAUtil {
	
	TextureAtlas atlas;
	SpriteBatch batch;
	
	public TAUtil(String atlas) {
		this.atlas = new TextureAtlas(atlas);
		batch = Noeron.getBatch();
	}
	
	//TODO: Store this in a HashMap, and retrieve the sprite from the hashmap. 
	public void draw(String spriteName, Location location) {
		Sprite sprite = atlas.createSprite(spriteName);
		sprite.setPosition(location.x, location.y);
		sprite.draw(batch);
	}
	
	public void dispose() {
		atlas.dispose();
	}

}

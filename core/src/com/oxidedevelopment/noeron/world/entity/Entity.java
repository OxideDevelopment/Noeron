package com.oxidedevelopment.noeron.world.entity;

import com.oxidedevelopment.noeron.world.Location;

public interface Entity {

	public Location getLocation();
	public void setLocation();
	public void draw();

}

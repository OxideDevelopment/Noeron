package com.oxidedevelopment.noeron.world;

import com.oxidedevelopment.noeron.util.TAUtil;

public class Block {

	BlockType type;
	Location location;
	TAUtil atlasUtil;
	
	public Block(BlockType type, Location location) {
		this.type = type;
		this.location = location;
		atlasUtil = new TAUtil("game/tpdata.txt");
	}

	public BlockType getBlockType() {
		return type;
	}

	public void setBlockType(BlockType type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void draw() {
		switch(type) {
		
		case Dirt:
			break;
		case Grass:
			atlasUtil.draw("grassMid", location);
			break;
		default:
			break;
		
		}
		
	}
	
	public void dispose() {
		atlasUtil.dispose();
	}
}

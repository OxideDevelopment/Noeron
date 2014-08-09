package com.oxidedevelopment.noeron.world;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.oxidedevelopment.noeron.world.entity.Entity;
import com.oxidedevelopment.noeron.world.entity.Player;

public class World {
	/*
	 * ================================ World ================================
	 * 
	 * What this needs:
	 * 
	 * The player Mobs Blocks HUD
	 */

	public static final int BLOCK_SIZE = 70;
	
	private Player player;
	private List<Block> blocks;
	private List<Entity> mobs;
	
	public World() {
		player = new Player();
		blocks = new ArrayList<Block>();
		mobs = new ArrayList<Entity>();
	}

	public Player getPlayer() {
		return player;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public List<Entity> getMobs() {
		return mobs;
	}

	public void editBlockAt(Location loc, Block newBlock) {
		for(Block block: blocks) {
			if(block.getLocation() == loc) {
				blocks.remove(block);
				blocks.add(newBlock);
			}
		}
	}
	
	public void editBlockAt(Block oldBlock, Block newBlock) {
		for(Block block: blocks) {
			if(block == oldBlock) {
				blocks.remove(block);
				blocks.add(newBlock);
			}
		}
	}
	/**
	 * Only use this if you know what you're doing, this could cause a lot of things to mess up.
	 * @param block Block to add.
	 */
	private void addBlock(Block block) {
		blocks.add(block);
	}
	/**
	 * Only use this if you know what you're doing, this could cause a lot of things to mess up.
	 * @param block Block to remove.
	 */
	@SuppressWarnings("unused")
	private void removeBlock(Block block) {
		blocks.remove(block);
	}
	
	public void dispose() {
		for(Block block: blocks) {
			block.dispose();
		}
	}
	
	public void saveGame(String savePath) {
		Gdx.app.log("World", "Saving game...");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath));
			oos.writeObject(this);
			oos.close();
			
			Gdx.app.log("World", "World saved to " + savePath);
		} catch (IOException exception) {
			Gdx.app.error("World","Error saving world. Got: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
	
	public void draw() {
		for(Block block: blocks) {
			block.draw();
		}
		
		//for(Entity mob: mobs) {
		//	mob.draw();
		//}
		
		//player.draw();
	}

	// Static functions
	public static World loadSave(String savePath) {
		Gdx.app.log("World", "Loading world.");
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath));
			World savedWorld = (World) ois.readObject();
			ois.close();
			
			Gdx.app.log("World", "World loaded successfully.");
			
			return savedWorld;
		} catch (IOException exception) {
			Gdx.app.error("World", "Error loading world. Got: " + exception.getMessage());
			exception.printStackTrace();
		} catch(ClassNotFoundException exception) {
			Gdx.app.error("World", "Error loading world. Got: " + exception.getMessage());
			exception.printStackTrace();
		}
		
		return null;
	}
	
	public static World generateWorld(long seed){
		Random random = new Random(seed);
		World world = new World();
		int blocksToGenerateX = Gdx.graphics.getWidth() / BLOCK_SIZE;
		int blocksToGenerateY = Gdx.graphics.getHeight() / BLOCK_SIZE;
		int currentBlock = 0;
		
		//Generate the starting block.
		Block starterBlock = new Block(BlockType.Grass, new Location(currentBlock * BLOCK_SIZE, random.nextInt(blocksToGenerateY) * BLOCK_SIZE));
		Gdx.app.debug("World Geneation", "StarterBlock location: " + starterBlock.getLocation().x + " " + starterBlock.getLocation().y);
		world.addBlock(starterBlock);
		currentBlock++;
		
		//Go up, down, or stay the same for the blocks.
		Block lastBlock = starterBlock;
		for(int i = currentBlock; i < blocksToGenerateX; i++) {
			int upDownSame = random.nextInt(3); //0 = go down, 1 = same, 2 = go up
			
			Block generatedBlock = null;
			if (upDownSame == 0) { //Go Down
				int x = i * BLOCK_SIZE;
				int y = (int) (lastBlock.getLocation().y - BLOCK_SIZE); //LibGDX's (0, 0) coordinate is at the bottom left. (I think..)
				generatedBlock = new Block(BlockType.Grass, new Location(x, y));
				
			} else if (upDownSame == 1) { // Stay the same
				int x = i * BLOCK_SIZE;
				int y = (int) lastBlock.getLocation().y;
				generatedBlock = new Block(BlockType.Grass, new Location(x, y));
				
			} else if (upDownSame == 2) { // Go Up
				int x = i * BLOCK_SIZE;
				int y = (int) (lastBlock.getLocation().y + BLOCK_SIZE);
				generatedBlock = new Block(BlockType.Grass, new Location(x, y));
			} else {
				Gdx.app.error("World Generation", "upDownSame was not in the 0-2 range. Iteration = " + i);
			}
			
			world.addBlock(generatedBlock);
			lastBlock = generatedBlock;
		}
		
		return world;
	}
}

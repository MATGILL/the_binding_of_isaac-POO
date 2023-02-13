package gameloop;

import gameWorld.GameWorld;
import gameobjects.Hero;
import gameobjects.Spider;
import libraries.StdDraw;
import libraries.Timer;
import resources.Controls;
import resources.DisplaySettings;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;


public class Main
{
	public static void main(String[] args)
	{
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, ImagePaths.ISAAC);
		Spider spider = new Spider(RoomInfos.POSITION_CENTER_OF_ROOM);
		GameWorld world = new GameWorld(isaac, spider);				
		initializeDisplay();
		while(!StdDraw.isKeyPressed(Controls.goUp)) {
			StdDraw.picture(0.5, 0.5, ImagePaths.START_SCREEN);
			if(StdDraw.isKeyPressed(Controls.Magdalen)) {//chose Magdalen
				isaac.setImagePath(ImagePaths.MAGDALENE);
			}
			if(StdDraw.isKeyPressed(Controls.Gaper)) {//Gaper 
				isaac.setImagePath(ImagePaths.GAPER);
			}
			if(StdDraw.isKeyPressed(Controls.Isaac)) {//Isaac
				isaac.setImagePath(ImagePaths.ISAAC);
			}
			StdDraw.picture(0.5, 0.5, isaac.getImagePath());
			StdDraw.picture(0.5, 0.5, isaac.getImagePath(), RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
			
			StdDraw.show();
		}
		StdDraw.clear();
		// Main loop of the game
		while (!world.gameOver())
		{
			processNextStep(world);
		}
	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.changeRoom();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		world.drawLevel();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}

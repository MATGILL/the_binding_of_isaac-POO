package resources;

import libraries.Vector2;

public class HeroInfos
{
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double ISAAC_SPEED = 0.01;
	public static final int LIFE = 6;
	public static final int TEARS_DAMMAGES = 1;
	public static final int TEARS_RANGE=10;
	public static final double TEARS_SPEED = 0.03;
	public static final Vector2 TEARS_SIZE =  RoomInfos.TILE_SIZE.scalarMultiplication(0.5);
	
}

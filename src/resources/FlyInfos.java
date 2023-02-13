package resources;

import libraries.Vector2;

public class FlyInfos {
	public static Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final int LIFE = 3;
	public static final int FLY_DAMMAGES = 1;
	public static final double FLY_SPEED = 0.002;
	public static final int FLY_PROJECTILE_DAMMAGES = 1;
	public static final int FLY_PROJECTILE_RANGE=10;
	public static final double FLY_PROJECTILE_SPEED = 0.008;
	public static final Vector2 FLY_PROJECTILE_SIZE =  RoomInfos.TILE_SIZE.scalarMultiplication(0.5);
}

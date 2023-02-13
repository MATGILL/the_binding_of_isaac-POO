package resources;

import libraries.Vector2;

public class BoosInfos {
    public static Vector2 SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
    public static final int LIFE = 15;
    public static final int DAMMAGE = 2;
    public static final double SPEED = 0.0015;
    public static final int PROJECTILE_DAMMAGES = 2;
    public static final double PROJECTILE_SPEED = 0.008;
    public static final Vector2 PROJECTILE_SIZE =  RoomInfos.TILE_SIZE.scalarMultiplication(0.5);
}
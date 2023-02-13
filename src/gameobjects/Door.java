package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Door extends Obstacles{
	private double rotation;
	private Boolean open;
	private String direction;
	
	/*---------------------------------Constructeur-----------------------------*/
	public Door(Vector2 position, double rotation, String direction) {
		super(position,RoomInfos.BIG_TILE_SIZE);
		this.setImagePath(ImagePaths.CLOSED_DOOR);
		this.rotation=rotation;
		this.open=false;
		this.setDirection(direction);
	}
	/*---------------------------------M�thodes---------------------------------*/
	public  void draw() {
		if(this.open==false) {
			StdDraw.picture(this.getPosition().getX(),this.getPosition().getY(), this.getImagePath(), this.getRotation());
		}
		else{
			this.setImagePath(ImagePaths.OPENED_DOOR);
			StdDraw.picture(this.getPosition().getX(),this.getPosition().getY(), this.getImagePath(), this.getRotation());
			
		}
		
	}
	
	public void collision(Vector2 position, Vector2 size) {
		
	}

	/*---------------------------------getters & setters------------------------*/
	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public String getDirection() {
		return this.direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	

}

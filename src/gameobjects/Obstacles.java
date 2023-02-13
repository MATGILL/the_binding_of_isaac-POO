package gameobjects;


import libraries.Vector2;
import resources.RoomInfos;

public abstract class Obstacles {
	//param�tres :
	private Vector2 size;
	private String imagePath;
	private Vector2 position;
	private int damage;
	/*--------------------------------constructeur-----------------------------------------*/
	public Obstacles(Vector2 position) {
		this.setPosition(position);
		this.size=RoomInfos.HALF_TILE_SIZE;
		}
	
	public Obstacles(Vector2 position,Vector2 size) {
		this.setPosition(position);
		this.size=size;
	}
	

	
	public abstract void draw();
	
	public void giveDamage(Life life) {
		life.setPv(life.getPv()-this.getDamage());
	}
	
	/*---------------------------------getters & setters-----------------------------------*/
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Vector2 getPosition() {
		return position;
	}


	public void setPosition(Vector2 position) {
		this.position = position;
	}


	public Vector2 getSize() {
		return size;
	}


	public void setSize(Vector2 size) {
		this.size = size;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}

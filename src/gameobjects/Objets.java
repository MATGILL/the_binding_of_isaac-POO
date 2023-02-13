package gameobjects;

import libraries.Vector2;
import resources.RoomInfos;

public abstract class Objets {
	
	private String nom;
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private int prix;
		
	public Objets(Vector2 position) {
		this.setPosition(position);
		this.size=RoomInfos.HALF_TILE_SIZE;
	}

	//drawgameObjetc
	//updateGameObject pas sur
	/*-------------------------------méthodes de la fonctions---------------------*/
	
	public abstract Boolean collision(Hero hero);
	public abstract void drawGameObject();
	public abstract void effects(Hero hero);
	
	
	/*-------------------------------getters & setters----------------------------*/
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
		
}

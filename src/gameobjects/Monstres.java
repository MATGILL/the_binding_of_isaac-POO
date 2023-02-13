package gameobjects;

import java.util.ArrayList;

import libraries.Physics;
import libraries.Vector2;

public abstract class Monstres {
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int life;
	private int damage;
	protected ArrayList<Fly_Projectile> projectiles = new ArrayList<Fly_Projectile>();
	//constructeur
	
	public  Monstres(Vector2 position) {
		this.position = position;
	}
	
	public abstract  void updateGameObject(Hero hero, ArrayList<Obstacles>obstacles,ArrayList<Monstres>monstres);
	
	public abstract void drawGameObject();
	
	
	public void takeDamage(ArrayList<Projectiles>tears) {
		for(int i =0;i<tears.size();i++) {
			if(Physics.rectangleCollision(this.getPosition(), this.getSize(), tears.get(i).getPosition(), tears.get(i).getSize())) {
				this.life= this.life - tears.get(i).getDamage();
				System.out.println(this.getClass().getName()+" "+	this.life);
				tears.remove(i);
				}
		}
	}
	
	
	/********************getters setters********************************************/
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Vector2 getDirection() {
		return direction;
	}
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setProjectiles(ArrayList<Fly_Projectile>projectiles) {
		this.projectiles=projectiles;
	}
	public ArrayList<Fly_Projectile>getProjectiles() {
		return projectiles;
	}
	
	
}

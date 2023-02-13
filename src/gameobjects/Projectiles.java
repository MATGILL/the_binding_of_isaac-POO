package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class Projectiles {
	/*
	 * a besoin d'une direction
	 * d'une position
	 * d'une taille
	 * d'une méthode movements
	 * d'un système de collisions
	 * de nbr de dégat
	 * une image
	 * 
	 * Sera presque similaire au projectile tiré par la mouche
	 */
	
	protected Vector2 direction;
	protected Vector2 position;
	private String imagePath;
	protected double speed;
	protected Vector2 size;
	protected int damage;
	private Vector2 finalDestination;
	private double range;
	
	public Projectiles(Vector2 direction, Vector2 position, String imagePath, Vector2 size, double speed, int damage) {
		this.setDirection(direction);
		this.setPosition(position);
		this.setImagePath(imagePath);
		this.setSize(size);
		this.setSpeed(speed);
		this.damage=damage;
		this.range=0.5;
		this.finalDestination=createFinalDestination();
	}
	
	public void updateGameObject()
	{
		move();
	}
	
	//gestion du déplacement de la larme(il faudra prendre en compte les collisions
	public void move() {
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		
	}
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	

	
	public Vector2 createFinalDestination() {
		if(this.direction.getX()==0 && this.direction.getY()==1) { //vers le nord
			return new Vector2(this.getPosition().getX(),this.getPosition().getY()+range);
		}
		if(this.direction.getX()==getPosition().getX() && this.direction.getY()==getPosition().getY()-10) {//vers le sud
			return new Vector2(this.getPosition().getX(),this.getPosition().getY()-range);
		}
		if(this.direction.getX()==getPosition().getX()+10 && this.direction.getY()==getPosition().getY()) {//vers le Est
			return new Vector2(this.getPosition().getX()+range,this.getPosition().getY());
		}
		if(this.direction.getX()==getPosition().getX()-10 && this.direction.getY()==getPosition().getY()) {//vers le Ouest
			return new Vector2(this.getPosition().getX()-range,this.getPosition().getY());
		}
		return null;
	}
	
	//getters & setters
	
	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Vector2 getSize() {
		return size;
	}
	public void setSize(Vector2 size) {
		this.size=size;
	}
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed=speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Vector2 getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(Vector2 finalDestination) {
		this.finalDestination = finalDestination;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}
	
	
}

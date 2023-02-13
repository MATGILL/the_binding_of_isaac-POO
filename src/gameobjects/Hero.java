package gameobjects;

import java.util.ArrayList;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.HeroInfos;



public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	public Life life;
	public ArrayList<Projectiles> tears = new ArrayList<Projectiles>();
	private Inventaire inventaire;
	private Boolean invincibility;
	private String nextRoomDirection = "";
	public int coolDownSpikes=20;
	private int damage;

	public Hero(Vector2 position, Vector2 size, double speed, String imagePath)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.life=new Life(HeroInfos.LIFE);
		this.inventaire=new Inventaire();
		this.invincibility=false;
		this.damage=1;
	}

	public void updateGameObject(ArrayList<Monstres>monstres, ArrayList<Obstacles>obstacles, ArrayList<Door>door)
	{
		nextRoomDirection = "";
		move(monstres, obstacles, door);
		
		//actualise les larmes tir�e par le hero
		
		for(int i =0; i<this.tears.size();i++) {
			this.tears.get(i).updateGameObject();
			if(Physics.rectangleCollision(tears.get(i).getPosition(), tears.get(i).getSize(), tears.get(i).getFinalDestination(), tears.get(i).getSize())) {
				tears.remove(i);
			}

		}
		//actualisation du coolDown pour les spikes
		if(coolDownSpikes<15 && coolDownSpikes!=0) {
			coolDownSpikes--;
		}
		else {
			coolDownSpikes=15;
		}
		
	}

	private void move(ArrayList<Monstres>monstres, ArrayList<Obstacles>obstacles, ArrayList<Door>door)
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		if(correctPosition(positionAfterMoving,monstres,obstacles, door)) setPosition(positionAfterMoving);

		direction = new Vector2();
	}
	
	//verifie que la future position est valide 
	private boolean correctPosition(Vector2 positionAfterMoving,ArrayList<Monstres>monstres, ArrayList<Obstacles>obstacles, ArrayList<Door>door) {
		for(int i =0;i<monstres.size();i++) {
			if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), monstres.get(i).getPosition(), monstres.get(i).getSize())) return false;
		}
		
		for(int i = 0;i<door.size();i++) {
			if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), door.get(i).getPosition(), door.get(i).getSize()) && door.get(i).getOpen()==true) {
				nextRoomDirection = door.get(i).getDirection();
				return true;
			}
		}
		
		for(int i =0;i<obstacles.size();i++) {
			if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), obstacles.get(i).getPosition(), obstacles.get(i).getSize())) {
				if(obstacles.get(i).getClass().getName()=="gameobjects.Wall"){
					return false;
				}
				else if(obstacles.get(i).getClass().getName()=="gameobjects.Piques") {
					if(coolDownSpikes==15) {
						hit(obstacles.get(i).getDamage());
						coolDownSpikes--;
					}
					return true;
				}
			}
		}
		return true;
	}

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public void hit(int damage) {
		if(this.invincibility==false) {
			life.setPv(life.getPv()- damage);
		}
	}
	

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}
	
	/*
	 * Shooting from key input
	 */
	public void shootUp() {
		Vector2 tearDirection = new Vector2(0, 1);
		tears.add( new Projectiles(tearDirection, getPosition(), ImagePaths.TEAR, HeroInfos.TEARS_SIZE, HeroInfos.TEARS_SPEED, damage ) );
	}
	public void ShootDown() {
		Vector2 tearDirection = new Vector2(getPosition().getX(), getPosition().getY()-10);
		tears.add( new Projectiles(tearDirection, getPosition(), ImagePaths.TEAR, HeroInfos.TEARS_SIZE, HeroInfos.TEARS_SPEED, damage ) );
	}
	public void ShootRight() {
		Vector2 tearDirection = new Vector2(getPosition().getX()+10, getPosition().getY());
		tears.add( new Projectiles(tearDirection, getPosition(), ImagePaths.TEAR, HeroInfos.TEARS_SIZE, HeroInfos.TEARS_SPEED, damage ) );
	}
	public void ShootLeft() {
		Vector2 tearDirection = new Vector2(getPosition().getX()-10, getPosition().getY());
		tears.add( new Projectiles(tearDirection, getPosition(), ImagePaths.TEAR, HeroInfos.TEARS_SIZE, HeroInfos.TEARS_SPEED, damage ) );
	}

	
	
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}


	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{

		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}

	public Boolean getInvincibility() {
		return invincibility;
	}

	public void setInvincibility(Boolean invincibility) {
		this.invincibility = invincibility;
	}
	
	public String getNextRoomDirection() {
		return nextRoomDirection;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}

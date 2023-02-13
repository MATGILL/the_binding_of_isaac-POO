package gameobjects;

import java.util.ArrayList;

import libraries.Physics;
import libraries.Vector2;
import resources.FlyInfos;
import resources.ImagePaths;

public class Fly_Projectile extends Projectiles{
	private double coefficientX = 0 ;
	private double coefficientY = 0 ;
	private Vector2 initial = new Vector2();
	

	public Fly_Projectile(Vector2 direction, Vector2 position) {
		super(direction, position, ImagePaths.TEAR, FlyInfos.FLY_PROJECTILE_SIZE, FlyInfos.FLY_PROJECTILE_SPEED, FlyInfos.FLY_PROJECTILE_DAMMAGES);
		initial = position ;
		moveCoeficient();
		
		Vector2 directionNorm = new Vector2(direction.getX(),direction.getY());
	}
	
	public void moveCoeficient() {
		if(direction.getX() > position.getX()){
			if(direction.getY() > position.getY()) {
				double AB = Math.abs(direction.getX() - position.getX());								
				double BC = Math.abs(direction.getY() - position.getY());

				double distance = (double) Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );

				setCoefficientX((speed/distance) * AB);
				setCoefficientY((speed/distance) * BC);
				
			}	
			else {
				double AB = Math.abs(direction.getX() - position.getX());
				double BC = Math.abs( position.getY() - direction.getY());
				double distance = (double)Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );
				setCoefficientX((speed/distance) * AB);
				setCoefficientY((speed/distance) * BC);
			}
		}
		else {
			if(direction.getY() > position.getY()) {
				double AB = Math.abs(position.getX() - direction.getX());
				double BC = Math.abs(direction.getY() - position.getY());
				double distance = (double) Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );
				setCoefficientX((speed/distance) * AB);
				setCoefficientY((speed/distance) * BC);
			}
			else{
				double AB = Math.abs(position.getX() - direction.getX());
				double BC = Math.abs( position.getY() - direction.getY());
				double distance = (double) Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );
				setCoefficientX((speed/distance) * AB);
				setCoefficientY((speed/distance) * BC);
			}
		}
	}

	public boolean updateGameObject(ArrayList<Obstacles> obstacles, Hero hero)
	{
		return move(obstacles,hero);
	}

	public boolean move(ArrayList<Obstacles> obstacles,Hero hero) {
		if(direction.getX() > initial.getX()){  
			if(direction.getY() > initial.getY()) {
				
				Vector2 positionAfterMoving = new Vector2(this.position.getX() + coefficientX,this.position.getY() + coefficientY);
				if(correctPosition(positionAfterMoving,hero,obstacles)) {
					setPosition(positionAfterMoving);
					return true;
				}
			}	
			else {
				Vector2 positionAfterMoving = new Vector2(this.position.getX() + coefficientX,this.position.getY() - coefficientY);
				if(correctPosition(positionAfterMoving,hero,obstacles)) {
					setPosition(positionAfterMoving);
					return true;
				}
			}
		}
		else {
			if(direction.getY() > initial.getY()) {
				Vector2 positionAfterMoving = new Vector2(this.position.getX() - coefficientX,this.position.getY() + coefficientY);
				if(correctPosition(positionAfterMoving,hero,obstacles)) {
					setPosition(positionAfterMoving);
					return true;
				}
			}
			else{ 
				Vector2 positionAfterMoving = new Vector2(this.position.getX() - coefficientX,this.position.getY() - coefficientY);
				if(correctPosition(positionAfterMoving,hero,obstacles)) {
					setPosition(positionAfterMoving);
					return true;
				}
			}
		}
		return false;
	}

	//verifie que la future position est valide 
	private boolean correctPosition(Vector2 positionAfterMoving,Hero hero, ArrayList<Obstacles>obstacles) {
		if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), hero.getPosition(), hero.getSize())) {
			hero.life.setPv(hero.life.getPv()-this.damage);  
			return false;
		}
		for(int i =0;i<obstacles.size();i++) {
			if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), obstacles.get(i).getPosition(), obstacles.get(i).getSize())) {
				return false;
			}
		}
		return true;
	}

	

	public double getCoefficientX() {
		return coefficientX;
	}

	public void setCoefficientX(double coefficientX) {
		this.coefficientX = coefficientX;
	}

	public double getCoefficientY() {
		return coefficientY;
	}

	public void setCoefficientY(double coefficientY) {
		this.coefficientY = coefficientY;
	}

}

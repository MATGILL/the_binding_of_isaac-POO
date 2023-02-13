package gameobjects;

import java.util.ArrayList;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.SpiderInfos;

/************log Spider***********************/
/*
 * Essayer d'am�liorer la fluidit� des d�placements
 * g�r� les collisions
 */
public class Spider extends Monstres {
	public int compteurMove = 30;
	public int compteurLife = 20;
	private int cooldown = 20;
	
	public Spider(Vector2 position) {
		super(position);
		this.setLife(SpiderInfos.LIFE);
		this.setSpeed(SpiderInfos.SPIDER_SPEED);
		this.setImagePath(ImagePaths.SPIDER);
		this.setSize(SpiderInfos.SPIDER_SIZE);
		this.setDamage(1);
	}
	//mise en place des mouvements
	
	@Override
	public void updateGameObject(Hero hero, ArrayList<Obstacles>obstacles, ArrayList<Monstres>monstres)
	{

		move(hero,obstacles);
		if(cooldown > 0)cooldown--;
		compteurMove--;
		if(compteurMove==0) {compteurMove=30;}
	}
	@Override
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	//TODO v�rifier et peut �tre modifier la cadence
		

	
	private void move(Hero hero, ArrayList<Obstacles>obstacles)
	{
		//new Vector2((random.nextDouble() * (1 - (-1)) + -1),(random.nextDouble() * (1 - (-1)) + -1))
		if(compteurMove== 30) {
			this.setDirection(new Vector2(-1 + Math.random()*2, -1+Math.random()*2));
			compteurMove--;
		}
		if(compteurMove<30 && compteurMove >10) {
			Vector2 normalizedDirection = getNormalizedDirection(this.getDirection());
			Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);	
			
			if(correctPosition(positionAfterMoving,hero,obstacles)) setPosition(positionAfterMoving);
			
			compteurMove--;
		}
	}
	
	//verifie que la future position est valide 
	private boolean correctPosition(Vector2 positionAfterMoving,Hero hero, ArrayList<Obstacles>obstacles) {
		if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), hero.getPosition(), hero.getSize())) {
			if(cooldown == 0) {
				hero.hit(getDamage());																							//si la prochaine position est le hero -> inflige degats
				cooldown = 20;
			}
			return true;
		}
		for(int i =0;i<obstacles.size();i++) {
			if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), obstacles.get(i).getPosition(), obstacles.get(i).getSize())) return false;
		}
		return true;
	}
	
	public Vector2 getNormalizedDirection(Vector2 direction)
	{

		Vector2 normalizedVector = new Vector2(direction);//modifer avec vecteur aléatoire
		normalizedVector.euclidianNormalize(this.getSpeed());
		return normalizedVector;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}


	
	
	


}

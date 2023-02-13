package gameobjects;

import java.util.ArrayList;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.FlyInfos;

public class Fly extends Monstres{
	public int compteurMove = 30;
	public int compteurLife = 20;
	private int cooldownCAC = 40;                        // cooldown degats au corps a corps
	private int cooldownProjectiles = 100 ;                // cooldown tir de projectiles



	public Fly(Vector2 position) {
		super(position);
		this.setLife(FlyInfos.LIFE);
		this.setSpeed(FlyInfos.FLY_SPEED);
		this.setImagePath(ImagePaths.FLY);
		this.setSize(FlyInfos.FLY_SIZE);
		this.setDamage(1);
	}
	//mise en place des mouvements

	@Override
	public void updateGameObject(Hero hero, ArrayList<Obstacles>obstacles,ArrayList<Monstres>monstres)
	{
		move(hero,obstacles);
		if(cooldownCAC > 0)cooldownCAC--;
		if(cooldownProjectiles > 0)cooldownProjectiles--;
		updateProjectile(hero,obstacles);    

	}
	@Override
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}


	private void move(Hero hero, ArrayList<Obstacles>obstacles)
	{

		this.setDirection(directionCentre(hero));
		Vector2 normalizedDirection = getNormalizedDirection(this.getDirection());
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);	
		if(correctPosition(positionAfterMoving,hero,obstacles)) setPosition(positionAfterMoving);

	}

	//verifie que la future position est valide 
	private boolean correctPosition(Vector2 positionAfterMoving,Hero hero, ArrayList<Obstacles>obstacles) {
		if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), hero.getPosition(), hero.getSize())) {
			return false;
		}
		for(int i =0;i<obstacles.size();i++) {
			if(Physics.rectangleCollision(positionAfterMoving, this.getSize(), obstacles.get(i).getPosition(), obstacles.get(i).getSize())) return false;
		}
		return true;
	}

	// permet de faire avance les projectiles
	public void updateProjectile(Hero hero,ArrayList<Obstacles>obstacles) {   
		
		// si les projectiles sont bloqué (par un obstacle) cela suprime le projectile
		ArrayList<Projectiles> temp_projectiles = new ArrayList<Projectiles>();
		for(int i=0; i<projectiles.size(); i++) {
			if(!this.projectiles.get(i).updateGameObject(obstacles,hero))temp_projectiles.add(projectiles.get(i));
			projectiles.get(i).drawGameObject();
		}
		for(int i=0; i<temp_projectiles.size(); i++) this.projectiles.remove(temp_projectiles.get(i));

		
		if(cooldownProjectiles == 0) {
			projectiles.add(new Fly_Projectile(hero.getPosition(), this.getPosition()));
			cooldownProjectiles = 100;
			System.out.println("shoot");
		}

	}


	//System permetant d'ajuste la direction de la mouche par rapport a la position du hero 
	public Vector2 directionCentre(Hero hero) {
		if(this.getPosition().getX()<hero.getPosition().getX()) {
			if (this.getPosition().getY()<hero.getPosition().getY()) return hero.getPosition();
			else return new Vector2(hero.getPosition().getX(),-hero.getPosition().getY());
		}
		else {
			if (this.getPosition().getY()<hero.getPosition().getY()) return new Vector2(-hero.getPosition().getX(),hero.getPosition().getY());
			else return new Vector2(-hero.getPosition().getX(),-hero.getPosition().getY());
		}
	}

	public Vector2 getNormalizedDirection(Vector2 direction)
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(this.getSpeed());
		return normalizedVector;
	}	
}

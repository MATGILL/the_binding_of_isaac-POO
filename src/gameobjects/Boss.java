package gameobjects;

import java.util.ArrayList;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.BoosInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Boss extends Monstres{
	public int compteurMove = 30;
	public int compteurLife = 20;
	private int cooldownCAC = 40;                       // cooldown degats au corps a corps
	private int cooldownProjectiles = 70;
	private int cooldownSpecial = 700;					// cooldown capacité special 
	public Life life;
	
	public Boss(Vector2 position, String imagePath, int level) {
		super(position);
		this.setLife(BoosInfos.LIFE);
		this.setSpeed(BoosInfos.SPEED);
		this.setSize(RoomInfos.TILE_SIZE);
		this.setDamage(BoosInfos.DAMMAGE);
		this.life = new Life(this.getLife());
		if(level ==1 ) this.setImagePath(ImagePaths.BOSS1);
		else if(level ==2 )this.setImagePath(ImagePaths.BOSS2);
	}


	//mise en place des mouvements

	@Override
	public void updateGameObject(Hero hero, ArrayList<Obstacles>obstacles,ArrayList<Monstres>monstres)
	{
		move(hero,obstacles);
		if(cooldownCAC > 0)cooldownCAC--;
		if(cooldownProjectiles > 0)cooldownProjectiles--;
		if(cooldownSpecial > 0)cooldownSpecial--;
		updateProjectile(hero,obstacles,monstres);    

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
	public void updateProjectile(Hero hero,ArrayList<Obstacles>obstacles,ArrayList<Monstres>monstres) {   
		
		// si les projectiles sont bloqu (par un obstacle) cela suprime le projectile
		ArrayList<Projectiles> temp_projectiles = new ArrayList<Projectiles>();
		for(int i=0; i<projectiles.size(); i++) {
			if(!this.projectiles.get(i).updateGameObject(obstacles,hero))temp_projectiles.add(projectiles.get(i));
			projectiles.get(i).drawGameObject();
		}
		for(int i=0; i<temp_projectiles.size(); i++) this.projectiles.remove(temp_projectiles.get(i));

		
		if(cooldownProjectiles == 0) {
			projectiles.add(new Fly_Projectile(hero.getPosition(), this.getPosition()));
			cooldownProjectiles = 70;
		}
		
		if(cooldownSpecial == 0) {
			monstres.add(new Fly(new Vector2(0.25,0.25)));
			monstres.add(new Spider(new Vector2(0.25,0.25)));
			monstres.add(new Spider(new Vector2(0.6,0.6)));
			cooldownSpecial = 700;
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

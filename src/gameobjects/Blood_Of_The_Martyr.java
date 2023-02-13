package gameobjects;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Blood_Of_The_Martyr extends Passif {
	private boolean pickUp;

/*-------------------------------------------constructeur------------------------------------------*/
	public Blood_Of_The_Martyr(Vector2 position) {
		super(position);
		this.setImagePath(ImagePaths.BLOOD_OF_THE_MARTYR);
		this.pickUp=true;
	}

	
/*-------------------------------------------méthode de la class-----------------------------------*/
	@Override
	public Boolean collision(Hero hero) {
		Boolean collid = Physics.rectangleCollision(hero.getPosition(), hero.getSize(), this.getPosition(), this.getSize());
		if(pickUp) {
			if(collid) {
				effects(hero);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public void effects(Hero hero) {
		//augmente les dégats du héro
		if(hero.getImagePath()==ImagePaths.ISAAC) {
			hero.setImagePath(ImagePaths.ISAAC_BLOOD);
		}
		if(hero.getImagePath()==ImagePaths.MAGDALENE) {
			hero.setImagePath(ImagePaths.MAGDALENE_BLOOD);
		}
		if(hero.getImagePath()==ImagePaths.GAPER) {
			hero.setImagePath(ImagePaths.GAPER_BLOOD);
		}
		hero.setDamage(hero.getDamage()+1);
	}


	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		
	}

/*-------------------------------------------getters & setters------------------------------------*/
	public boolean isPickUp() {
		return pickUp;
	}

	public void setPickUp(boolean pickUp) {
		this.pickUp = pickUp;
	}

}

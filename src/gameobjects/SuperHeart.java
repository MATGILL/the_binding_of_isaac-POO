package gameobjects;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class SuperHeart extends Passif {
	private Boolean pickUp; //ramassable ou non(false dans le shop)
	
/*---------------------------------------Constructeur------------------------------------------*/
	public SuperHeart(Vector2 position) {
		super(position);
		this.setImagePath(ImagePaths.SUPER_HEART);
		this.pickUp=true;
	}

/*---------------------------------------Méthodes de la class----------------------------------*/
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
		hero.life.setPvmax(hero.life.getPvmax()+2);
		hero.life.setPv(hero.life.getPvmax());
	}

	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		
	}


}

package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Piques extends Obstacles {
	

/*--------------------------------------------------Constructeur----------------------------------------------------*/	
	public Piques(Vector2 position) {
		super(position);
		this.setImagePath(ImagePaths.SPIKES);
		this.setDamage(1);
	}
/*--------------------------------------------------méthodes de la class--------------------------------------------*/
	@Override
	public void draw() {
		StdDraw.picture(this.getPosition().getX(), this.getPosition().getY(), this.getImagePath());
		
	}
	
/*--------------------------------------------------getters & setters-----------------------------------------------*/


}

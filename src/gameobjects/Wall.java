package gameobjects;


import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Wall extends Obstacles {
	
	public Wall(Vector2 position) {
		super(position);
		this.setImagePath(ImagePaths.ROCK);
	}
	
	
	/*---------------------------------Méthodes--------------------------------------------*/
	public void draw() {
		StdDraw.picture(this.getPosition().getX(), this.getPosition().getY(), this.getImagePath());
	}


}

package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Life {
	private int pv;
	private int pvmax;
	private Vector2 size;
	public final Vector2 position=new Vector2(0.11,0.95);
	
	
	public Life(int pvmax) {
		this.pv=pvmax;
		this.pvmax=pvmax;
		this.size=RoomInfos.TILE_SIZE.scalarMultiplication(0.5);
		
		
	}
	
	public void drawgameObject() {
		int coeurEntier;
		int demiCoeur;
		double decallage=0;
		//méthode pour mettre 1/2 1 2 2/2 ect coeur
		if(pv%2==0) {
			//coeur entier
			coeurEntier = pv/2;	
			for(int i =0;i<coeurEntier;i++) {
				StdDraw.picture(position.getX()+decallage, position.getY(), ImagePaths.HEART_HUD, getSize().getX(), getSize().getY(),
						0);
				decallage+=0.06;
			}
			
		}
		else if(pv%2==1){
			//coeur entier+demi coeur
			coeurEntier = pv/2;
			demiCoeur = 1;
			for(int i =0;i<coeurEntier;i++) {
				StdDraw.picture(position.getX()+decallage, position.getY(), ImagePaths.HEART_HUD, getSize().getX(), getSize().getY(),
						0);
				decallage+=0.06;
			}
			for(int y=0;y<demiCoeur;y++) {
				StdDraw.picture(position.getX()+decallage, position.getY(), ImagePaths.HALF_HEART_HUD, getSize().getX(), getSize().getY(),
						0);
			}
		}
		

		
		
	}
	

	
	
	
	//getteres & setters
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public int getPvmax() {
		return pvmax;
	}
	public void setPvmax(int pvmax) {
		this.pvmax = pvmax;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
	
}

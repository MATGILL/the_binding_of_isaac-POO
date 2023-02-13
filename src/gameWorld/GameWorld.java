package gameWorld;

import java.awt.Font;
import gameobjects.Hero;
import gameobjects.Spider;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.ImagePaths;

public class GameWorld
{
	public int compteur =20;
	public int compteurCheat=20;
	private Room currentRoom;
	private Hero hero;
	public Map level1;
	public Map level2;
	private String level;
	public Map map;
	public Vector2 coord;
	//private Spider spider; // faire une liste de mob avec des coordonn�es al�atoire(ou pr�d�finis)
	

	// A world needs a hero
	public GameWorld(Hero hero, Spider spider)
	{
		this.hero = hero;
		this.level1 = new Map(10, 10, hero,1);
		this.level2 = new Map(10,10,hero,2);
		this.map=level1;
		currentRoom = map.getMap()[4][3];
		coord = new Vector2(4,3);
		this.level="level : 1";
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeyFoShoot();
		processKeyForCheating();
	}

	public boolean gameOver()
	{
		if(this.hero.life.getPv()<=0) {
			StdDraw.picture(0.5, 0.5, ImagePaths.LOSE_SCREEN);
			StdDraw.show();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void drawLevel() {
		Font font = new Font("Arial", Font.BOLD, 30);
		StdDraw.setFont(font);
	    StdDraw.text(0.90 , 0.95,this.getLevel() );
	}
	
	public void changeRoom() {
		String changingRoom = hero.getNextRoomDirection();
		if(changingRoom!="") {
			if(changingRoom=="N") {
				currentRoom=map.getMap()[(int)coord.getX()-1][(int)coord.getY()];
				coord.setX(coord.getX()-1); coord.setY(coord.getY());
				this.hero.setPosition(new Vector2(0.5,0.2));//remet en bas le h�ros sinon il reste bloqu� dans le mur
			}
			else if(changingRoom=="S") {
				currentRoom=map.getMap()[(int)coord.getX()+1][(int)coord.getY()];
				coord.setX(coord.getX()+1); coord.setY(coord.getY());
				this.hero.setPosition(new Vector2(0.5,0.8));//remet en bas le h�ros sinon il reste bloqu� dans le mur
			}
			else if(changingRoom=="E") {
				currentRoom=map.getMap()[(int)coord.getX()][(int)coord.getY()-1];
				coord.setX(coord.getX()); coord.setY(coord.getY()-1);
				this.hero.setPosition(new Vector2(0.8,0.5));//remet en bas le h�ros sinon il reste bloqu� dans le mur
			}
			else if(changingRoom=="O") {
				currentRoom=map.getMap()[(int)coord.getX()][(int)coord.getY()+1];
				coord.setX(coord.getX()); coord.setY(coord.getY()+1);
				this.hero.setPosition(new Vector2(0.2,0.5));
			}
			else if(changingRoom=="level2") {
				this.map=this.level2;
				currentRoom=this.level2.getMap()[0][0];
				coord.setX(0); coord.setY(0);
				this.hero.setPosition(new Vector2(0.5,0.2));
				this.setLevel("level : 2");
			}
		}
	}
	
	public void openDoors() {
		if(currentRoom.allMobsAreDead()) {
			currentRoom.openDoors();
		}
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
		openDoors();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}

	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
	}
	
	private void processKeyFoShoot() {
		
		if((StdDraw.isKeyPressed(Controls.ShootUp) || StdDraw.isKeyPressed(Controls.ShootDown) || StdDraw.isKeyPressed(Controls.ShootRight) || StdDraw.isKeyPressed(Controls.ShootLeft)) && compteur==20 ) {
			if (StdDraw.isKeyPressed(Controls.ShootUp))
			{
				hero.shootUp();
			}
		
			else if (StdDraw.isKeyPressed(Controls.ShootDown))
			{
				hero.ShootDown();
			}
			else if (StdDraw.isKeyPressed(Controls.ShootRight))
			{
				hero.ShootRight();
			}
			else if (StdDraw.isKeyPressed(Controls.ShootLeft))
			{
				hero.ShootLeft();
			}
			compteur--;
		}
		else if(compteur<20) {
			compteur--;
		}
		if(compteur==0) {
			compteur=20;
		}
	}
	
	private void processKeyForCheating() {
		
		if(StdDraw.isKeyPressed(Controls.invincibility)) {
			this.hero.setInvincibility(true);
		}
		if(StdDraw.isKeyPressed(Controls.cheatSpeed)) {
			if(hero.getSpeed()<0.02) {			
				hero.setSpeed(hero.getSpeed()+0.01);
			}
		}
		if(StdDraw.isKeyPressed(Controls.killMobs)) {
			for(int i=0;i<this.currentRoom.monstres.size();i++) {
				this.currentRoom.monstres.remove(i);
			}
		}
		if(StdDraw.isKeyPressed(Controls.giveCoin)) {
			hero.getInventaire().setCoins(hero.getInventaire().getCoins()+10);
		}
		if(StdDraw.isKeyPressed(Controls.fullDammaged)) {
			hero.setDamage(1000);
		}
		
	}
	/*---------------------------getters & setters------------------------------------*/
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}

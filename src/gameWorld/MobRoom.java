package gameWorld;

import java.util.ArrayList;

import gameobjects.Door;
import gameobjects.Hero;
import gameobjects.Monstres;
import libraries.Vector2;


public class MobRoom extends Room {
	
	/*-------------paramètres--------------------*/
	

	
	//Listes Objets(type + Position)
	
	
	
	/*-------------Constructeur-----------------*/
	public MobRoom(Hero hero,ArrayList<Monstres> monstres,  ArrayList<Door> doors) {
		super(hero, doors, monstres);
		this.monstres=monstres;
		hero.setPosition(new Vector2(0.5,0.2));
		// TODO Auto-generated constructor stub
	}
	
	/*-------------Méthodes--------------------*/
	public void updateRoom() {
		super.updateRoom();
		//TODO rajouter makeMonstre
		updateMob();
		//peut êtres rajouter updateObstacles pour les doors
	}
	
	
	public void drawRoom() {
		super.drawRoom();
		//TODO rajouter les obstacles & les monstres
	}
	
	public void updateMob() {
		
		
	}
	

}

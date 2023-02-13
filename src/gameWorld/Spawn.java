package gameWorld;

import java.util.ArrayList;

import gameobjects.Door;
import gameobjects.Hero;
import gameobjects.Monstres;


public class Spawn extends Room {
	
	
	
	
	public Spawn(Hero hero, ArrayList<Door> doors,ArrayList<Monstres> monstres) {
		super(hero, doors, monstres);
	}
	
	public void updateRoom()
	{
		super.updateRoom();		
	}
	

	
	public void drawRoom() {
		super.drawRoom();
	}
	


}

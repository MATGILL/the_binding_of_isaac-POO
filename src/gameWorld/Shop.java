package gameWorld;

import java.util.ArrayList;


import gameobjects.Blood_Of_The_Martyr;
import gameobjects.Coins;
import gameobjects.Door;
import gameobjects.Heart;
import gameobjects.Hero;
import gameobjects.Monstres;
import gameobjects.Pair;
import gameobjects.SuperHeart;
import libraries.Vector2;

public class Shop extends Room {
	//
	
	private ArrayList<Pair>ListeObjetsDuShop = new ArrayList<Pair>();

/*--------------------------------constructeur ---------------------------------------*/
	public Shop(Hero hero, ArrayList<Door> doors, ArrayList<Monstres> monstres) {
		super(hero, doors, monstres);
		this.objets.add(new Blood_Of_The_Martyr(new Vector2(0.5,0.5)));
		createListeObjets();
	}

/*---------------------------------méthodes de la class------------------------------*/
	
	//méthode qui va généré une liste d'objet compris entres 1 et 3 d'objets qui seront en vente dans le chop
	public void createListeObjets() {

		//position première père :
		ListeObjetsDuShop.add(new Pair(new Coins(new Vector2(0.7,0.7),15, false),new Blood_Of_The_Martyr(new Vector2(0.77,0.7))));
		ListeObjetsDuShop.add(new Pair(new Coins(new Vector2(0.7,0.5),15,false),new Heart(new Vector2(0.77,0.5))));
		ListeObjetsDuShop.add(new Pair(new Coins(new Vector2(0.7,0.3),15,false),new SuperHeart(new Vector2(0.77,0.3))));
	}
	
	public void updateRoom()
	{
		super.updateRoom();
		
		//collison spécifique au objets en vente
		for(int j=0;j<this.ListeObjetsDuShop.size();j++) {
			if(this.ListeObjetsDuShop.get(j).getCoins().collision(this.getHero())) {
				this.ListeObjetsDuShop.get(j).getObjets().effects(this.getHero());
				this.ListeObjetsDuShop.remove(j);
			}
		}
		
	}
	

	
	public void drawRoom() {
		super.drawRoom();
		drawListeObjets();
		
	}
	
	public void drawListeObjets() {
		for(int i = 0;i<ListeObjetsDuShop.size();i++) {
			ListeObjetsDuShop.get(i).getCoins().drawGameObject();
			ListeObjetsDuShop.get(i).getObjets().drawGameObject();
		}
	}
	
/*---------------------------------getters & setters --------------------------------*/

	public ArrayList<Pair> getListeObjetsDuShop() {
		return ListeObjetsDuShop;
	}

	public void setListeObjetsDuShop(ArrayList<Pair> listeObjetsDuShop) {
		ListeObjetsDuShop = listeObjetsDuShop;
	}


}

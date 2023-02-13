package gameobjects;

import java.awt.Font;
import java.util.ArrayList;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Inventaire {
	private int coins;
	public final Vector2 position=new Vector2(0.11,0.90);
	public final Vector2 objets_hud_size =RoomInfos.TILE_SIZE.scalarMultiplication(0.5);
	private ArrayList<Objets> listeObjet = new ArrayList<Objets>();
	//rajouter plus tard les autres objets
	
	public Inventaire() {
		this.setCoins(0);
	}
	
/*------------------------------------méthodes de la classe-------------------------------*/
	public void drawgameObject() {
		//affichage des pièce non final
		StdDraw.picture(position.getX(), position.getY(), ImagePaths.COIN, objets_hud_size.getX(), objets_hud_size.getY(),
				0);
		StdDraw.setPenColor(StdDraw.WHITE);
		Font font = new Font("Arial", Font.BOLD, 30);
		StdDraw.setFont(font);
	    StdDraw.text(0.17 , 0.90, String.valueOf(coins));
	    
	    //pour le reste des objets on les affichera si non null

	}
/*------------------------------------getters & setters-----------------------------------*/
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public ArrayList<Objets> getListeObjet() {
		return listeObjet;
	}

	public void setListeObjet(ArrayList<Objets> listeObjet) {
		this.listeObjet = listeObjet;
	}
}

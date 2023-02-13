package gameobjects;

import java.util.Random;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Coins extends Consommables {
//pièce de 1, 5 ou 10 qui vont directement dans l'inventaire du Héro
	private int value;
	private Boolean pickUp;
	
	public Coins(Vector2 position) {
		super(position);
		this.setNom("Coin");
		this.setValue(createValue());
		this.setImagePath(createImagePath());
		this.setPrix(0);
		this.pickUp=true;
	}
	
	//constructeur que l'on utilisera dans le SHOP
	public Coins(Vector2 position, int prix, Boolean pickUp) {
		super(position);
		this.setNom("Coin");
		this.setValue(prix);
		this.setImagePath(createImagePath());
		this.setPrix(prix);
		this.pickUp=pickUp;
	}
	

/*---------------------méthode de la classe---------------------------*/
	
	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		
	}
	
	//va ajouter la valeurs de la pièce à l'invetaire du héro
	//pas sur enfait vuyq que dans le shop ca enlève des pièces de l'inventaire rajouter un boolean savoir si on est dans le shop ou non ?
	//le retour de type boolean servira à supprimer de la liste des objets de la room les coins si il y a collision
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
			if(collid && hero.getInventaire().getCoins()>=this.value) {
				hero.getInventaire().setCoins(hero.getInventaire().getCoins()-this.value);
				return true;
			}
			else{
				return false;
			}
		}
		
	}
	
	//fonction qui réalise les effets de Coin
	@Override
	public void effects(Hero hero) {
		hero.getInventaire().setCoins(hero.getInventaire().getCoins()+this.value);
	}
	

	//cette fonction va générer un chiffre aléatoire entres 0 et 3, et donnera la valeur 1, 5 ou 10 à la pièce
	public int createValue() {
		Random random = new Random();
		int nb;
		nb = random.nextInt(2);
		if(nb==0) {
			return 1;
		}
		else if(nb==1) {
			return 5;
		}
		else {
			return 10;
		}
	}
	
	//cette fonction va généré le imagePath en fonctions de la value de la pièce
	public String createImagePath() {
		if(this.value==1) {
			return ImagePaths.NICKEL;
		}
		else if(this.value==5) {
			return ImagePaths.DIME;
		}
		else{
			return ImagePaths.COIN;
		}
	}

/*----------------------getteres & setters--------------------------*/
	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}

	public Boolean getPickUp() {
		return pickUp;
	}

	public void setPickUp(Boolean pickUp) {
		this.pickUp = pickUp;
	}





}

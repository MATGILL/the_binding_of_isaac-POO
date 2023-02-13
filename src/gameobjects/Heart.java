package gameobjects;

import java.util.Random;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Heart extends Consommables{
/*
 * Cette class permet de créé des coeur qui une fois ramasser vont redonner des point de vie au héro, différents du <3 qui va rajouter des pv max a ISAAC
 * 
 */
	
	private int value;
	private Boolean pickUp;
/*------------------------------constructeur-----------------------------*/	
	public Heart(Vector2 position) {
		super(position);
		this.setNom("Heart");
		this.value=createValue();
		this.setImagePath(choseImagePath());
		this.setSize(RoomInfos.HALF_TILE_SIZE);
		this.pickUp=true;
	}
/*------------------------------méthode de la focntion-------------------*/
	
	
	
	public int createValue() {
		Random random = new Random();
		int nb = random.nextInt(1);
		if(nb==0) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	public String choseImagePath() {
		if(value==1) {
			return ImagePaths.HEART_PICKABLE;
		}
		else {
			return ImagePaths.HALF_HEART_PICKABLE;
		}
	}
	
	@Override
	public Boolean collision(Hero hero) {
		Boolean collid = Physics.rectangleCollision(hero.getPosition(), hero.getSize(), this.getPosition(), this.getSize());
		if(pickUp) {
			if(collid && hero.life.getPv()<hero.life.getPvmax()) {
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
		//hero.life.getPv()<hero.life.getPvmax()
		if(hero.life.getPv()<hero.life.getPvmax()) {
			if(hero.life.getPvmax()-hero.life.getPv()+this.value <=0) {
				hero.life.setPv(hero.life.getPv()+this.value-1);
			}
			else {
				hero.life.setPv(hero.life.getPv()+this.value);
			}
		}
	}

	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		
	}
/*-------------------------------getters & setters-----------------------*/
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}





}

package gameWorld;

import java.util.ArrayList; //utilikser pours crï¿½ï¿½ des listes de mob
import java.util.Random;


import gameobjects.Blood_Of_The_Martyr;
import gameobjects.Coins;
import gameobjects.Door;
import gameobjects.Heart;
import gameobjects.Hero;
import gameobjects.Monstres;
import gameobjects.Objets;
import gameobjects.Obstacles;
import gameobjects.SuperHeart;
import gameobjects.Wall;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public abstract class Room
{
	private Hero hero;
	//liste des portes de la salle
	public ArrayList<Door> doors = new ArrayList<Door>();
	
	//Liste des mur de la salle
	public ArrayList<Obstacles>obstacles = new ArrayList<Obstacles>();
	
	//Liste des monstres dans la room
	public ArrayList<Monstres> monstres=new ArrayList<Monstres>();

	//Liste des objets dans la piï¿½ce(au sol)
	public ArrayList<Objets> objets=new ArrayList<Objets>();
	
	public int compteurLoot=1;

	public Room(Hero hero,ArrayList<Door> doors, ArrayList<Monstres> monstres )
	{
		this.setHero(hero);
		this.doors=doors;
		this.obstacles=createBorder();
		this.monstres=monstres;
		this.objets=new ArrayList<Objets>();

	}
	
	/*----------------------create the border-----------------------------*/
	public ArrayList<Obstacles> createBorder() {
		ArrayList<Obstacles>obstacles= new ArrayList<Obstacles>();
		for(double i = RoomInfos.TILE_WIDTH;i<1 - RoomInfos.TILE_WIDTH;i+=RoomInfos.TILE_WIDTH/3) {
			obstacles.add(new Wall(new Vector2(i, RoomInfos.TILE_WIDTH)));
			
			obstacles.add(new Wall(new Vector2(RoomInfos.TILE_WIDTH,i)));
			
			obstacles.add(new Wall(new Vector2(i,1-RoomInfos.TILE_WIDTH)));
			

			obstacles.add(new Wall(new Vector2(1-RoomInfos.TILE_WIDTH,i)));
		}
		return obstacles;
	}
	

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
		makeMonstreMove();
		for(int j=0;j<this.objets.size();j++) {
			if(this.objets.get(j).collision(this.hero)) {
				this.objets.remove(j);
			}
		}
		damageMonstre();
		
	}
	


	private void makeHeroPlay()
	{
		getHero().updateGameObject(monstres,obstacles, this.doors);
	}
	
	private void makeMonstreMove(){
		for(int i =0;i<this.monstres.size();i++) {
			this.monstres.get(i).updateGameObject(hero,obstacles,monstres);
		}
	}
	
	//fonction qui enlève les mobs quand ils sont supposé mort
	public void damageMonstre() {
		for(int i=0;i<this.monstres.size();i++) {
			this.monstres.get(i).takeDamage(hero.tears);
			if(this.monstres.get(i).getLife()<=0) {
				this.monstres.remove(i);
			}
		}
	}
	
	//mï¿½thode qui vï¿½rifie que tout les mob sont mort
	public Boolean allMobsAreDead() {
		if(this.monstres.isEmpty()) {
			if(this.getClass().getName()!="gameWorld.Spawn" && this.getClass().getName()!="gameWorld.Shop" && compteurLoot==1 ) {
				createLoot();
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	//mï¿½thode qui sert a ouvrir toute les portes de la room
	public void openDoors() {
		for(int i = 0;i<this.doors.size();i++) {
			this.doors.get(i).setOpen(true);
		}
	}
	
	public void createLoot() {
		   compteurLoot=0;
			Random random = new Random();
		   int numberOfItem = random.nextInt(2); //génère le nombre d'objet qui va apparaitre dans la room
		   while(numberOfItem>=0) {
			   adObjectRandom(random.nextInt(3),generateCoord());
			   numberOfItem--;
		   }
	}
	
	public Vector2 generateCoord() {  //génère les coordonnées des objets qui vont aparaitre
		   double x = (double) (Math.random() * (1-RoomInfos.TILE_WIDTH-0.05 - 0.15)) + 0.15;
		   double y = (double) (Math.random() * (1-RoomInfos.TILE_WIDTH-0.05 - 0.15)) + 0.15;
		   return new Vector2(x,y);
	}
	
	public void adObjectRandom(int random, Vector2 position) { //génère le type d'objet qui va être rajouter
		if(random ==0) {
			//heart
			this.objets.add(new Heart(position));
		}
		if(random ==1) {
			//blood_of_martyr
			this.objets.add(new Blood_Of_The_Martyr(position));
			
		}
		if(random ==2) {
			//superHeart
			this.objets.add(new SuperHeart(position));
			
		}
		if(random ==3) {
			//COINS
			this.objets.add(new Coins(position));
			
		}
		
	}

	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		/*-------------------------Bordure autour des murs--------------------*/
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 1; i < RoomInfos.NB_TILES-1; i++)
		{
			for (int j = 1; j < RoomInfos.NB_TILES-1; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		
		/*---------------------------DrawWall------------------------------*/
		for(int i =0;i<obstacles.size();i++) {
			this.obstacles.get(i).draw();
		}
		


		
		/*---------------------------DrawDoor------------------------------*/
		
		getHero().drawGameObject();
		//on affiche les larmes tirï¿½es par le hï¿½ro
		for(int i =0; i<getHero().tears.size();i++) {
			getHero().tears.get(i).drawGameObject();
		}
		//on affiche les monstres
		drawMonstre();
		//on affiche les portes
		drawDoor();
		//on affiche les points de vie du personnage
		getHero().life.drawgameObject();
		//on affiche l'inventaire
		getHero().getInventaire().drawgameObject();
		drawObjets();
		for(int i =0; i<monstres.size();i++) {
            for(int j =0; j<monstres.get(i).getProjectiles().size();j++) {
                monstres.get(i).getProjectiles().get(j).drawGameObject();
            }
        }
		
	}
	public void drawMonstre() {
		for(int i =0;i<this.monstres.size();i++) {
			this.monstres.get(i).drawGameObject();
		}
	}
	
	public void drawDoor() {
		for(int i =0; i<this.doors.size();i++) {
			this.doors.get(i).draw();
		}
	}
	
	public void drawObjets() {
		if(this.objets!=null) {
			for(int i = 0;i<objets.size();i++) {
				this.objets.get(i).drawGameObject();
			}
		}
	}

	
	/*---------------------getters & setters-----------------------------*/
	
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}


	public Hero getHero() {
		return hero;
	}


	public void setHero(Hero hero) {
		this.hero = hero;
	}
}

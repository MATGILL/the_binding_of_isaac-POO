package gameWorld;

import java.util.ArrayList;
import gameobjects.Boss;
import gameobjects.Door;
import gameobjects.Hero;
import gameobjects.Monstres;
import gameobjects.Piques;
import gameobjects.Spider;
import gameobjects.Wall;
import gameobjects.Fly;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Map {
	
	
	
	
	
	
	public Room [][]map;
	/*je fais une caseCorrecte pour v�rifier que les salles a coter de la ou est issac en soit une pour poser une door je via sprendre en r�f�rence le tableau de la map
		public static boolean caseCorrecte(int i, int j) {
			boolean valide = false;
			if (i<map.length && i>=1 && j<map[map.length-1].length && j>=1)valide = true;
			return valide;
		}
	// fonction pour v�rifier si il y'a une porte � mettre dans la salle et si oui de quel c�ter en, haut en bas � droite ou � gauche
	// elle est encore a completer pour faire apparaitre les doors sur l'affichage
	//	public static  void calculAdjacent() {
		//for (int i=0;i<map.length;i++) {
			//for (int j=0; j<map[map.length].length;j++) {
				//if(caseCorrecte(i,j-1) && map[i][j-1]==1) {}//ouest
				//if(caseCorrecte(i-1,j-1) && map[i-1][j-1]==1) {}//nord
				//if(caseCorrecte(i,j+1) && map[i][j+1]==1) {}//est
				//if(caseCorrecte(i+1,j) && map[i+1][j-1]==1) {}//sud
			/*}
		}
	*/ 
		//pour g�n�rer entre 0 et 1 pour voir si il y'a une salle ou non
		/*public static int entierAleatoire (int a, int b) {
			a=0;
			b=1;
			return ThreadLocalRandom.current().nextInt(a,b);
		}*/
		
/*-------------------------------------Constructeur du niveau de base--------------------------------------------*/
		public Map (int h,int l , Hero hero, int level ) {
			map = new Room [h][l];
			Spawn spawn;
			MobRoom mobroom1;
			MobRoom mobroom2;
			MobRoom boss;
			Room shop;
			
			if(level==1) {
				//***SPAWN***//

				ArrayList<Door> door = new ArrayList<Door>();
				door.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.03), 0,"N" )); 
				ArrayList<Monstres> monstresspawn = new ArrayList<Monstres>();
				spawn = new Spawn(hero, door, monstresspawn);
				spawn.obstacles.add(new Piques(new Vector2(1-RoomInfos.TILE_WIDTH-0.05,0.5)));
				this.map[4][3]=spawn;
				
				//*** mobRoom1***///
				ArrayList<Door> doorMobRoom1 = new ArrayList<Door>();
				//door1.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.04), 0 )); //nord
				doorMobRoom1.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90, "E" )); //salle mobroom2 EST
				doorMobRoom1.add(new Door(new Vector2(0.5,RoomInfos.TILE_WIDTH-0.04),180,"S"));//SUD
				doorMobRoom1.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
				//door1.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5), 270 )); //salle de spawn
				ArrayList<Monstres> monstreMobRoom1 = new ArrayList<Monstres>();
				monstreMobRoom1.add(new Fly(new Vector2(0.25,0.25)));
				monstreMobRoom1.add(new Spider(new Vector2(0.25,0.25)));
				monstreMobRoom1.add(new Spider(new Vector2(0.6,0.6)));
				mobroom1= new MobRoom(hero, monstreMobRoom1 ,doorMobRoom1);
				this.map[3][3]=mobroom1;
				
				//***SHOP***//
				ArrayList<Door> doorsShop = new ArrayList<Door>();
				doorsShop.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90,"E" ));
				ArrayList<Monstres>shopMob=new ArrayList<Monstres>();
				shop = new Shop(hero, doorsShop, shopMob);
				map[3][4]=shop;
				
				
				
			
				//*** MobRoom2***//
				ArrayList<Door> doorMobRoom2 = new ArrayList<Door>();
				doorMobRoom2.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
				doorMobRoom2.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.03), 0,"N" )); 
				ArrayList<Monstres> monstreMobRoom2 = new ArrayList<Monstres>();
				monstreMobRoom2.add(new Spider(new Vector2(0.25,0.25)));
				monstreMobRoom2.add(new Spider(new Vector2(0.6,0.6)));
				
				mobroom2= new MobRoom(hero, monstreMobRoom2 ,doorMobRoom2);
				map[3][2]=mobroom2;
				
				
				//***BossRoom***//
				
				ArrayList<Door> doorBoss1 = new ArrayList<Door>();
				doorBoss1.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.03), 0,"level2" )); 
				doorBoss1.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
				ArrayList<Monstres> monstreBoss = new ArrayList<Monstres>();
				monstreBoss.add(new Boss(new Vector2(0.5,0.5),ImagePaths.BOSS1,1));
				boss=new MobRoom(hero, monstreBoss,doorBoss1);
				map[2][2]=boss;

			}
			else if(level ==2) {
				/*---------------Spawn----------------------------------------------*/
				ArrayList<Door> door = new ArrayList<Door>();
				door.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
				ArrayList<Monstres> monstresspawnl2 = new ArrayList<Monstres>();
				spawn = new Spawn(hero, door, monstresspawnl2); 
				 map[0][0]=spawn;
				 
				 /*--------------mobRoom1-------------------------------------------*/
					Room mobroom1l2;	
					 ArrayList<Door> doorMobRoom1l2 = new ArrayList<Door>();
					doorMobRoom1l2.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90, "E" )); //salle mobroom2 EST
					doorMobRoom1l2.add(new Door(new Vector2(0.5,RoomInfos.TILE_WIDTH-0.04),180,"S"));//SUD
					ArrayList<Monstres> monstreMobRoom1l2 = new ArrayList<Monstres>();
					monstreMobRoom1l2.add(new Spider(new Vector2(0.35,0.25)));
					monstreMobRoom1l2.add(new Spider(new Vector2(0.3,0.6)));
					mobroom1l2= new MobRoom(hero, monstreMobRoom1l2 ,doorMobRoom1l2);
					mobroom1l2.obstacles.add(new Piques(new Vector2(0.2,0.3)));
					mobroom1l2.obstacles.add(new Piques(new Vector2(0.3,0.2)));
					this.map[0][1]=mobroom1l2;
				 /*------------mobRoom2------------------------------------------------*/
					//*** MobRoom2***//
					 Room mobroom2l2;
					 	ArrayList<Door> doorMobRoom2l2 = new ArrayList<Door>();
					doorMobRoom2l2.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.04), 0 ,"N")); //nord
					doorMobRoom2l2.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
					ArrayList<Monstres> monstreMobRoom2l2 = new ArrayList<Monstres>();
					monstreMobRoom2l2.add(new Fly(new Vector2(0.7,0.2)));
					monstreMobRoom2l2.add(new Spider(new Vector2(0.4,0.6)));
					mobroom2l2= new MobRoom(hero, monstreMobRoom2l2 ,doorMobRoom2l2);
					mobroom2l2.obstacles.add(new Piques(new Vector2(0.4,0.4)));
					this.map[1][1]=mobroom2l2;
				 /*------------mobRoom3--------------------------------------------------*/
					Room mobroom3;
				 	ArrayList<Door> doorMobRoom3l2 = new ArrayList<Door>();
				 	doorMobRoom3l2.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.04), 0,"N" )); //nord
				 	doorMobRoom3l2.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90, "E" )); //salle mobroom2 EST
				 	ArrayList<Monstres> monstreMobRoom3l3 = new ArrayList<Monstres>();
				 	monstreMobRoom3l3.add(new Fly(new Vector2(0.25,0.25)));
				 	monstreMobRoom3l3.add(new Fly(new Vector2(0.80,0.80)));
				 	monstreMobRoom3l3.add(new Spider(new Vector2(0.5,0.5)));
					mobroom3= new MobRoom(hero, monstreMobRoom3l3 ,doorMobRoom3l2);
					mobroom3.obstacles.add(new Piques(new Vector2(0.4,0.6)));
				
					this.map[1][2]=mobroom3;
					
				/*--------------------------mobRoom4--------------------------------------*/
					Room mobroom4;
					ArrayList<Door> doorMobRoom4l2 = new ArrayList<Door>();
					doorMobRoom4l2.add(new Door(new Vector2(0.5,RoomInfos.TILE_WIDTH-0.04),180,"S"));//SUD
					doorMobRoom4l2.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
					ArrayList<Monstres> monstreMobRoom4l2 = new ArrayList<Monstres>();
					monstreMobRoom4l2.add(new Fly(new Vector2(0.2,0.2)));
					monstreMobRoom4l2.add(new Spider(new Vector2(0.6,0.6)));
					monstreMobRoom4l2.add(new Spider(new Vector2(0.5,0.80)));
					mobroom4= new MobRoom(hero, monstreMobRoom4l2 ,doorMobRoom4l2);
					mobroom4.obstacles.add(new Wall(new Vector2(0.6,0.3)));
					mobroom4.obstacles.add(new Wall(new Vector2(0.3,0.3)));
					mobroom4.obstacles.add(new Piques(new Vector2(0.2,0.7)));
					mobroom4.obstacles.add(new Piques(new Vector2(0.6,0.7)));
				
				this.map[0][2]=mobroom4;
				/*-------------------------SHOP--------------------------------------------*/
					ArrayList<Door> doorsShopl2 = new ArrayList<Door>();
					doorsShopl2.add(new Door(new Vector2(0.5,RoomInfos.TILE_WIDTH-0.04),180,"S"));//SUD
					doorsShopl2.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
					doorsShopl2.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90, "E" ));
					ArrayList<Monstres>shopMobl2=new ArrayList<Monstres>();
					shop = new Shop(hero, doorsShopl2, shopMobl2);
					this.map[0][3]=shop;
					
				/*-------------------------MobRoom5----------------------------------------*/
					Room mobroom5; 	
					ArrayList<Door> doorMobRoom5l2 = new ArrayList<Door>();
					
					doorMobRoom5l2.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90, "E" )); //salle mobroom2 EST
					ArrayList<Monstres> monstreMobRoom5l2 = new ArrayList<Monstres>();
					monstreMobRoom5l2.add(new Fly(new Vector2(0.3,0.3)));
					monstreMobRoom5l2.add(new Fly(new Vector2(0.80,0.2)));
					monstreMobRoom5l2.add(new Spider(new Vector2(0.45,0.2)));
					monstreMobRoom5l2.add(new Spider(new Vector2(0.8,0.68)));
					mobroom5= new MobRoom(hero, monstreMobRoom5l2 ,doorMobRoom5l2);
					mobroom5.obstacles.add(new Piques(new Vector2(0.8,0.4)));
					mobroom5.obstacles.add(new Piques(new Vector2(0.2,0.7)));
					
					this.map[0][4]=mobroom5;
					
				/*-------------------------MobRoom6---------------------------------------*/
					Room mobroom6;
					ArrayList<Door> doorMobRoom6l2 = new ArrayList<Door>();
					doorMobRoom6l2.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.04),0, "N")); //nord
					doorMobRoom6l2.add(new Door(new Vector2(0.5,RoomInfos.TILE_WIDTH-0.04),180,"S"));//SUD
					ArrayList<Monstres> monstreMobRoom6l2 = new ArrayList<Monstres>();
					monstreMobRoom6l2.add(new Fly(new Vector2(0.8,0.8)));
					monstreMobRoom6l2.add(new Fly(new Vector2(0.4,0.4)));
					monstreMobRoom6l2.add(new Fly(new Vector2(0.25,0.4)));
					mobroom6= new MobRoom(hero, monstreMobRoom6l2 ,doorMobRoom6l2);
					mobroom6.obstacles.add(new Piques(new Vector2(0.4,0.2)));
					mobroom6.obstacles.add(new Piques(new Vector2(0.3,0.2)));
					mobroom6.obstacles.add(new Piques(new Vector2(0.2,0.2)));
				
					this.map[1][3]=mobroom6;
				/*--------------------------MobRoom7-------------------------------------*/
					Room mobroom7;
				 	ArrayList<Door> doorMobRoom7 = new ArrayList<Door>();
				 	doorMobRoom7.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.04), 0 ,"N")); //nord
				 	doorMobRoom7.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90, "E" )); //salle mobroom2 EST
				 	ArrayList<Monstres> monstreMobRoom7l2 = new ArrayList<Monstres>();
				 	monstreMobRoom7l2.add(new Spider(new Vector2(0.4,0.8)));
				 	monstreMobRoom7l2.add(new Spider(new Vector2(0.6,0.7)));
				 	monstreMobRoom7l2.add(new Spider(new Vector2(0.6,0.8)));
				 	monstreMobRoom7l2.add(new Spider(new Vector2(0.6,0.6)));
				 	mobroom7= new MobRoom(hero, monstreMobRoom7l2 ,doorMobRoom7);
				 	mobroom7.obstacles.add(new Piques(new Vector2(0.68,0.3)));
				 	mobroom7.obstacles.add(new Piques(new Vector2(0.54,0.47)));
				 	mobroom7.obstacles.add(new Wall(new Vector2(0.72,0.65)));
				 	mobroom7.obstacles.add(new Wall(new Vector2(0.24,0.79)));
				 	mobroom7.obstacles.add(new Wall(new Vector2(0.19,0.20)));
				 	this.map[2][3]=mobroom7;
				/*-------------------------MobRoom8-------------------------------------*/
					Room mobroom8;
					ArrayList<Door> doorMobRoom8 = new ArrayList<Door>();
				
					doorMobRoom8.add(new Door(new Vector2(RoomInfos.TILE_WIDTH-0.04,0.5), 90, "E" )); //salle mobroom2 EST
					doorMobRoom8.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
					ArrayList<Monstres> monstreMobRoom8 = new ArrayList<Monstres>();
					monstreMobRoom8.add(new Fly(new Vector2(0.25,0.25)));
					monstreMobRoom8.add(new Spider(new Vector2(0.3,0.3)));
					monstreMobRoom8.add(new Spider(new Vector2(0.6,0.2)));
					monstreMobRoom8.add(new Spider(new Vector2(0.2,0.6)));
					mobroom8= new MobRoom(hero, monstreMobRoom8 ,doorMobRoom8);
					mobroom8.obstacles.add(new Wall(new Vector2(0.2,0.4)));
					mobroom8.obstacles.add(new Wall(new Vector2(0.3,0.4)));
					mobroom8.obstacles.add(new Piques(new Vector2(0.68,0.32)));
					mobroom8.obstacles.add(new Piques(new Vector2(0.6,0.8)));
					this.map[2][2]=mobroom8;
				/*-----------------------------MobRoom9-----------------------------------*/
					 Room mobRoom9;
						ArrayList<Door> doorMobRoom9 = new ArrayList<Door>();
						doorMobRoom9.add(new Door(new Vector2(0.5,RoomInfos.TILE_WIDTH-0.04),180,"S"));//SUD
						doorMobRoom9.add(new Door(new Vector2(1-RoomInfos.TILE_WIDTH+0.04,0.5),-90,"O"));
						ArrayList<Monstres> monstreMobRoom9 = new ArrayList<Monstres>();
						monstreMobRoom9.add(new Fly(new Vector2(0.4,0.8)));
						monstreMobRoom9.add(new Spider(new Vector2(0.2,0.8)));
						monstreMobRoom9.add(new Fly(new Vector2(0.8,0.2)));
						monstreMobRoom9.add(new Fly(new Vector2(0.2,0.8)));
						mobRoom9= new MobRoom(hero, monstreMobRoom9 ,doorMobRoom9);
						mobRoom9.obstacles.add(new Wall(new Vector2(0.2,0.4)));
						mobRoom9.obstacles.add(new Wall(new Vector2(0.3,0.4)));
						mobRoom9.obstacles.add(new Wall(new Vector2(0.4,0.2)));
						this.map[2][1]=mobRoom9;
						
				/*-------------------------------BossRoom2--------------------------------*/
						
						Room boss2;
						 ArrayList<Door> doorB = new ArrayList<Door>();
						doorB.add(new Door(new Vector2(0.5,1-RoomInfos.TILE_WIDTH+0.04), 0 ,"N")); 
						ArrayList<Monstres> monstresspawnBoss2 = new ArrayList<Monstres>();
						monstresspawnBoss2.add(new Boss(new Vector2(0.5,0.5),ImagePaths.BOSS1,2));
						boss2 = new MobRoom(hero, monstresspawnBoss2 ,doorB);
						boss2.obstacles.add(new Piques(new Vector2(0.1,0.1)));
						boss2.obstacles.add(new Piques(new Vector2(0.2,0.1)));
						boss2.obstacles.add(new Piques(new Vector2(0.3,0.3)));
						boss2.obstacles.add(new Piques(new Vector2(0.4,0.3)));
						boss2.obstacles.add(new Piques(new Vector2(0.5,0.2)));
						boss2.obstacles.add(new Piques(new Vector2(0.4,0.1)));
						
						this.map[3][1]=boss2;
						
				
			}
			
			
					
					//.doors.addNewdoors
		}
		
	/*------------------------------------------------getters & setters-----------------------------------------*/
	public Room[][] getMap() {
		return this.map;
	}
	public void setMap(Room[][]map) {
		this.map=map;
	}
		
}

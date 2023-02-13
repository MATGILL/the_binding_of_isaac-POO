package gameobjects;

public class Pair {
	private Coins coins;
	private Objets objets;

/*----------------------------constructeur-------------------------------*/
	public Pair(Coins coins, Objets objets) {
		this.coins=coins;
		this.objets=objets;
	}
	
	
	
/*-----------------------------getters & setters-------------------------*/
	public Coins getCoins() {
		return coins;
	}
	public void setCoins(Coins coins) {
		this.coins = coins;
	}
	public Objets getObjets() {
		return objets;
	}
	public void setObjets(Objets objets) {
		this.objets = objets;
	}
}

package model;

public class Produkt {
	private String produktName;
	private double preis;

	public Produkt(String produktName, double preis){
		this.preis = preis;
		this.produktName = produktName;
	}

	public double getPreis(){
		return preis;
	}

	public String getName(){
		return produktName;
	}
	
	public void setPreis(double preis){
		this.preis = preis;
	}
	
	public void setName(String name){
		this.produktName = name;
	}
}

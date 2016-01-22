package model;

import java.util.Vector;

public class ProduktListe {
	private Vector<Produkt> liste;

	public ProduktListe(){
		liste = new Vector<Produkt>();
	}
	
	//Produkt hinzufügen
	public void addProdukt(Produkt produktToAdd){
		if(existProdukt(produktToAdd.getName())==-1){
			liste.add(produktToAdd);
		}
		else
			System.out.println("existiert schon");
	}
	
	//Produkt löschen
	public void delProdukt(String produktName){
		if(liste.size()>0){
			if(existProdukt(produktName)!= -1){
				liste.removeElementAt(existProdukt(produktName));
			}
			else
				System.out.println("Produkt existiert nicht");
		}
		else 
			System.out.println("liste ist leer");
	}
	
	//preis des produktes zurückgeben
	public double getPreisFromProdukt(String produktName){
		double preis = 0;
		if(liste.size()>0){
			if(existProdukt(produktName)!= -1){
				preis = liste.elementAt(existProdukt(produktName)).getPreis();
			}
		}
		return preis;
	}
	
	//prüfen ob element existiert -> rückgabe indexnummer oder -1 bei nicht existentem element
	public int existProdukt(String name){
		
		int index =-1;
		if(liste.size()>0){
			for(int i = 0; i < liste.size(); i++){
				if(liste.elementAt(i).getName().equals(name.toString())){				
					index = i;
				}
			}
		}
		return index;
	}
	
	//Grösse zurückgeben
	public int getSize(){
		return liste.size();
	}
	
	//Produkt an position zurückgeben
	public Produkt getProduktAt(int i){
		return liste.elementAt(i);
	}
}


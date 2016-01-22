package model;

import java.util.Vector;

public class ProduktListe {
	private Vector<Produkt> liste;

	public ProduktListe(){
		liste = new Vector<Produkt>();
	}
	
	//Produkt hinzuf�gen
	public void addProdukt(Produkt produktToAdd){
		if(existProdukt(produktToAdd.getName())==-1){
			liste.add(produktToAdd);
		}
		else
			System.out.println("existiert schon");
	}
	
	//Produkt l�schen
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
	
	//preis des produktes zur�ckgeben
	public double getPreisFromProdukt(String produktName){
		double preis = 0;
		if(liste.size()>0){
			if(existProdukt(produktName)!= -1){
				preis = liste.elementAt(existProdukt(produktName)).getPreis();
			}
		}
		return preis;
	}
	
	//pr�fen ob element existiert -> r�ckgabe indexnummer oder -1 bei nicht existentem element
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
	
	//Gr�sse zur�ckgeben
	public int getSize(){
		return liste.size();
	}
	
	//Produkt an position zur�ckgeben
	public Produkt getProduktAt(int i){
		return liste.elementAt(i);
	}
}


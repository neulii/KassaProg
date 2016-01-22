package model;

import java.util.Vector;

public class SchuldnerListe  {
	private Vector<Schuldner> alleSchuldner = new Vector<Schuldner>();		//Liste mit Schuldnern
	private long gesamtOffenerBetrag;	//wert in cent
	
	public SchuldnerListe(){
		
	}
	
	public SchuldnerListe(SchuldnerListe liste){
		this.alleSchuldner = liste.alleSchuldner;
		
	}
	//Schuldner in liste eintragen
	public int addEntry(Schuldner schuldner){
		String tempName = schuldner.getNameSchuldner();
		
		int schuldnerExistIndex = 0;
		boolean schuldnerExist = false;
		
		gesamtOffenerBetrag = gesamtOffenerBetrag + ((long)(1000 * schuldner.getOffenerBetrag()));			//gesamt offenen betrag berechnen
		
		if(alleSchuldner.size()>0 ){		//wenn liste schon einträge hat
			for(int i=0; i<alleSchuldner.size();i++){	//name wird gesucht
				if(alleSchuldner.elementAt(i).getNameSchuldner().equals(tempName)){
					schuldnerExistIndex = i;
					schuldnerExist = true;
										
					alleSchuldner.elementAt(i).addBetrag(schuldner.getOffenerBetrag());	//schulden eintragen
				}
			}
		}
		
		if(!schuldnerExist && !schuldner.getNameSchuldner().equals("")){	//wenn schuldner noch nicht existiert zur liste hinzufügen
			alleSchuldner.add(schuldner);
		}
		
		return schuldnerExistIndex;
	}
	
	//Schuldner aus liste löschen mit index
	public void delEntry(int index){

		if(index>=0){
			gesamtOffenerBetrag = gesamtOffenerBetrag - ((long)(1000 * alleSchuldner.elementAt(index).getOffenerBetrag()));
			alleSchuldner.remove(index);
		}
	}
	
	public void delEntry(Schuldner name){
		
		alleSchuldner.removeElement(name);
		
		
	}
	
	public Vector<Schuldner> getSchuldnerListe(){
		return alleSchuldner;
		
	}
	
	public Schuldner getSchuldner(String name){
		int index = 0;
		for(int i=0;i<alleSchuldner.size();i++){
			if(alleSchuldner.elementAt(i).getNameSchuldner().equals(name))
				index = i;
		}
	
		return alleSchuldner.elementAt(index);
	}
	
	//anzahl der einträge zurückgeben
	public int getSize(){
		return alleSchuldner.size();
	}
	
	//gesamt offenen betrag zurückgeben
	public double getGesamtOffenerBetrag(){
		//return (double)(gesamtOffenerBetrag)/1000;
		double temp_offen = 0;
		
		for(int i = 0; i<alleSchuldner.size(); i++){
			temp_offen = temp_offen + (long) (1000*(alleSchuldner.elementAt(i).getOffenerBetrag()));
		
		}
	
		return (double)(temp_offen)/1000;
	}
	
	public Vector<String> getNamensListe(){
		Vector<String> tempVector = new Vector<String>();
		
		for (int i =0; i<alleSchuldner.size();i++){
			tempVector.add(alleSchuldner.elementAt(i).getNameSchuldner());
		}
		return tempVector;
	}
	
	public void betragBezahlen(String schuldner, double betrag){
		alleSchuldner.elementAt(getIndexOfSchuldner(schuldner)).betragBezahlen(betrag);
		gesamtOffenerBetrag = gesamtOffenerBetrag - ((long)(betrag * 1000));
	}
	
	public int getIndexOfSchuldner(String name){
		
		for(int i=0; i<alleSchuldner.size();i++){
			if(alleSchuldner.elementAt(i).getNameSchuldner().equals(name)){
				return i;	
			}
		}
		return -1;	//0 falls name nicht enthalten in liste
	}
	
	public Schuldner getSchuldnerAt(int i){
		return alleSchuldner.elementAt(i);
	}
}

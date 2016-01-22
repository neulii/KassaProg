package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public class Schuldner {

	private String nameSchuldner;
	private long offenerBetrag;		//wert wird in cent umgerechnet um ungenauigkeiten zu vermeiden
	private String zuletztBezahlt;
	private Calendar zuletztBezahltDatum = new GregorianCalendar();

	//Konstruktor
	public Schuldner(String nameSchuldner, double offenerBetrag, String zuletztBezahlt){
		double temp = 1000 * offenerBetrag;		//umrechnung in Cent
		this.nameSchuldner = nameSchuldner;
		this.offenerBetrag = (long)temp;
		setZuletztBezahlt(zuletztBezahlt);
	}
	
	//Konstruktor nur mit Namen
	public Schuldner(String nameSchuldner){
		this(nameSchuldner,0,"");
	}
	
	//Konstruktor mit String Vector
	public Schuldner(Vector <String> schuldner){
		double temp = 0;
		this.nameSchuldner = schuldner.elementAt(0).toString();
		temp = Double.valueOf(schuldner.elementAt(1).toString());
		this.offenerBetrag = (long)(temp * 1000);
		
		this.zuletztBezahlt = schuldner.elementAt(2).toString();
		
		setZuletztBezahlt(zuletztBezahlt);
	}
	
	//Namen zurückgeben
	public String getNameSchuldner(){
		return nameSchuldner;
	}
	
	//betrag zurückgeben
	public double getOffenerBetrag(){
		double temp;
		temp = (double)offenerBetrag / 1000;
		return temp;
	}
	
	//datum wann zuletzt bezahlt zurückgeben
	public String getZuletztBezahlt(){
		
		int day = zuletztBezahltDatum.get(Calendar.DAY_OF_MONTH);
		int month = zuletztBezahltDatum.get(Calendar.MONTH) + 1;
		int year = zuletztBezahltDatum.get(Calendar.YEAR);
		
		this.zuletztBezahlt = Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
	
		return zuletztBezahlt;
	}
	
	//bestimmten betrag bezahlen
	public int betragBezahlen(double betrag){
		offenerBetrag = offenerBetrag - ((long)(betrag * 1000));
		
		Calendar actDatum = Calendar.getInstance();
		
		this.zuletztBezahltDatum = actDatum;
		
		return 0;
	}
	
	//Betrag addieren
	public int addBetrag(double betrag){
		offenerBetrag = offenerBetrag + ((long)(betrag * 1000));
		return 0;
	}
	
	//ganzen Betrag bezahlen
	public int ganzenBetragBezahlen(){
		offenerBetrag = 0;
		
		Calendar actDatum = Calendar.getInstance();
		this.zuletztBezahltDatum = actDatum;
		
		return 0;
	}
	
	//Datum setzen mit StringParameter
	public void setZuletztBezahlt(String zuletztDatum){
		
		SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");
		Date datum = new Date();
		
		try {
			//string in datum umwandeln
			datum = dformat.parse(zuletztDatum);
			zuletztBezahltDatum.setTime(datum);
		
			//falls kein korrektes Datum
		} catch (ParseException e) {
			//e.printStackTrace();
			
			zuletztBezahltDatum.set(Calendar.DAY_OF_MONTH, 1);
			zuletztBezahltDatum.set(Calendar.MONTH, Calendar.JANUARY);
			zuletztBezahltDatum.set(Calendar.YEAR, 1900);
			
		}
	}
	
	public void setNewSchuldnerName(String name){
		nameSchuldner = name;
	}
}

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utily.Xor;

public class KassaProgFile{
	
	File file;
	FileReader fr = null;
	FileWriter fw = null;
	StringBuffer sb = null;
	String fileString = "";
	String cryptKey = "";
	
	boolean isKassaProgFile;
	boolean crypted;
	String dataString;
	String protokoll;
	
	boolean fileCompleteRead;
	
	//Konstruktor
	public KassaProgFile(File file, boolean crypted, String cryptKey){
		this.file = file;
		this.isKassaProgFile= false;
		this.crypted = crypted;
		this.dataString = "";
		this.protokoll = null;
		this.fileCompleteRead = false;
		this.cryptKey = cryptKey;
		
		//getStringFromFile();
		//readInformationFromString();
		//showInformationOfFile();
	}
	
	//informationen von Datei auslesen
	public void showInformationOfFile() {
		if(fileCompleteRead){
			System.out.println("Kassaprog Datei:   " + isKassaProgFile);
			System.out.println("Verschlüsselung:   " + this.crypted);
			System.out.println("FileString:        " + dataString);
		}
		else
			System.out.println("Dateiinfos konnten noch nicht gelesen werden");
	}

	//dateiinformationen setzen
	public void createStringToWrite(boolean crypted,String dataString){
		this.crypted = crypted;
		this.dataString = dataString;
		
		String tempString = "";
		
		// @gorpassak@crypted:true@datastring:
		StringBuilder sb = new StringBuilder();
		//dateiinformation in string schreiben
		tempString = ("@gorpassak@crypted:" + this.crypted + "@datastring:");
		//verschüsselung
		tempString = Xor.xorCrypt(tempString, cryptKey);
		sb.append(tempString);
		//System.out.println("achtung   " +   sb.toString());
		//daten anhängen
		if(crypted){
			sb.append(Xor.xorCrypt(dataString,cryptKey));
		}
		else 
			sb.append(dataString);

		fileString = sb.toString();
		//System.out.println(fileString);
	}
	
	public void writeFile(){
		try {
			//System.out.println(fileString);
			fw = new FileWriter(file);
			fw.write(fileString);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fehler beim Schreiben der datei!");
		}
		finally {
			try 
			{
				fw.close();
			}
			catch (IOException e) {
				System.out.println("Fehler beim Schliessen der Datei!");
				
				e.printStackTrace();
			}
		}
	}
	
	public void readInformationFromString() {
		//informationen wie verschlüsselung und dateityp sind IMMER mit Xor-Verschlüsselung gespeichert
		//der Rest des dateistringes kommt drauf an of crypted true/false
		//überprüfung ob gültige kassaprog datei -> gorpassak -> kassaprog
		
		try {
			//auslesen ob Kassaprogfile vorliegt
			if(Xor.xorCrypt(fileString.substring(0,10), cryptKey).equals("@gorpassak")){
				isKassaProgFile = true;
		
				//auslesen ob verschlüsselt
				int positionOfCrypt = Xor.xorCrypt(fileString, cryptKey).indexOf("crypted");
				if(Xor.xorCrypt(fileString, cryptKey).substring(positionOfCrypt + 8, positionOfCrypt+12).equals("true")){
					this.crypted = true;
				}
				else if (Xor.xorCrypt(fileString, cryptKey).substring(positionOfCrypt + 8, positionOfCrypt+13).equals("false")) {
					this.crypted = false;	
				}
				else{
					System.out.println("Dateiinhalt konnte nicht gelesen werden!!");
					fileCompleteRead = false;
					return;
				}
				//wenn erfolgreich geprüft auf kassafile und verschlüsselung dataString auslesen
				
				int positionOfDataString = 0;
				positionOfDataString = Xor.xorCrypt(fileString, cryptKey).indexOf("datastring");
				if(crypted)
					dataString = Xor.xorCrypt(fileString, cryptKey).substring(positionOfDataString + "datastring".length()+1);		
				else
					dataString = fileString.substring(positionOfDataString + "datastring".length()+1);
				
				//wenn datei erfolgreich vollständig gelesen wurde
				fileCompleteRead = true;
			}
			else
			{
				System.out.println("Datei ist keine Kassaprog Datei!!");
			}
		} catch (Exception e) {
			System.out.println("Fehler beim Lesen der datei");
			fileCompleteRead = false;
			//e.printStackTrace();
		}
	}

	//gesamte Datei in String schreiben
	public String getStringFromFile(){
		
		this.fr = null;
		this.sb = null;
		this.fileString = "";
		
		//wenn datei existiert
		if(file.exists()){
			try {
				fr = new FileReader(file);
				sb = new StringBuffer();
				int ch;
				
				//datei zeichenweise auslesen und in stringbuffer speichern
				while( (ch=this.fr.read()) != -1 )
					this.sb.append((char)ch);
				this.fileString = this.sb.toString();
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					this.fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
		else
			System.out.println("Datei wurde nicht gefunden!! -> Auslesen abgebrochen");
		
		return fileString;
	}
	
	//Datenstring zurückgeben
	public String getDataString(){
		if(fileCompleteRead)
			return dataString;
		else return null;
	}
	
	public boolean isKassaProgFile(){
		return isKassaProgFile;
	}
}

package mainProgram;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import actionListener.BetragAusBezahlenWindow.BetragAusBezahlenAbbrechenButtonListener;
import actionListener.BetragAusBezahlenWindow.BetragAusBezahlenBestatigenButtonListener;
import actionListener.BetragBezahlenWindow.BetragBezahlenAbbrechenButtonListener;
import actionListener.BetragBezahlenWindow.BetragBezahlenBestatigenButtonListener;
import actionListener.EinkaufVerrechnenWindow.EinkaufVerrechnenAbbrechenButtonListener;
import actionListener.EinkaufVerrechnenWindow.EinkaufVerrechnenBestätigenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientUebernehmenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientBetragAusBezahlenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientBetragBezahlenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientBetragHinzufugenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientEinkaufVerrechnenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientFensterSchliessenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientKlientLoschenButtonListener;
import actionListener.KlientBearbeitenWindow.ChangeKlientNameChangeButtonListener;
import actionListener.KlientHinzufugenWindow.AddKlientWindowAbbrechenButtonListener;
import actionListener.KlientHinzufugenWindow.AddKlientWindowOKButtonListener;
import actionListener.PropertiesWindow.PropertiesWindowActionListener;
import actionListener.mainWindow.MainWindowClosingListener;
import actionListener.mainWindow.MainWindowDateiExportierenListener;
import actionListener.mainWindow.MainWindowDateiNeuListener;
import actionListener.mainWindow.MainWindowDatenSpeichernButtonListener;
import actionListener.mainWindow.MainWindowKlientBearbeitenButtonListener;
import actionListener.mainWindow.MainWindowKlientDateiOeffnenAction;
import actionListener.mainWindow.MainWindowKlientHinzufugenButtonListener;
import actionListener.mainWindow.MainWindowLastOpenedAction;
import actionListener.mainWindow.MainWindowPropertiesActionListener;
import actionListener.mainWindow.MainWindowShowInfoDialogListener;
import actionListener.mainWindow.MainWindowTableListener;
import model.KassaProgFile;
import model.Produkt;
import model.ProduktListe;
import model.Schuldner;
import model.SchuldnerListe;
import utily.ExcelWorkbook;
import utily.PropertyHandler;
import windows.BetragAusBezahlenWindow;
import windows.BetragBezahlenWindow;
import windows.EinkaufVerrechnenWindow;
import windows.KlientBearbeitenWindow;
import windows.KlientHinzufWindow;
import windows.MainWindow;
import windows.PropertiesWindow;

public class Controller {
	
	private File workingFile = null;
	private SchuldnerListe liste;
	private PropertyHandler propertyHandler;
	private final File propFile = new File("properties.txt");
	private File lastOpenedFile;
	private ProduktListe productList;
	
	
	//einstellungen
	private String propLastOpenedFile;
	private String propLoadFileOnStart = "false";
	private String propOpenExcelFileAfterSave = "false";
	private String propExportExcelFileAfterSave = "false";
	
	//Fenster
	private MainWindow mainWindow;
	private KlientHinzufWindow addKlientWindow;
	private KlientBearbeitenWindow changeKlientWindow;
	private BetragBezahlenWindow betragBezahlenWindow;
	private BetragAusBezahlenWindow betragAusBezahlenWindow;
	private EinkaufVerrechnenWindow einkaufVerrechnenWindow;
	private PropertiesWindow propertiesWindow;
	
	//Änderung gemacht
	private boolean dataChanged = false;
	
	//Key für Verschüssselung
	private final String cryptKey = "super";
	
	//konstruktor
	public Controller(SchuldnerListe liste, MainWindow fenster) {
				
		//einstellungen laden
		loadProperties();
		
		//zuletzt geöffnete datei ins menü schreiben
		
		if(!(lastOpenedFile==null))
			fenster.setMenuLastOpenedText(lastOpenedFile.toString());
		else
			fenster.setMenuLastOpenedText("");
		
		//Produkte Laden
		loadProductList();
		
		this.liste = liste;
		this.mainWindow = fenster;
		
		addListenersToMainWindow();		//ActionListener hinzufügen

		if(propLoadFileOnStart.equals("true")){
			//datei laden
			if(loadFile(lastOpenedFile))
				actualizeStatusBar();
			
		}
		else
			actualizeStatusBar("Neue Datei");
	}
	
	//Produkte in Produktliste laden
	public void loadProductList() {
		productList = new ProduktListe();
		productList.addProdukt(new Produkt("Bier", 1.5));
		productList.addProdukt(new Produkt("Limo", 0.6));
		productList.addProdukt(new Produkt("Cola", 0.7));
		productList.addProdukt(new Produkt("Red Bull",1.2));
		productList.addProdukt(new Produkt("Wafferl/Mineral",0.2));
		productList.addProdukt(new Produkt("Kaffee",0.5));
		productList.addProdukt(new Produkt("Tee",0.2));
		//productList.addProdukt(new Produkt("summe",0.2));
	}

	//Einstelllungen aus datei laden
	public void loadProperties() {
		propertyHandler = new PropertyHandler(propFile);
		//wenn einstellungsdatei existiert
		if(propFile.exists()){
			try{
				propertyHandler.loadFromFile();
				
				propLastOpenedFile = propertyHandler.getProperty("lastOpenedFile");
				lastOpenedFile = new File(propLastOpenedFile);
				propLoadFileOnStart = propertyHandler.getProperty("loadFileOnStart");
				propOpenExcelFileAfterSave = propertyHandler.getProperty("OpenExcelFileAfterSave");
				propExportExcelFileAfterSave = propertyHandler.getProperty("exportExcelFileAfterSave");
				
			}
			catch (IOException e){
				workingFile=null;
			}
		}
		//wenn properties datei nicht existiert neu anlegen un standartwerte setzen
		else{
			propertyHandler.setProperty("lastOpenedFile", "");
			propertyHandler.setProperty("loadFileOnStart", "false");
			propertyHandler.setProperty("OpenExcelFileAfterSave", "false");
			propertyHandler.setProperty("exportExcelFileAfterSave", "false");
			propertyHandler.saveToFile("Einstellungen");
			
			
			//System.out.println("properties.txt nicht gefunden -> wurde neu angelegt");
		}
	}

	//schreibe daten in tabelle
	public void writeListToTable() {
		Vector<String> tempVector = new Vector<String>();
		mainWindow.getTable_set().setRowCount(0);	//anzahl der tabellenzeilen wieder auf 0 setzen -> tabelle löschen
		
		for(int i=0;i<this.liste.getSize();i++){
			
			tempVector = new Vector<String>();
			
			tempVector.add(liste.getSchuldnerListe().elementAt(i).getNameSchuldner());
			tempVector.add(Double.toString(liste.getSchuldnerListe().elementAt(i).getOffenerBetrag()));
			tempVector.add(liste.getSchuldnerListe().elementAt(i).getZuletztBezahlt());
			
			mainWindow.getTable_set().addRow(tempVector);
		}
		
		mainWindow.setGesamtOffenText(liste.getGesamtOffenerBetrag());
	}
	
	//Funktion liste speichern in datei
	@Deprecated
	public void writeDataFromListToFile() {		//wirft fehler an aufrufende  funktion zurück
			try{
			FileOutputStream fileStream = new FileOutputStream(workingFile);	//outputstream
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileStream));  //filewriter
			
			//liste wird durchgelaufen und in datei geschrieben
			
			
			for(int i=0; i<liste.getSize();i++){
//				bw.write(liste.getSchuldnerListe().elementAt(i).getNameSchuldner() + ',');
//				bw.write(Double.toString(liste.getSchuldnerListe().elementAt(i).getOffenerBetrag()) + ',');
//				bw.write(liste.getSchuldnerListe().elementAt(i).getZuletztBezahlt());
//				bw.newLine();
				String stringToWrite = liste.getSchuldnerListe().elementAt(i).getNameSchuldner() + ',' + Double.toString(liste.getSchuldnerListe().elementAt(i).getOffenerBetrag()) + ',' + liste.getSchuldnerListe().elementAt(i).getZuletztBezahlt();
				
				bw.write(stringToWrite);
				bw.newLine();
			}
			
			bw.close();		//datei schliessen	
			
			} catch (IOException e1) {
				//System.out.println("Fehler beim Speichern der Daten aufgetreten!!");
				e1.printStackTrace();
			}
			//System.out.println("Daten wurden geschrieben!");
	}
	
	public void actualizeStatusBar(){
		if(workingFile != null)
			mainWindow.getStatusBarFileName().setText(workingFile.toString());
		else
			mainWindow.getStatusBarFileName().setText("Neue Datei");
	}
	
	public void actualizeStatusBar(String text){
		mainWindow.getStatusBarFileName().setText(text);
	}
	
	//ActionListener hinzufügen
	public void addListenersToMainWindow() {
		//Listeners für Hauptfenster
		mainWindow.setSaveDataAction(new MainWindowDatenSpeichernButtonListener(this, false, mainWindow));						//listener für daten speichern button
		mainWindow.setButtonKlientHinzufugenAction(new MainWindowKlientHinzufugenButtonListener(this)); 	//listener für neuen klienten hinzufügen
		mainWindow.setButtonKlientBearbeitenAction(new MainWindowKlientBearbeitenButtonListener(this));
		mainWindow.setWindowClosingAction(new MainWindowClosingListener(this));
		
		//listeners für Menü
		mainWindow.setMenuKlientHinzufugenAction(new MainWindowKlientHinzufugenButtonListener(this));		//Listener für Menü klienten hinzufügen
		mainWindow.setMenuKlientBearbeitenAction(new MainWindowKlientBearbeitenButtonListener(this));
		mainWindow.setMenuDateiBeendenAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Window Closing wird manuell ausgelöst
				mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
			}
		});
		mainWindow.setMenuPropertiesAction(new MainWindowPropertiesActionListener(this));
		mainWindow.setMenuDateiOeffnenAction(new MainWindowKlientDateiOeffnenAction(this));
		mainWindow.setMenuLastOpenedAction(new MainWindowLastOpenedAction(this));
		mainWindow.setMenuDateiSpeichernAction(new MainWindowDatenSpeichernButtonListener(this,false, mainWindow));
		mainWindow.setMenuDateiNeuAction(new MainWindowDateiNeuListener(this));
		mainWindow.setMenuInfoActionListener(new MainWindowShowInfoDialogListener(this,mainWindow));
		mainWindow.setMenuDateiSpeichernUnterAction(new MainWindowDatenSpeichernButtonListener(this, true, mainWindow));
		mainWindow.setMenuDateiExportierenAction(new MainWindowDateiExportierenListener(this));
		//listener für Tabelle
		mainWindow.setTableAction(new MainWindowTableListener(this));
	}

	//ActionListener für Klient hinzufügen hinzufügen
	public void addListenersToKlientAddWindow(){
		addKlientWindow.setButtonOKListener(new AddKlientWindowOKButtonListener(this));
		addKlientWindow.setButtonAbbrechen(new AddKlientWindowAbbrechenButtonListener(this));
	}
	
	//ActionListener für Klient Bearbeiten hinzufügen
	public void addListenersToKlientBearbeitenWindow(){
		changeKlientWindow.setBetragHinzufugenButtonListener(new ChangeKlientBetragHinzufugenButtonListener(changeKlientWindow)); //Listener für Betrag hinzufügen 
		changeKlientWindow.setAnderungenSpeichernButtonListener(new ChangeKlientUebernehmenButtonListener(this, changeKlientWindow, mainWindow));		//Listener für Änderungen Speichern
		changeKlientWindow.setSchliessenButtonListener(new ChangeKlientFensterSchliessenButtonListener(changeKlientWindow));											//Listener für Fenster Schliessen
		changeKlientWindow.setBetragBezahlenButtonListener(new ChangeKlientBetragBezahlenButtonListener(changeKlientWindow, this));			//Listener für Betrag bezahlen button
		changeKlientWindow.setKlientLoschenButtonListener(new ChangeKlientKlientLoschenButtonListener(this, changeKlientWindow));		//Listener für klientlöschen button
		changeKlientWindow.setEinkaufVerrechnenButtonListener(new ChangeKlientEinkaufVerrechnenButtonListener(this));	//Listener für einkauf verrechnen button
		changeKlientWindow.setBetragAusBezahlenButtonListener(new ChangeKlientBetragAusBezahlenButtonListener(changeKlientWindow, this));	//Listener für betrag ausbezahlen button
		changeKlientWindow.setKlientNameChangeButtonListener(new ChangeKlientNameChangeButtonListener(this,changeKlientWindow));
	}
	
	//ActionListener für BetragBezahlen Window hinzufügen
	public void addListenersToBetragBezahlenWindow(){
		
		betragBezahlenWindow.setAbbrechenButtonListener(new BetragBezahlenAbbrechenButtonListener(betragBezahlenWindow));
		betragBezahlenWindow.setBestatigenButtonListener(new BetragBezahlenBestatigenButtonListener(this, betragBezahlenWindow, changeKlientWindow));
	}
	
	public void addListenersToBetragAusbezahlenWindow(){
		betragAusBezahlenWindow.setAbbrechenButtonListener(new BetragAusBezahlenAbbrechenButtonListener(betragAusBezahlenWindow));
		betragAusBezahlenWindow.setBestatigenButtonListener(new BetragAusBezahlenBestatigenButtonListener(this, betragAusBezahlenWindow, changeKlientWindow, mainWindow));
		
	}
	
	//Window Einkaufverrechnen Listener hinzufügen
	public void addListenersToEinkaufVerrechnenWindow(){
		einkaufVerrechnenWindow.setAbbrechenButtonListener(new EinkaufVerrechnenAbbrechenButtonListener(einkaufVerrechnenWindow));
		einkaufVerrechnenWindow.setBestatigenButtonListener(new EinkaufVerrechnenBestätigenButtonListener(einkaufVerrechnenWindow, changeKlientWindow, mainWindow));
	}
	
	//Properties fenster Listener hinzufügen
	public void addListenersToPropertiesWindow(){
		propertiesWindow.setActionListener(new PropertiesWindowActionListener(this,propertiesWindow));
	}
	//Hauptfenster anzeigen
	public void showMainWindow() {
		mainWindow.showWindow();
	}
			
	public void addSchuldnerToTable(Schuldner schuldner){
		Vector<String> tempVector = new Vector<String>();
		tempVector.add(schuldner.getNameSchuldner());
		tempVector.add(Double.toString(schuldner.getOffenerBetrag()));
		tempVector.add(schuldner.getZuletztBezahlt());
		
		mainWindow.getTable_set().addRow(tempVector);
	}
	
	//Daten von addKlientWindow einlesen
	public void getKlientHinzufugenWindowData(){

		String tempName = "";
		double tempBetrag = 0;
		
		try{
			tempName = addKlientWindow.getNameInput().trim();
			
			//wenn kein name eingegeben wurde
			if(tempName.equals("")){
			
				JOptionPane.showMessageDialog(addKlientWindow, "Es wurde kein Name eingetragen!!");
				addKlientWindow.setFocusInputName();
				return;
			}
			
			//falls schuldner bereits existiert
			if(liste.getIndexOfSchuldner(tempName)>=0){
				JOptionPane.showMessageDialog(addKlientWindow, "Klient existiert bereits!!");
				addKlientWindow.setInputNameMarked();
				addKlientWindow.setFocusInputName();
				return;
			}
			
			//wenn schuldner noch nicht existiert
			if(liste.getIndexOfSchuldner(tempName)==-1){
				//Saldowert auslesen und in Double umwandeln
				tempBetrag = Double.parseDouble(addKlientWindow.getSaldoInput().replace(',', '.'));
			}
		}
		//falls falscher wert oder keine Zahl eingegeben wurde
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(addKlientWindow, "Im Feld \"Saldo\" wurde keine Zahl eingegeben!!", "Falsche Eingabe!!",JOptionPane.WARNING_MESSAGE);
			addKlientWindow.setFocusInputSaldo();
			addKlientWindow.setInputSaldoMarked();
			return;
		}
		
		//werte in liste übernehmen
		liste.addEntry(new Schuldner(tempName, tempBetrag, ""));
		//liste in tabelle schreiben
		writeListToTable();
		addKlientWindow.dispose();
	}
	
	public void showKlientBearbeiten(){
		if(mainWindow.getSelectedTableRow() == -1){
			JOptionPane.showMessageDialog(mainWindow, "Kein Eintrag Ausgewählt!!");
			return;
		}
		
		changeKlientWindow = new KlientBearbeitenWindow(liste.getSchuldnerListe().elementAt(mainWindow.getSelectedTableRow()),liste);
		addListenersToKlientBearbeitenWindow();		//Listener zum Fenster hinzufügen
				
		changeKlientWindow.setLocation(getLocationForWindow(mainWindow, changeKlientWindow));
		changeKlientWindow.setVisible(true);
	}
	
	public void showKlientHinzufugen(){
		//Fenster erzeugen
		addKlientWindow = new KlientHinzufWindow();

		//Listener hinzufügen
		addListenersToKlientAddWindow();
				
		addKlientWindow.setLocation(getLocationForWindow(mainWindow, addKlientWindow));
		addKlientWindow.setVisible(true);
	}
	
	public void showEinkaufVerrechnen(){
		einkaufVerrechnenWindow = new EinkaufVerrechnenWindow();
		addListenersToEinkaufVerrechnenWindow();
		einkaufVerrechnenWindow.setLocation(getLocationForWindow(changeKlientWindow, einkaufVerrechnenWindow));
		einkaufVerrechnenWindow.setVisible(true);
	}
	
	public void showBetragBezahlen(){
		betragBezahlenWindow = new BetragBezahlenWindow();
		addListenersToBetragBezahlenWindow();

		betragBezahlenWindow.setLocation(getLocationForWindow(changeKlientWindow,betragBezahlenWindow));
		betragBezahlenWindow.setVisible(true);
	}
	public void showPropertiesWindow(){
		propertiesWindow = new PropertiesWindow(this, mainWindow);
		addListenersToPropertiesWindow();
		
		propertiesWindow.setLocation(getLocationForWindow(mainWindow, propertiesWindow));
		propertiesWindow.setVisible(true);
	}
	public void showBetragAusBezahlen(){
		betragAusBezahlenWindow = new BetragAusBezahlenWindow();
		addListenersToBetragAusbezahlenWindow();

		betragAusBezahlenWindow.setLocation(getLocationForWindow(changeKlientWindow,betragAusBezahlenWindow));
		betragAusBezahlenWindow.setVisible(true);	
	}
	
	//funktion zum ermitteln der neuen koordinaten. -> parameter ist Kind-Fenster
	
	public Point getLocationForWindow(Window kindWindow, Window newWindow){
		int x=0;
		int y=0;
		
		x = kindWindow.getX() + kindWindow.getWidth()/2 - newWindow.getWidth()/2;
		y = kindWindow.getY() + kindWindow.getHeight()/2 - newWindow.getHeight()/2;
		
		return new Point(x,y);
	}
	
	public void closeAddKlientWindow(){
		addKlientWindow.dispose();
	}

	//daten von datei in liste einfügen
	@Deprecated
	public void readListFromFile(File filename, SchuldnerListe liste){
		
		String tempString;
		StringBuilder str = new StringBuilder();
		Vector<String> tempVector = new Vector<String>();
		boolean fileExists = filename.exists();
		
		if(fileExists){	//wenn datei existiert datei auslesen und in liste schreiben
			try{
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(filename);

				while(scanner.hasNextLine()){
					tempString = scanner.nextLine();
					
					str = new StringBuilder();
					
					for(int i = 0; i<tempString.length(); i++){
						if(tempString.charAt(i)!= ','){
							str.append(tempString.charAt(i));
						}
						
						if(tempString.charAt(i)==',' || tempString.length() == i+1){
							tempVector.add(str.toString());
							str.delete(0, str.length());
						}
					}
					liste.addEntry(new Schuldner(tempVector));
					tempVector.clear();
				}
			}
				
			catch (FileNotFoundException e) {
				//System.out.println("Dateifehler!!");
				e.printStackTrace();
			}	
		}
		else{	//falls datei nicht existiert wird datei erstellt
			//System.out.println("Datei existiert nicht!!!");
				try{
					if(filename.createNewFile()){	//datei wird erstellt
						//System.out.println("datei wurde erstellt");
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
	
	}
	
	//String aufteilen und in Schuldnerliste schreiben
	public void readListFromStringToSchuldnerListe(String daten, SchuldnerListe liste){
		
		String tempString;
		StringBuilder str = new StringBuilder();
	
		Vector<String> tempVector = new Vector<String>();
			
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(daten);

			while(scanner.hasNextLine()){
				tempString = scanner.nextLine();

				str = new StringBuilder();
				
				for(int i = 0; i<tempString.length(); i++){
					if(tempString.charAt(i)!= ','){
						str.append(tempString.charAt(i));
					}
					
					if(tempString.charAt(i)==',' || tempString.length() == i+1){
						tempVector.add(str.toString());
						str.delete(0, str.length());
					}
				}
				
				liste.addEntry(new Schuldner(tempVector));
				tempVector.clear();
			}
		}

	public SchuldnerListe getSchuldnerListe(){
		return this.liste;
	}
	
	//String Zeichenweise von DAtei in String auslesen
	public String getStringFromFile(File datei){
		
		//System.out.println(datei);
		FileReader fr = null;
		StringBuffer sb = null;
		String fertigString = "";
		
		if(datei.exists()){
			try {
				fr = new FileReader(datei);
				sb = new StringBuffer();
				int ch;
				
				while( (ch=fr.read()) != -1 )
					sb.append((char)ch);
				fertigString = sb.toString();
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		else
			JOptionPane.showMessageDialog(null, "Datei wurde nicht gefunden!!");
		
		return fertigString;
	}
	
	//Aus datei lesen mit verschlüsselung
	public String getStringFromFile(File datei, boolean crypt, String key){
		KassaProgFile kpfile = new KassaProgFile(datei, crypt, key);
	
		kpfile.getStringFromFile();
		kpfile.readInformationFromString();
		
		String text = kpfile.getDataString();
		return text;
	}
	
	//Daten von Tabelle in String zurückgeben
	public String getStringFromTable() {		//wirft fehler an aufrufende  funktion zurück
		StringBuilder string = new StringBuilder();
	
		for(int i=0; i<liste.getSize();i++){
			string.append(liste.getSchuldnerListe().elementAt(i).getNameSchuldner() + ',' + Double.toString(liste.getSchuldnerListe().elementAt(i).getOffenerBetrag()) + ',' + liste.getSchuldnerListe().elementAt(i).getZuletztBezahlt());
			string.append('\n');
		}
		
		return string.toString();			
	}
	
	//String von Tabelle in Datei schreiben
	public void writeStringToFile(String text, File file){
		
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(file);
			fw.write(text);
			setDataChanged(false);
		}
		catch (FileNotFoundException e) {
			System.out.println("Fehler beim Speichern");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		finally{
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
				//System.out.println("Fehler beim schliessen der datei");
			}
		}
	}
	
	//in Datei schreiben nur verschlüsselt Überschriebene Methode
	public void writeStringToFile(String text, File file, boolean crypt, String key){
		KassaProgFile kpfile = new KassaProgFile(file, crypt,key);
		kpfile.createStringToWrite(crypt, text);
		kpfile.writeFile();
		//writeStringToFile(Xor.xorCrypt(text,key), file);
	}
	
	//geöffnete Datei zurückgeben
	public File getWorkingFile(){
		return workingFile;
	}
	
	public String getCryptKey(){
		return cryptKey;
	}
	
	//Daten geändert setzen
	public void setDataChanged(boolean changed){
		this.dataChanged = changed;
	}
	
	//Daten geändert abfragen
	public boolean getDataChanged(){
		return this.dataChanged;
	}
	
	//Datei laden -> gibt 0 zurück wenn keine kassaprog datei!
	public boolean loadFile(File file){
		KassaProgFile kpFile = new KassaProgFile(file, true, cryptKey);
		kpFile.getStringFromFile();
		kpFile.readInformationFromString();
		
		if(kpFile.isKassaProgFile()){
		
			//neue Schuldnerliste anlegen
			workingFile = file;
			liste = new SchuldnerListe();
			
				readListFromStringToSchuldnerListe(getStringFromFile(file,true,cryptKey),liste);
				writeListToTable();		//daten in tabelle schreiben
				actualizeStatusBar();
				mainWindow.setMenuLastOpenedText(workingFile.toString());
			return true;
		}
		else{
			return false;
		}
	}
	public void setLastOpenedFile(String lastOpened){
		propLastOpenedFile = lastOpened;
	}
	
	public PropertyHandler getPropertyHandler(){
		return propertyHandler;
	}
	
	public File getLastOpenedFile(){
		return lastOpenedFile;
	}	
	
	public void setWorkingFile(File file){
		workingFile = file;
	}
	
	//Datei speichern mit Dialog
	public File getFileFromSaveDialog(String fileType, String fileDescription){
		int chooseReturn;
		JFileChooser chooser = new JFileChooser();
		
		//Filter setzen
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				if (file.isDirectory())
					return true;
				return file.getName().toLowerCase().endsWith("." + fileType);
			}

			@Override
			public String getDescription() {
				return fileDescription;
			}
		});
		
		if(getLastOpenedFile()!=null)
			chooser.setCurrentDirectory(getLastOpenedFile().getAbsoluteFile());	
			
		chooseReturn = chooser.showSaveDialog(null);
		
		
		if(chooseReturn==0){
			return new File(chooser.getSelectedFile() +"." + fileType);
//			setWorkingFile(new File(chooser.getSelectedFile()+".kpr"));
//			writeStringToFile(getStringFromTable(), getWorkingFile(),true,getCryptKey());
//			actualizeStatusBar();			
		}
		
		else return null;
	}
	
	//Datei schliessen
	public void closeFile(){
		
		//speicherabfrage wenn daten geänder wurden
		if(dataChanged){
			if(JOptionPane.showConfirmDialog(null, "Wollen Sie vor dem Beenden Speichern?", "Daten wurden noch nicht gespeichert!!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0)
				if(getWorkingFile()==null){
					getFileFromSaveDialog("kpr","KassaProg Dateien");
				}
				else
					writeStringToFile(getStringFromTable(), getWorkingFile(),true,getCryptKey());
		}

		//datei schliessen
		
		liste = new SchuldnerListe();
		writeListToTable();
		setWorkingFile(null);
		actualizeStatusBar("Neue Datei");
	}
	
	//Excel datei erstellen
	public void writeExcelFile(File file){
		ExcelWorkbook myWorkbook = new ExcelWorkbook(liste,productList, file);
		myWorkbook.writeWorkbook();
	}
	
	public boolean getPropOpenExcelAfterSave(){
		if(propOpenExcelFileAfterSave.equals("true"))
			return true;
		else 
			return false;
	}
	
	public boolean getPropExportExcelAfterSave(){
		if(propExportExcelFileAfterSave.equals("true"))
			return true;
		else 
			return false;
	}
	
	public boolean getPropLoadFileOnStart(){
		if(propLoadFileOnStart.equals("true"))
			return true;
		else 
			return false;
	}
}


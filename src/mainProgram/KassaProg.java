package mainProgram;

import model.SchuldnerListe;
import windows.MainWindow;



public class KassaProg {

	public static void main(String[] args) {

		SchuldnerListe liste = new SchuldnerListe();		//Modell erzeugen
		MainWindow fenster = new MainWindow();				//View erzeugen
		Controller c = new Controller( liste, fenster );	//Controller erzeugen
		c.showMainWindow();		
	}
}

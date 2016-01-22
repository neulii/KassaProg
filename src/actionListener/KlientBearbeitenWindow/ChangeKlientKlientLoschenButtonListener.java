package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import mainProgram.Controller;
import windows.KlientBearbeitenWindow;

public class ChangeKlientKlientLoschenButtonListener implements ActionListener {

	private Controller controller;
	private KlientBearbeitenWindow fenster;
	int antwort = -1;
	
	public ChangeKlientKlientLoschenButtonListener(Controller controller, KlientBearbeitenWindow fenster) {
		this.controller = controller;
		this.fenster = fenster;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		antwort = JOptionPane.showConfirmDialog(fenster, "Löschen bestätigen", "Wirklich löschen?", JOptionPane.OK_CANCEL_OPTION , JOptionPane.WARNING_MESSAGE);		
		if(antwort == 0){
			//Schuldner löschen
			controller.getSchuldnerListe().delEntry(fenster.getOpenSchuldner());
			fenster.dispose();
			controller.writeListToTable();
			
			//Datensatz wurde geändert
			controller.setDataChanged(true);
		}
	}

}

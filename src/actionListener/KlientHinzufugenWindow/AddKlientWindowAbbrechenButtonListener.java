package actionListener.KlientHinzufugenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mainProgram.Controller;


//ActionListener f�r Fenster Klient hinzuf�gen -> Abbrechen Button

public class AddKlientWindowAbbrechenButtonListener implements ActionListener{

	private Controller controller;
	
	public AddKlientWindowAbbrechenButtonListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.closeAddKlientWindow();
	}

}

package actionListener.KlientHinzufugenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainProgram.Controller;

//ActionListener für Fenster Klient hinzufügen -> OK Button

public class AddKlientWindowOKButtonListener implements ActionListener{

	private Controller controller;
	
	public AddKlientWindowOKButtonListener(Controller controller) {
	this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getKlientHinzufugenWindowData();
		controller.setDataChanged(true);
	}
}

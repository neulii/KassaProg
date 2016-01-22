package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainProgram.Controller;
import windows.KlientBearbeitenWindow;

public class ChangeKlientBetragAusBezahlenButtonListener implements ActionListener{
	private Controller controller;

	public ChangeKlientBetragAusBezahlenButtonListener(KlientBearbeitenWindow fenster, Controller controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.showBetragAusBezahlen();
	}
}

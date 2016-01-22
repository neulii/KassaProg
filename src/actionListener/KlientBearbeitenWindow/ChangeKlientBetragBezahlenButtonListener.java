package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import mainProgram.Controller;
import windows.KlientBearbeitenWindow;

public class ChangeKlientBetragBezahlenButtonListener implements ActionListener {

	//private KlientBearbeitenWindow fenster;
	private Controller controller;
	
	public ChangeKlientBetragBezahlenButtonListener(KlientBearbeitenWindow fenster, Controller controller){
		//this.fenster = fenster;
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.showBetragBezahlen();
	}

}

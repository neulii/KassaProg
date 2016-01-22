package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mainProgram.Controller;
import windows.KlientBearbeitenWindow;
import windows.MainWindow;

public class ChangeKlientAnderungenSpeichernButtonListener implements ActionListener{

	private Controller controller;
	private KlientBearbeitenWindow fenster;
	//private MainWindow mainWindow;

	
	public ChangeKlientAnderungenSpeichernButtonListener(Controller controller,KlientBearbeitenWindow changeKlientWindow, MainWindow mainWindow) {
		this.controller = controller;
		this.fenster = changeKlientWindow;
		//this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		fenster.getOpenSchuldner().addBetrag(fenster.getBetragHinzu());
		
		fenster.setBetragHinzu(0);
		controller.writeListToTable();
		
	}

}
